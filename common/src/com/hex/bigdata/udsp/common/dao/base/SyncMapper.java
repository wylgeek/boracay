package com.hex.bigdata.udsp.common.dao.base;

import com.hex.bigdata.udsp.common.dao.ComOperationLogMapper;
import com.hex.bigdata.udsp.common.dao.cache.Cache;
import com.hex.bigdata.udsp.common.model.ComOperationLog;
import com.hex.bigdata.udsp.common.util.CacheUtil;
import com.hex.bigdata.udsp.common.util.JSONUtil;
import com.hex.bigdata.udsp.common.util.MD5Util;
import com.hex.goframe.dao.BaseMapper;
import com.hex.goframe.util.Util;
import com.hex.goframe.util.WebUtil;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * 同步操作的抽象类，对数据库（和内存）操作，DDL操作都记日志且日志入库
 *
 * @param <T>
 */
public abstract class SyncMapper<T> extends BaseMapper {
    private static Logger logger = LogManager.getLogger(SyncMapper.class);
    private static final FastDateFormat format = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    private String clazz = this.getClass().getName();

    @Autowired
    private ComOperationLogMapper comOperationLogMapper;

//    protected abstract Cache<T> getCache();

    /**
     * 缓存模式（none、local、ehcache、redis）
     */
    @Value("${cache.mode:ehcache}")
    private String cacheMode;

    protected Cache<T> cache;

    /**
     * 这里必须是protected，如子类需要使用其他缓存时可以自行重载
     *
     * @return
     */
    protected Cache<T> getCache() {
        if (this.cache == null) {
            this.cache = CacheUtil.getCache(cacheMode);
        }
        return this.cache;
    }

    // ----------------------------insert----------------------------------------

    /**
     * 插入
     *
     * @param id
     * @param t
     * @return
     */
    public boolean insert(String id, T t) {
        String key = MD5Util.MD5_16(clazz) + ":" + id;
        tranInsertObject(t);
        boolean status = insertExe(t);
        if (status) getCache().insertCache(key, t);
        insertLog("1", "Class=" + clazz + " Object=" + t.getClass().getName());
        return status;
    }

    protected abstract boolean insertExe(T t);

    //------------------------------update------------------------------------------

    /**
     * 更新
     *
     * @param id
     * @param t
     * @return
     */
    public boolean update(String id, T t) {
        String key = MD5Util.MD5_16(clazz) + ":" + id;
        tranUpdateObject(t);
        boolean status = updateExe(t);
        if (status) getCache().updateCache(key, t);
        insertLog("2", "Class=" + clazz + " Object=" + t.getClass().getName());
        return status;
    }

    protected abstract boolean updateExe(T t);

    //---------------------------------delete--------------------------------------

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean delete(String id) {
        String key = MD5Util.MD5_16(clazz) + ":" + id;
        boolean status = deleteExe(id);
        if (status) getCache().deleteCache(key);
        insertLog("3", "Class=" + clazz + " PkId=" + id);
        return status;
    }

    protected abstract boolean deleteExe(String id);

    //------------------------------------select------------------------------------

    /**
     * 查询
     *
     * @param id
     * @return
     */
    public T select(String id) {
        String key = MD5Util.MD5_16(clazz) + ":" + id;
        Cache<T> cache = getCache();
        /*T t = cache.selectCache(key);
        if (t == null) {*/
            T t  = selectExe(id);
            /*if (t != null) {
                cache.insertCache(key, t);
            }
        }*/
        logger.debug("查询 Class=" + clazz + " PkId=" + id);
        return t;
    }

    protected abstract T selectExe(String id);

    //-----------------------------------insertList---------------------------------------

    /**
     * 插入集合
     *
     * @param id
     * @param list
     * @return
     */
    public boolean insertList(String id, List<T> list) {
        String key = MD5Util.MD5_16(clazz) + ":" + id;
        boolean status = true;
        for (T t : list) {
            tranInsertObject(t);
            if (!insertExe(t)) {
                status = false;
                break;
            }
        }
        if (status) getCache().insertListCache(key, list);
        insertLog("1", "Class=" + clazz + " ListSize=" + list.size());
        return status;
    }

    //------------------------------updateList------------------------------------------

    /**
     * 更新集合
     *
     * @param id
     * @param list
     * @return
     */
    public boolean updateList(String id, List<T> list) {
        String key = MD5Util.MD5_16(clazz) + ":" + id;
        boolean status = true;
        for (T t : list) {
            tranUpdateObject(t);
            if (!updateExe(t)) {
                status = false;
                break;
            }
        }
        if (status) getCache().updateListCache(key, list);
        insertLog("2", "Class=" + clazz + " ListSize=" + list.size());
        return status;
    }

    //---------------------------------deleteList--------------------------------------

    /**
     * 删除集合
     *
     * @param id
     * @return
     */
    public boolean deleteList(String id) {
        String key = MD5Util.MD5_16(clazz) + ":" + id;
        boolean status = deleteListExe(id);
        if (status) getCache().deleteListCache(key);
        insertLog("3", "Class=" + clazz + " FkId=" + id);
        return status;
    }

    protected abstract boolean deleteListExe(String id);

    //-----------------------------------selectList---------------------------------------

    /**
     * 查询集合
     *
     * @param id
     * @return
     */
    public List<T> selectList(String id) {
        String key = MD5Util.MD5_16(clazz) + ":" + id;
        Cache<T> cache = getCache();
        List<T> list = cache.selectListCache(key);
        if (list == null || list.size() == 0) {
            list = selectListExe(id);
            if (list != null && list.size() > 0) {
                cache.insertListCache(key, list);
            }
        }
        logger.debug("批量查询 Class=" + clazz + " FkId=" + id);
        return list;
    }

    protected abstract List<T> selectListExe(String id);

    // --------------------------------------------------------------------------------------
    private void tranInsertObject(T t) {
        Class clazz = t.getClass();
        try {
            Field field = clazz.getDeclaredField("delFlg");
            setField(field, t, "0");
        } catch (NoSuchFieldException e) {
            //
        }
        String userId = WebUtil.getCurrentUserId();
        try {
            Field field = clazz.getDeclaredField("crtUser");
            setField(field, t, userId);
        } catch (NoSuchFieldException e) {
            //
        }
        try {
            Field field = clazz.getDeclaredField("uptUser");
            setField(field, t, userId);
        } catch (NoSuchFieldException e) {
            //
        }
        String nowTime = format.format(new Date());
        try {
            Field field = clazz.getDeclaredField("crtTime");
            setField(field, t, nowTime);
        } catch (NoSuchFieldException e) {
            //
        }
        try {
            Field field = clazz.getDeclaredField("uptTime");
            setField(field, t, nowTime);
        } catch (NoSuchFieldException e) {
            //
        }
    }

    private void tranUpdateObject(T t) {
        Class clazz = t.getClass();
        String userId = WebUtil.getCurrentUserId();
        try {
            Field field = clazz.getDeclaredField("uptUser");
            setField(field, t, userId);
        } catch (NoSuchFieldException e) {
            //
        }
        String nowTime = format.format(new Date());
        try {
            Field field = clazz.getDeclaredField("uptTime");
            setField(field, t, nowTime);
        } catch (NoSuchFieldException e) {
            //
        }
    }

    private void setField(Field field, T t, String vaule) {
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(t, vaule);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertLog(String type, String log) {
        try {
            ComOperationLog comOperationLog = new ComOperationLog();
            String pkId = Util.uuid();
            comOperationLog.setPkId(pkId);
            comOperationLog.setActionType(type);
            comOperationLog.setActionUser(WebUtil.getCurrentUserName());
            comOperationLog.setActionUrl(WebUtil.getRequest().getRequestURL().toString());
            comOperationLog.setActionTime(format.format(new Date()));
            comOperationLog.setActionContent(log);
            comOperationLogMapper.insert(pkId, comOperationLog);
            logger.info(JSONUtil.parseObj2JSON(comOperationLog));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

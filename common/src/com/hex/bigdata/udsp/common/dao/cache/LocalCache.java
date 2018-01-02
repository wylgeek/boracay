package com.hex.bigdata.udsp.common.dao.cache;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.hex.bigdata.udsp.common.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by junjiem on 2017-2-16.
 */
@Repository("localCache")
public class LocalCache<T> implements Cache<T> {

    @Value("${local.cache.maximum.size:100000}")
    private long maximumSize;

    @Value("${local.cache.expire.after.access.time.ms:3600000}")
    private long expireAfterAccessTimeMs;

    private static com.google.common.cache.Cache<Object, Object> cache;

    private static Map<Long, com.google.common.cache.Cache<Object, Object>> cacheMap;

    private com.google.common.cache.Cache<Object, Object> getCache() {
        if (this.cache == null) {
            this.cache = CacheBuilder.newBuilder()//
                    .maximumSize(maximumSize)//
                    .expireAfterAccess(expireAfterAccessTimeMs, TimeUnit.MILLISECONDS)//
                    .ticker(Ticker.systemTicker())//
                    .build();
        }
        return this.cache;
    }

    private boolean insert(String key, Object obj) {
        synchronized (key) {
            getCache().put(key, obj);
            return true;
        }
    }

    private boolean update(String key, Object obj) {
        synchronized (key) {
            getCache().invalidate(key);
            getCache().put(key, obj);
            return true;
        }
    }

    private boolean delete(String key) {
        synchronized (key) {
            getCache().invalidate(key);
            return true;
        }
    }

    private Object select(String key) {
        synchronized (key) {
            return getCache().getIfPresent(key);
        }
    }

    @Override
    public boolean insertCache(String key, T t) {
        return insert(key, cloneObj(t));
    }

    @Override
    public boolean updateCache(String key, T t) {
        return update(key, cloneObj(t));
    }

    @Override
    public boolean deleteCache(String key) {
        return delete(key);
    }

    @Override
    public T selectCache(String key) {
        Object obj = select(key);
        if (obj == null && cacheMap != null) {
            synchronized (key) {
                for (Map.Entry<Long, com.google.common.cache.Cache<Object, Object>> entry : cacheMap.entrySet()) {
                    obj = entry.getValue().getIfPresent(key);
                    if (obj != null) {
                        break;
                    }
                }
            }
        }
        return cloneObj((T) obj);
    }

    @Override
    public boolean insertListCache(String key, List<T> list) {
        return insert(key, cloneList(list));
    }

    @Override
    public boolean updateListCache(String key, List<T> list) {
        return update(key, cloneList(list));
    }

    @Override
    public boolean deleteListCache(String key) {
        return delete(key);
    }

    @Override
    public List<T> selectListCache(String key) {
        Object obj = select(key);
        return cloneList((List<T>) obj);
    }

    @Override
    @Deprecated
    public <T> List<T> selectCacheLike(String likeKey) {
        return null;
    }

    @Override
    @Deprecated
    public boolean removeCacheLike(String likeKey) {
        return false;
    }

    @Override
    public boolean insertTimeoutCache(String key, T t, long timeout) {
        synchronized (key) {
            com.google.common.cache.Cache<Object, Object> cache = cacheMap.get(timeout);
            if (cache == null) {
                cache = CacheBuilder.newBuilder()//
                        .maximumSize(maximumSize)//
                        .expireAfterWrite(timeout, TimeUnit.MILLISECONDS)//
                        .ticker(Ticker.systemTicker())//
                        .build();
                cacheMap.put(timeout, cache);
            }
            cache.put(key, cloneObj(t));
            return true;
        }
    }

    /**
     * 克隆对象
     *
     * @param obj
     * @return
     */
    private T cloneObj(T obj) {
        T t = null;
        if (obj != null) {
            try {
                if (obj instanceof Integer) {
                    t = (T) new Integer((Integer) obj);
                } else if (obj instanceof Long) {
                    t = (T) new Long((Long) obj);
                } else if (obj instanceof Short) {
                    t = (T) new Short((Short) obj);
                } else if (obj instanceof Double) {
                    t = (T) new Double((Double) obj);
                } else if (obj instanceof Float) {
                    t = (T) new Float((Float) obj);
                } else {
                    t = (T) obj.getClass().newInstance();
                    ObjectUtil.copyObject(obj, t);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    /**
     * 克隆集合
     *
     * @param objs
     * @return
     */
    private List<T> cloneList(List<T> objs) {
        List<T> list = null;
        if (objs != null) {
            list = new ArrayList<>();
            T t = null;
            for (T obj : objs) {
                t = cloneObj(obj);
                if (t != null)
                    list.add(t);
            }
        }
        return list;
    }
}

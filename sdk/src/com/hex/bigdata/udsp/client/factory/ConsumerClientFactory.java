package com.hex.bigdata.udsp.client.factory;

import com.hex.bigdata.udsp.config.SdkClientConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA
 * Author: tomnic.wang
 * DATE:2017/7/19
 * TIME:17:06
 */
public class ConsumerClientFactory {

    /**
     * 创建自定义的客户端
     *
     * @return
     */
    public static <T> T createCustomClient(Class<T> T, String requestUrl) {
        T t = null;
        try {
            Constructor constructor = T.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            t = (T) constructor.newInstance(requestUrl);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 创建默认的客户端
     *
     * @return
     */
    public static <T> T createCustomClient(Class<T> T) {
        return createCustomClient(T, SdkClientConfig.consumeUrl);
    }
}

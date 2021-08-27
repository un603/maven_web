package com.hit.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @Classname WEBUtils
 * @Description TODO
 * @Author DELL
 * @Date 2021/7/26 16:25
 */
public class WEBUtils {
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * @Author DELL
     * @Description  将字符串转换成为 int 类型的数据
     * @Date 2021/7/27 15:28
     * @Param [strInt, defaultValue]
     * @Return int
     * @Version 1.0
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}

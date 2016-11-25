/**
 *
 *	created by 冀秦, Sep 12, 2016
 *	Copyright (c) 2016, 1042407818@qq.com All Rights Reserved.
 *
 *                #####################################################
 *                #                                                   #
 *                #                       _oo0oo_                     #
 *                #                      o8888888o                    #
 *                #                      88" . "88                    #
 *                #                      (| -_- |)                    #
 *                #                      0\  =  /0                    #
 *                #                    ___/`---'\___                  #
 *                #                  .' \\|     |# '.                 #
 *                #                 / \\|||  :  |||# \                #
 *                #                / _||||| -:- |||||- \              #
 *                #               |   | \\\  -  #/ |   |              #
 *                #               | \_|  ''\---/''  |_/ |             #
 *                #               \  .-\__  '-'  ___/-. /             #
 *                #             ___'. .'  /--.--\  `. .'___           #
 *                #          ."" '<  `.___\_<|>_/___.' >' "".         #
 *                #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 *                #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 *                #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 *                #                       `=---='                     #
 *                #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 *                #                                                   #
 *                #               佛祖保佑         永无BUG              #
 *                #                                                   #
 *                #####################################################
 */

package android.mobile.commonutils;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RefectionUtils {
    /**
     * 判断类是否是基础数据类型
     * 目前支持11种
     *
     * @param clazz
     * @return
     */
    public static boolean isBaseDataType(Class<?> clazz) {
        return clazz.isPrimitive() || clazz.equals(String.class) || clazz.equals(Boolean.class)
                || clazz.equals(Integer.class) || clazz.equals(Long.class) || clazz.equals(Float.class)
                || clazz.equals(Double.class) || clazz.equals(Byte.class) || clazz.equals(Character.class)
                || clazz.equals(Short.class) || clazz.equals(Date.class) || clazz.equals(byte[].class)
                || clazz.equals(Byte[].class);
    }

    /**
     * 根据类获取对象：不再必须一个无参构造
     *
     * @param claxx
     * @return
     * @throws Exception
     */
    public static <T> T newInstance(Class<T> claxx) throws Exception {
        Constructor<?>[] cons = claxx.getDeclaredConstructors();
        for (Constructor<?> c : cons) {
            Class[] cls = c.getParameterTypes();
            if (cls.length == 0) {
                c.setAccessible(true);
                return (T) c.newInstance();
            } else {
                Object[] objs = new Object[cls.length];
                for (int i = 0; i < cls.length; i++) {
                    objs[i] = getDefaultPrimiticeValue(cls[i]);
                }
                c.setAccessible(true);
                return (T) c.newInstance(objs);
            }
        }
        return null;
    }

    public static Object getDefaultPrimiticeValue(Class clazz) {
        if (clazz.isPrimitive()) {
            return clazz == boolean.class ? false : 0;
        }
        return null;
    }

    /**
     * 判断是否是集合对象
     * @param claxx
     * @return
     */
    public static boolean isCollection(Class claxx) {
        return Collection.class.isAssignableFrom(claxx);
    }

    /**
     * 判断是否是数组
     * @param claxx
     * @return
     */
    public static boolean isArray(Class claxx) {
        return claxx.isArray();
    }

    /**
     * 判断是否序列化
     *
     * @param f
     * @return
     */
    public static boolean isSerializable(Field f) {
        Class<?>[] cls = f.getType().getInterfaces();
        for (Class<?> c : cls) {
            if (Serializable.class == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置域的值
     *
     * @param f
     * @param obj
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object set(Field f, Object obj, Object value) throws IllegalArgumentException, IllegalAccessException {
        f.setAccessible(true);
        f.set(obj, value);
        return f.get(obj);
    }

    /**
     * 获取域的值
     *
     * @param f
     * @param obj
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object get(Field f, Object obj) throws IllegalArgumentException, IllegalAccessException {
        f.setAccessible(true);
        return f.get(obj);
    }

    public static boolean isLong(Field field) {
        return field.getType() == long.class || field.getType() == Long.class;
    }

    public static boolean isInteger(Field field) {
        return field.getType() == int.class || field.getType() != Integer.class;
    }

    /**
     * 获取域的泛型类型，如果不带泛型返回null
     *
     * @param f
     * @return
     */
    public static Class<?> getGenericType(Field f) {
        Type type = f.getGenericType();
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type instanceof Class<?>) return (Class<?>) type;
        } else if (type instanceof Class<?>) return (Class<?>) type;
        return null;
    }

    /**
     * 获取数组的类型
     *
     * @param f
     * @return
     */
    public static Class<?> getComponentType(Field f) {
        return f.getType().getComponentType();
    }

    /**
     * 获取全部Field，包括父类
     *
     * @param claxx
     * @return
     */
    public static List<Field> getAllDeclaredFields(Class<?> claxx) {
        // find all field.
        LinkedList<Field> fieldList = new LinkedList<Field>();
        while (claxx != null && claxx != Object.class) {
            Field[] fs = claxx.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                if (!isInvalid(f)) {
                    fieldList.addLast(f);
                }
            }
            claxx = claxx.getSuperclass();
        }
        return fieldList;
    }

    /**
     * 是静态常量或者内部结构属性
     *
     * @param f
     * @return
     */
    public static boolean isInvalid(Field f) {
        return (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers())) || f.isSynthetic();
    }
}

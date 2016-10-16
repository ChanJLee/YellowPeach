package com.chan.lib;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by chan on 16/10/15.
 * jiacheng.li@shanbay.com
 */
public class YellowPeach {
    public static Peach bind(Object o) {
        try {
            final String clazzName = o.getClass().getPackage().getName().toString() +
                    ".YellowPeach$" + o.getClass().getCanonicalName().replaceAll("\\.", "\\$");
            Class<?> clazz = o.getClass().getClassLoader().loadClass(clazzName);
            Constructor<?> constructors[] = clazz.getConstructors();
            return (Peach) constructors[0].newInstance(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}

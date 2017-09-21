package com.expertzlab.yieldmanagement.fileutil;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by expertzlab on 8/11/17.
 */
public abstract class DataSetter {
    abstract Object run() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}

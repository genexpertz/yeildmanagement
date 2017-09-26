package com.expertzlab.yieldmanagement.fileutils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created by expertzlab on 8/11/17.
 */
public abstract class DataSetter {
    public abstract Date run() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}

package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.Owner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by expertzlab on 8/11/17.
 */
public class OwnerDataSetter extends DataSetter{
    Class clazz;
    String[] hArray;
    String[] rArray;
    OwnerDataSetter(Class clazz,String[] hArray,String[] rArray)
    {
        this.clazz = clazz;
        this.hArray=this.hArray;
        this.rArray=this.rArray;
    }


    void setData() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Owner ow = null;
        for(int i=0; i<hArray.length; i++) {
            if (clazz.getName().equals("Participant")) {

                Class<?> loadedClass = Class.forName(clazz.getName());
                ow = (Owner) loadedClass.newInstance();
            }
            Method m = clazz.getMethod("set" + hArray[i], String.class);
            m.invoke(ow, rArray[i]);
        }
    }
}

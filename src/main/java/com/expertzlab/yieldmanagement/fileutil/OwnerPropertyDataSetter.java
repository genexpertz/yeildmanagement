package com.expertzlab.yieldmanagement.fileutil;

import com.expertzlab.yieldmanagement.models.Availability;
import com.expertzlab.yieldmanagement.models.OwnerProperty;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by expertzlab on 9/6/17.
 */
public abstract class OwnerPropertyDataSetter extends DataSetter {
    Class clazz;
    String[] hArray;
    String[] rArray;

    OwnerPropertyDataSetter(Class clazz, String[] hArray, String[] rArray) {
        this.clazz =clazz;
        this.hArray = hArray;
        this.rArray = rArray;
    }
    public OwnerProperty run() {

        OwnerProperty op =null;
        Class<?> loadedClass = null;
        try {
            loadedClass = Class.forName(clazz.getName());
            op = (OwnerProperty) loadedClass.newInstance();
            for(int i = 0; i< hArray.length; i++) {

                Method m = clazz.getMethod("set" +capitalizeFirstLetter( hArray[i]), String.class);
                m.invoke(op, rArray[i]);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return op;
    }

    public String capitalizeFirstLetter(String str){
        String ch = str.substring(0,1);
        String remStr= str.substring(1);
        String result= ch.toUpperCase()+remStr;
        return result;
    }
}

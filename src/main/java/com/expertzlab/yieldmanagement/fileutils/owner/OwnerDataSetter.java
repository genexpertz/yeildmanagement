package com.expertzlab.yieldmanagement.fileutils.owner;

import com.expertzlab.yieldmanagement.fileutils.DataSetter;
import com.expertzlab.yieldmanagement.models.Owner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by expertzlab on 8/11/17.
 */
public class OwnerDataSetter extends DataSetter {
    Class clazz;
    String[] hArray;
    String[] rArray;

    public OwnerDataSetter(Class clazz, String[] hArray, String[] rArray) {
        this.clazz =clazz;
        this.hArray = hArray;
        this.rArray = rArray;
    }
    public Owner run() {
        Owner own =null;
        //Class<?> loadedClass = null;
        try {
            //loadedClass = Class.forName(clazz.getName());
            own = (Owner) clazz.newInstance();
            for(int i = 0; i< hArray.length; i++) {

                Method m = clazz.getMethod("set" +capitalizeFirstLetter( hArray[i]), String.class);
                m.invoke(own, rArray[i]);
            }
        }  catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return own;
    }

    public String capitalizeFirstLetter(String str){
        String ch = str.substring(0,1);
        String remStr= str.substring(1);
        String result= ch.toUpperCase()+remStr;
        return result;
    }
}

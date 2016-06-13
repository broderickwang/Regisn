package com.example.ttb.regisn.bean;

import java.io.Serializable;

/**
 * Created by ttb on 16/4/16.
 */
public class BaseInfo implements Serializable{
    private String name;
    private int age;

    public BaseInfo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name+"-"+age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

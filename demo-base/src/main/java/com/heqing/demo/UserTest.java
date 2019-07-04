package com.heqing.demo;

import java.io.Serializable;

/**
 * @author heqing
 * @date 2019/5/17 13:45
 */
public class UserTest implements Serializable {

    private String name;
    private int age;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

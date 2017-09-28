package com.hex.bigdata.udsp.elsearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ElSearchJsonTest {
    private String name;
    private int age;
    private String address;

    public static void main(String[] args) {
        ElSearchJsonTest elSearchJsonTest = new ElSearchJsonTest("lily");
        //System.out.println(JSON.toJSONString(elSearchJsonTest));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("elSearchJsonTest",elSearchJsonTest);
        System.out.println(jsonObject.toJSONString());

    }

    public ElSearchJsonTest(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "ElSearchJsonTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}

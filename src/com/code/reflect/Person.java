package com.code.reflect;

/**
 * Created by jon on 2016/10/17.
 */
public class Person {
    private String name;
    private int age;

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
    @Override
    public String toString(){
        System.out.println("aaa");
        return "["+this.name+","+this.age+"]";
    }
    public void say(){
        System.out.println("I'm Person!");
    }
    public Person(String name ,int age){
        this.name=name;
        this.age=age;
    }
    public Person(){}

}

package com.code.reflect;

import org.testng.annotations.Test;
import org.yaml.snakeyaml.constructor.Construct;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by jon on 2016/10/17.
 */
public class Demo {
    private String name;
    public void say(){
        System.out.println("Demo Say!");
    }
    @Test
    public void test(){
        /*
        获取类名称,包名;
         */
        Demo demo=new Demo();
        System.out.println(demo.getClass().getName());
    }
    @Test
    public void test2(){
        /*
        实例化Class类对象
         */
        Class<?> demo1=null;
        Class<?> demo2=null;
        Class<?> demo3=null;
        try {
            demo1=Class.forName("com.code.reflect.Demo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        demo2=new Demo().getClass();
        demo3=Demo.class;
        System.out.println("类名称:"+demo1.getName());
        System.out.println("类名称:"+demo2.getName());
        System.out.println("类名称:"+demo3.getName());
    }
    @Test
    public void test3() throws Exception {
        /*
        通过Class类实例化其他类对象
         */
        Class<?> demo=null;
        demo=Class.forName("com.code.reflect.Person");
        Person per=null;
        per= (Person) demo.newInstance();
        per.setName("tester");
        per.setAge(18);
        System.out.println(per);
        per.say();
    }
    @Test
    public void test4() throws Exception{
        Class<?> demo=null;
        demo=Class.forName("com.code.reflect.Person");
        Person per1=null;
        Person per2=null;
        Person per3=null;
        Person per4=null;
        Constructor<?> cons[]=demo.getConstructors();
        per1=(Person)cons[0].newInstance();
        per2=(Person)cons[1].newInstance("jin",10);
//        per3=(Person)cons[2].newInstance("a",20);
        //per4=(Person)cons[3].newInstance("ji",40);
        System.out.println(per1);
        System.out.println(per2);
        System.out.println(per3);
        System.out.println(per4);
        for(int i=0;i<cons.length;i++){
            System.out.println("构造函数:"+cons[i].toString());
        }

    }

    @Test
    public void test5(){
        /*
        获取父类名称
         */
        Class<?> demo=null;
        try{
            demo=Class.forName("com.code.reflect.Person");
        }catch (Exception e) {
            e.printStackTrace();
        }
        //取得父类
        Class<?> temp=demo.getSuperclass();
        System.out.println("继承的父类为：   "+temp.getName());
    }

    @Test
    public void test6(){
        /*
        获取实现的接口
         */
        Class<?> demo=null;
        try{
            demo=Class.forName("com.code.reflect.Person");
        }catch (Exception e) {
            e.printStackTrace();
        }
        //保存所有的接口
        Class<?> intes[]=demo.getInterfaces();
        for (int i = 0; i < intes.length; i++) {
            System.out.println("实现的接口   "+intes[i].getName());
        }

    }

    @Test
    public void test7(){
        Class<?> demo=null;
        try{
            demo=Class.forName("com.code.reflect.Person");
        }catch (Exception e) {
            e.printStackTrace();
        }
        Constructor<?>cons[]=demo.getConstructors();
        for (int i = 0; i < cons.length; i++) {
            Class<?> p[]=cons[i].getParameterTypes();
            System.out.print("构造方法：  ");
            int mo=cons[i].getModifiers();
            System.out.print(Modifier.toString(mo)+" ");
            System.out.print(cons[i].getName());
            System.out.print("(");
            for(int j=0;j<p.length;++j){
                System.out.print(p[j].getName()+" arg"+i);
                if(j<p.length-1){
                    System.out.print(",");
                }
            }
            System.out.println("){}");
        }

    }

    @Test
    public void test8(){
        /*
        获取类方法
         */
        Class<?> demo=null;
        try{
            demo=Class.forName("com.code.reflect.Person");
        }catch (Exception e) {
            e.printStackTrace();
        }
        Method method[]=demo.getMethods();
        for(int i=0;i<method.length;++i){
            Class<?> returnType=method[i].getReturnType();
            Class<?> para[]=method[i].getParameterTypes();
            int temp=method[i].getModifiers();
            System.out.print(Modifier.toString(temp)+" ");
            System.out.print(returnType.getName()+"  ");
            System.out.print(method[i].getName()+" ");
            System.out.print("(");
            for(int j=0;j<para.length;++j){
                System.out.print(para[j].getName()+" "+"arg"+j);
                if(j<para.length-1){
                    System.out.print(",");
                }
            }
            Class<?> exce[]=method[i].getExceptionTypes();
            if(exce.length>0){
                System.out.print(") throws ");
                for(int k=0;k<exce.length;++k){
                    System.out.print(exce[k].getName()+" ");
                    if(k<exce.length-1){
                        System.out.print(",");
                    }
                }
            }else{
                System.out.print(")");
            }
            System.out.println();
        }
    }

    @Test
    public void test9(){
        Class<?> demo = null;
        try {
            demo = Class.forName("com.code.reflect.Person");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            //调用Person类中的sayChina方法
            Method method=demo.getMethod("toString");
            method.invoke(demo.newInstance());
            //调用Person的sayHello方法
            method=demo.getMethod("say");
            method.invoke(demo.newInstance(),"Rollen",20);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

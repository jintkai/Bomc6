package com.code.test.ibnmsConfig;

import org.testng.annotations.Test;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by jinkai on 2014/7/10.
 */
public class TT {
    public static void main(String[] str) throws ParserConfigurationException, IOException, SAXException {
        /*
        要读取一个xml文档，首先要有个DocumentBuilder,它可以通过
        DocumentBuilderFactory的newDocumentBuilder方法获取；
         */
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        /*
        现在可以从文件中读入某个文档
         */
        File f=new File("D:\\Test\\Bomc6\\src\\runTestConfig\\testng.xml");
        Document doc=builder.parse(f);
        /*
        或则，可以用一个URL
        URL u=......;
        Document doc=builder.parse(u);
        或则使用一个输入流,
        InputStream in=......;
        Document doc=builder.parse(in);*/
        /*
        Document对象是xml文档的树形结构在内存中的表现；
        getDocumentElement返回xml的根元素，getTagName则返回元素的标签名；
         */
        Element root= doc.getDocumentElement();
        System.out.println(root.getTagName());
        /*
        若想得到该元素的子元素，可以使用getChildNodes方法；
         */
        NodeList children=root.getChildNodes();
        for (int i=0;i<children.getLength();i++)
        {
            Node child=children.item(i);//Node中包含了空格
            if(child instanceof  Element)//此处主要禁止掉空白字符,否则将node转换成Element会报错，空白字符转换报错
            {
                Element ch=(Element) child;
                System.out.println(ch.getTagName());
                /*
                想得到元素的文本值，这些文本值放在test类型的子节点中，且text是唯一的，姑不用遍历，key使用getFirstChild
                ，如后用getData方法得到文本值；
                 */
                Text textnode=(Text)ch.getFirstChild();
                String test= textnode.getData().trim();
                System.out.println(test);
            }
        }
        /*
        节点属性的可以使用getAttributes方法，返回一个NameNodeMap对象，包含了描述属性的节点对象；
        通过getNodeName获取属性名称，getNodeValue获得属性值;
        若知道属性名，则可使用getAttribute来获取属性名的值
         */
        NamedNodeMap attributes=root.getAttributes();
        for (int i=0;i<attributes.getLength();i++)
        {
            Node attribute=attributes.item(i);
            System.out.println(attribute.getNodeName()+"，"+attribute.getNodeValue());
        }
        System.out.println(root.getAttribute("name"));


    }

    @Test
    public void t()
    {

        String str="共 100 页,1 2 3 4..";
        //split支持正则表达式,返回数组
        System.out.println(str.split(",")[0]);
        System.out.println(str.split(" ")[1]);
        System.out.println(str.split(" ")[2]);
        //采用indexOf
        int begin=str.indexOf(" ");
        int end=str.indexOf(" ",begin+1);
        System.out.println("substring:"+str.substring(begin+1,end));

        str="10-11-11";
        System.out.println("---:"+str.split("-")[0]);
        str="12";
        System.out.println("+++:"+str.split("-")[0]);

        Date now =new Date();
        System.out.println(System.currentTimeMillis());
    }
}

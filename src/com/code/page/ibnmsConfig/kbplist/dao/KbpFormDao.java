package com.code.page.ibnmsConfig.kbplist.dao;

import com.code.common.BaseDao;
import com.code.page.ibnmsConfig.kbplist.domain.KbpFormDomain;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 * Created by jon on 16/5/4.
 */
public class KbpFormDao  extends BaseDao{
    /*
    SqlSession session;

    public KbpFormDao() {
        SqlSessionFactory factory = null;
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.properties");
            factory = new SqlSessionFactoryBuilder().build(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }

        session = factory.openSession();

        //Worker worker = new Worker("003", "Join", 100);
        //session.insert("com.lubby.bean.addWorker", worker);
        //session.delete("com.lubby.bean.deleteWorkerById", "005");
        //session.commit();
        //List<Worker> list = session.selectList("com.lubby.bean.getAllWorker");
        /*System.out.println(session.selectOne("com.lubby.bean.getWorkerById", "001"));
        System.out.println(session.update("com.lubby.bean.updateWorker", ));
        System.out.println(session.delete("com.lubby.bean.deleteWorkerById", "004"));
        session.commit();
        System.out.println(session.selectList("com.lubby.bean.getWorkerByName", "J"));
        //System.out.println(list);
    }
    */
    @Test
    public KbpFormDomain selectKbp(String kbpClass){

        KbpFormDomain domain=this.session.selectOne("getKbpForm",kbpClass);
        System.out.println(domain.toString());
        return  domain;
    }
    @Test
    public KbpFormDomain selectKbpByRowNum(int num){
        System.out.println( session.selectOne("getKbpByRowNum",num));
        return  session.selectOne("getKbpByRowNum",num);
    }
    public List<KbpFormDomain> selectKbpList(KbpFormDomain domain){
        return session.selectList("getKbpList",domain);

    }
    public KbpFormDomain selectOneKbpByClass(){
        return session.selectOne("getOneKbpByClass");
    }
    public KbpFormDomain selectOneKbpByCaption(){
        return session.selectOne("getOneKbpByCaption");
    }

    /**
     * 获取所有一级KBP编号
     * @return
     */
    public List<String> selectKbpID(){
        return session.selectList("getKbpID");
    }
}

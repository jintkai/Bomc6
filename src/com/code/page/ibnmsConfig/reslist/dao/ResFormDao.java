package com.code.page.ibnmsConfig.reslist.dao;

import com.code.common.BaseDao;
import com.code.page.ibnmsConfig.reslist.domain.ResHostFormDomain;
import org.testng.annotations.Test;

/**
 * Created by jon on 16/5/18.
 */
public class ResFormDao extends BaseDao {

    @Test
    public ResHostFormDomain getHostFormByRowNum(int num){
        ResHostFormDomain domain=this.session.selectOne("getHostFormByRowNum",num);

         return domain;
    }
    @Test
    public void test()
    {
        System.out.print("1");
    }
}

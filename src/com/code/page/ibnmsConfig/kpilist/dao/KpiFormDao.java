package com.code.page.ibnmsConfig.kpilist.dao;

import com.code.common.BaseDao;
import com.code.page.ibnmsConfig.kpilist.domain.KpiFormDomain;

import java.util.List;

/**
 * Created by jon on 16/5/10.
 */
public class KpiFormDao extends BaseDao {
    public KpiFormDomain selectKpiByRowNum(int num)
    {
        return session.selectOne("getKpiByRowNum",num);
    }
    public List selectKpiList(KpiFormDomain domain){
        return session.selectList("getKpiList",domain);
    }
    public KpiFormDomain selectOneKpiByID(){
        return session.selectOne("getOneKpiById");
    }
}

package com.code.bnms.kpilist.dao;

import com.code.common.BaseDao;
import com.code.bnms.kpilist.domain.KpiFormDomain;

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

package com.code.itilManage.formConfig.Domain;

import java.util.Map;

import com.code.common.DBTools;
import com.code.common.Tools;

public class FormConfAdd {
	

	
    public DBTools dbTools=new DBTools();
    public Tools tools;//=new Tools(eventDriver);

    public Map<String,String> map;
	

		public String setelementname() {
			String elementname="新增";	
			return elementname;
		}
		
		public String setlength() {
			
			return "50";
		}
		
		public String setRow() {
			return "50";
		}
		
		public String setfunc_text() {
			return "文本框";
		}
		
	
		
		

}

package com.code.itilManage.formjsConfig.Domain;

public class FormjsDomain {
	private  String js_validate;
	private	 String func_param;
	private	 String js_relation;
	private  String js_init;
	private  String link_url;
	private  String elecomment;
	
	
	public void setfunc_param(String func_param) {
		this.func_param=func_param;	
	}
	
	public String getFUNC_PARAM() {
		return func_param;	
	}
	
	public void setjs_validate(String js_validate) {

		this.js_validate=js_validate;		
	}
	
	public String getjs_validate() {
		return js_validate;	
	}
	
	
	public void setjs_relation(String js_relation) {

		this.js_relation=js_relation;		
	}
	
	public String getjs_relation() {
		return js_relation;	
	}
	
	public void setJS_INIT(String js_init) {

		this.js_init=js_init;			
	}	
		
	public String getjs_init() {
		return js_init;	
	}
	
	public void setLINK_URL(String link_url) {

		this.link_url=link_url;		
	}
	
	
	public String getlink_url() {
		return link_url;	
	}
	
	public void setcomments(String elecomment) {

		this.elecomment=elecomment;		
	}

	
	public String getelecomment() {
		return elecomment;	
	}
}

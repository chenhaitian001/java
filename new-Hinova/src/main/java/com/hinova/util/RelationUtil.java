package com.hinova.util;

public class RelationUtil {
	public static String getRelation(String code){
		String relation = "";
		if(code.equals("1")){
			relation = "爸爸";
		}else if(code.equals("2")){
			relation = "妈妈";
		}else if(code.equals("3")){
			relation = "爷爷";
		}else if(code.equals("4")){
			relation = "奶奶";
		}else if(code.equals("5")){
			relation = "姥姥";
		}else if(code.equals("6")){
			relation = "姥爷";
		}else{
			relation = "其他亲属";
		}
		return relation;
	}
}

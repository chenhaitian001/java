package link.x86.util;

public class RelationUtil {
	public static String getRelation(String code){
		String relation = "";
		if(code.equals("爸爸")){
			relation = "1";
		}else if(code.equals("妈妈")){
			relation = "2";
		}else if(code.equals("爷爷")){
			relation = "3";
		}else if(code.equals("奶奶")){
			relation = "4";
		}else if(code.equals("姥姥")){
			relation = "5";
		}else if(code.equals("姥爷")){
			relation = "6";
		}else{
			relation = "7";
		}
		return relation;
	}
	public static String getSex(String codes){
		String code = "";
		if(code.equals("男")){
			code = "1";
		}else if(code.equals("女")){
			code = "2";
		}else{
			code = "3";
		}
		return code;
		
	}
}

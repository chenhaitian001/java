package com.utils;



import java.io.File;

import net.sf.json.JSONObject;
//人脸接口调用
public class FaceApi2 {
	
	
  public static void main(String[] args) {
	   // File file=new File("d://1214.jpg");
	    File file=new File("d://new1.png");//2e037eea-4c0d-4049-9b70-14a1fda6e793
		String getImageStr = base64Util.GetImageStr(file);//图片转base64数据
		//System.out.println(getImageStr);
		//File file2=new File("d://12.jpg");
		//base64Util2.GenerateImage(getImageStr, file2);
	    //File file = new File("D://1213.jpg");
		System.out.println(getImageStr);
		 
		
		//经典组合接口测试：   创建人脸组----->在人脸组中添加人脸------->搜索人脸组中某个人脸
		//createGroup("", "", "", getImageStr, "1234567890");//facegrp_token\":\"a4bfa59d-5a04-49af-9819-c21760249aa9
		addGroup("", "a4bfa59d-5a04-49af-9819-c21760249aa9", getImageStr, "", "1");//face_tokens\":[\"aa971a06-916f-4b6c-b308-517d165b2be5
		//search("", "a4bfa59d-5a04-49af-9819-c21760249aa9", getImageStr);
		
		
		//其他接口
		//deleteGroup("", "ada6d18e-b077-4c07-8c87-4a3de3ded52c");
		//existGroup("", "ada6d18e-b077-4c07-8c87-4a3de3ded52c");
		//removeface("", "ada6d18e-b077-4c07-8c87-4a3de3ded52c", new String[]{"16d6c1ad-f049-455a-9ca2-4d4692f11c17"});
		//queryGroup("", "ada6d18e-b077-4c07-8c87-4a3de3ded52c");
		//compare(getImageStr,"",getImageStr,"");
		//detect(getImageStr,"1");
  }
  
  //private static String baseurl="http://127.0.0.1:8080";//路径
  private static String baseurl="http://202.38.128.92:8080";//路径
  
  //2.1人脸检测接口
  public static JSONObject detect(String img,String face_cnt) {
	    //String url = "http://202.38.128.92:8080/face-server/face/detect";
	    String url =baseurl+ "/face-server/face/detect";
	    
	   //调用接口
  		JSONObject jSONObject=null;
  		try {
  			JSONObject params = new JSONObject();
  			//File file = new File("D://5.png");
  			params.put("face_cnt", face_cnt);
  			params.put("img", img);
  			//params.put("img", base64Util2.GetImageStr(file));
  			//params.put("img", base64Util2.getImageBase64String("D://5.jpg"));
  			jSONObject=ClientFaceUtil2.doPost(url, params);
  			String ret = jSONObject.toString();
  		    System.out.println(ret);
  		} catch (Exception e) {
  			throw new RuntimeException(e);
  		}
		return jSONObject;
	    //调用成功
	    //{"count":1,"faces":[{"face_token":"c4d7803a-8255-48e9-949b-ed20af8d5f0b","rect":{"x":87,"y":29,"width":105,"height":134,"roll":-12.878224,"pitch":2.468057,"yaw":-10.344731,"score":0.99971265}}]}
	    //服务端错误
	    //{"code":"Face4","developerMessage":"message:Face Not Detected in Image","message":"没有检测到人脸图像","moreInfo":"","requestId":"621ff57b-6f6a-4f94-bc7f-c2f9723b31f3","status":"451"}
  }
  //2.2人脸比对
  public static JSONObject compare(String face1,String face_token_1,String face2,String face_token_2) {
	    String url =baseurl+ "/face-server/face/compare";
		//调用接口
		JSONObject jSONret=null;
		try {
			JSONObject params = new JSONObject();
			params.put("face1", face1);
			//params.put("face_token_1", face_token_1);
			params.put("face2", face2);//省略
			//params.put("face_token_2", face_token_2);
			jSONret=ClientFaceUtil2.doPost(url, params);
			String ret = jSONret.toString();
  		    System.out.println(ret);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jSONret;
  }
  //2.3搜索人脸接口（已调通）
  public static JSONObject search(String facegrp_id,String facegrp_token,String base64file) {
	    String url =baseurl+ "/face-server/face/search";
	    
	    //File file=new File("d://chenhaitian.jpg");//2e037eea-4c0d-4049-9b70-14a1fda6e793
	//	 base64file = base64Util.GetImageStr(file);//图片转base64数据
	    
	    //调用接口
	  	JSONObject jSONret=null;
	    try {
			JSONObject params = new JSONObject();
			//File file = new File("D://5.jpg");
			//String facegrp_token="3ba6eebb-a351-49a0-9bcb-628ef5e10df7";
			params.put("facegrp_token", facegrp_token);
			params.put("facegrp_id", facegrp_id);
			params.put("img", base64file);
			jSONret=ClientFaceUtil2.doPost(url, params);
			String ret = jSONret.toString();
  		    System.out.println(ret);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jSONret;
	    //{"img":"base64","face_token":"43eb3b84-0755-4420-ab39-197114594567","sim":1,"face_id":"dfdfdfddggg22221222"}
  }
  
  //2.4.1创建人脸组接口
  public static JSONObject createGroup(String facegrp_id,String facegrp_name,String facegrp_desc
			,String img,String face_cnt) {
	    String url =baseurl+ "/face-server/face/group/create";
		//调用接口
		JSONObject jSONret=null;
		try {
			JSONObject params = new JSONObject();
			params.put("facegrp_id", facegrp_id);
			params.put("facegrp_name", facegrp_name);
			params.put("facegrp_desc", facegrp_desc);
			params.put("img", img);
			params.put("face_cnt", face_cnt);
			jSONret=ClientFaceUtil2.doPost(url, params);
			String ret = jSONret.toString();
  		    System.out.println(ret);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jSONret;
	   // {"facegrp_id":"","facegrp_name":"met","facegrp_token":"3ba6eebb-a351-49a0-9bcb-628ef5e10df7","face_count":0}
  }
  
  //2.4.2添加到人脸组接口
  public static JSONObject addGroup(String facegrp_id,String facegrp_token,String img
			,String face_id,String face_cnt) {
	    String url =baseurl+ "/face-server/face/group/addface";
		
	    //调用接口
	  	JSONObject jSONret=null;
	    try {
			JSONObject params = new JSONObject();
			params.put("facegrp_id", facegrp_id);
			params.put("facegrp_token", facegrp_token);
			//图片必须有人脸图形
			params.put("img",img);
			params.put("face_id",face_id);//每调一次 id要改变
			params.put("face_cnt", face_cnt);
			
			jSONret=ClientFaceUtil2.doPost(url, params);
			String ret = jSONret.toString();
  		    System.out.println(ret);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jSONret;
	    //{"facegrp_token":"3ba6eebb-a351-49a0-9bcb-628ef5e10df7","face_count":3,"face_added":1,"face_tokens":["9f7f3a13-837e-4f1d-8b5c-41d0201f970e"]}
        //face_count总数量
  }
  //2.4.3删除人脸组的人脸
  public static JSONObject removeface(String facegrp_id,String facegrp_token,String[] face_tokens) {
	    String url =baseurl+ "/face-server/face/group/removeface";
	    //调用接口
	  	JSONObject jSONret=null;
	    try {
			JSONObject params = new JSONObject();
			params.put("facegrp_id", facegrp_id);
			params.put("facegrp_token", facegrp_token);
			params.put("face_tokens",face_tokens);
			jSONret=ClientFaceUtil2.doPost(url, params);
			String ret = jSONret.toString();
  		    System.out.println(ret);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jSONret;
  }
  //2.4.4更新人脸组信息
  public static JSONObject updateGroup(String facegrp_id,String facegrp_token,String facegrp_name,String facegrp_desc) {
	    String url =baseurl+ "/face-server/face/group/update";
	    //调用接口
	  	JSONObject jSONret=null;
	    try {
			JSONObject params = new JSONObject();
			params.put("facegrp_id", facegrp_id);
			params.put("facegrp_token", facegrp_token);
			params.put("facegrp_name",facegrp_name);
			params.put("facegrp_desc",facegrp_desc);
			jSONret=ClientFaceUtil2.doPost(url, params);
			String ret = jSONret.toString();
  		    System.out.println(ret);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jSONret;
  }
  //2.4.4查询人脸组信息
  public static JSONObject queryGroup(String facegrp_id,String facegrp_token) {
	    String url =baseurl+ "/face-server/face/group/query";
	    //调用接口
	  	JSONObject jSONret=null;
	    try {
			JSONObject params = new JSONObject();
			params.put("facegrp_id", facegrp_id); 
			params.put("facegrp_token", facegrp_token);
			jSONret=ClientFaceUtil2.doPost(url, params);
			String ret = jSONret.toString();
  		    System.out.println(ret);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jSONret;
  }
  //2.4.5删除人脸组
  public static JSONObject deleteGroup(String facegrp_id,String facegrp_token) {
	    String url =baseurl+ "/face-server/face/group/delete";
	    //调用接口
	  	JSONObject jSONret=null;
	    try {
			JSONObject params = new JSONObject();
			params.put("facegrp_id", facegrp_id); 
			params.put("facegrp_token", facegrp_token);
			jSONret=ClientFaceUtil2.doPost(url, params);
			String ret = jSONret.toString();
  		    System.out.println(ret);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jSONret;
  }
  //2.4.5判断人脸组是否存在
  public static JSONObject existGroup(String facegrp_id,String facegrp_token) {
	    String url =baseurl+ "/face-server/face/group/exist";
	    //调用接口
	  	JSONObject jSONret=null;
	    try {
			JSONObject params = new JSONObject();
			params.put("facegrp_id", facegrp_id); 
			params.put("facegrp_token", facegrp_token);
			jSONret=ClientFaceUtil2.doPost(url, params);
			String ret = jSONret.toString();
  		    System.out.println(ret);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return jSONret;
  }
 
  
}

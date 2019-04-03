package com.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;



public class ClientFaceUtil2 {

	public static void main(String arg[]) throws Exception {

		String url = "http://202.38.128.92:8080/face-server/face/group/create";

		JSONObject params = new JSONObject();

		params.put("img", "");
		params.put("face_cnt", "1");
		params.put("facegrp_id", "");
		params.put("facegrp_name", "dd");
		params.put("facegrp_desc", "");

		String ret = doPost(url, params).toString();

		System.out.println(ret);
	}

	public static JSONObject doPost(String url, JSONObject json) {
		@SuppressWarnings({ "deprecation", "resource" })
		HttpClient client = new DefaultHttpClient();

		HttpPost post = new HttpPost(url);

		JSONObject response = null;

		try {
			StringEntity s = new StringEntity(json.toString());

			
			s.setContentEncoding("UTF-8");

			s.setContentType("application/json");
			post.setEntity(s);
			post.setHeader("AppToken", "eyJhbGciOiJSUzI1NiJ9.eyJTQ09QRSI6IiIsIkhFQURFUl9BQ0NfSUQiOiI4MjBmOGM5MC1iYzkxLTQxN2MtYTFkYi1jYmIwNDlmODM0MWMiLCJpc3MiOiJDb21pRmFjZV9TREtUb2tlbiJ9.ZSdfEOqrxCDOq4c_5s2ubIdZ228xiEPe2gfPLl8txUjKs2zCdsWqYgiiqVcK8ISFItRC8dqI6S-KyQEZvvBvqbfICSYYFpi0lxVYXIgCJQrbIsfylAQ1NKzbaAZNl8qtooNP1zMgxqMLz8liHlfqTIzNSFvOrTI3faaJAL-9dfM");
			
			HttpResponse res = client.execute(post);

			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(entity);
				ReturnStatus build = ReturnStatus.build(1, result);
				response = JSONObject.fromObject(JsonUtil.toJson(build));
				System.out.println("[人脸]调用成功");
			}else{
				HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(entity);
				ReturnStatus build = ReturnStatus.build(0, result);
				response = JSONObject.fromObject(JsonUtil.toJson(build));
				System.out.println("[人脸]服务端错误");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

}
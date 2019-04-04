package ruvs_wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;





/**
 * 获取地址、转换百度经纬度 工具类
 *
 */
public class map {


	/**
	* 根据提供的经纬度，代理地址、端口号和ak获取地址
	* @param longitude:经度
	* @param latitude：纬度
	* @param proxyAddress：代理地址(有的公司网络是有代理的，这时候如果不设置则会出现连接超时的异常)
	* @param proxyPort：代理端口
	* @param ak：秘钥
	* @return
	* @throws IOException
	*/
	public static String getAddress(String longitude, String latitude, String proxyAddress, String proxyPort, String ak) throws Exception { 
	URL url = new URL("http://api.map.baidu.com/geocoder/v2/?ak=" + ak + 
	"&location=" + latitude + "," + longitude + "&output=json"); 
	
	String object = getJSONObjectByUrl(url,proxyAddress,proxyPort);
	
	
	// 返回的json串格式 {"status":0,"result":{"location":{"lng":96.32298699999997,"lat":39.98342407140365},"formatted_address":"甘肃省酒泉市瓜州县","business":"","addressComponent":{"country":"中国","country_code":0,"province":"甘肃省","city":"酒泉市","district":"瓜州县","adcode":"620922","street":"","street_number":"","direction":"","distance":""},"pois":[],"poiRegions":[],"sematic_description":"","cityCode":37}}
	
	
	return object;
	}
	
	
	/**
	* 根据参数将非百度坐标转换为百度坐标，供getAddress方法使用
	* @param longitude
	* @param latitude
	* @param proxyAddress
	* @param proxyPort
	* @param ak
	* @return
	* @throws Exception
	*/
	public static String convertCoordinate(String longitude, String latitude, String proxyAddress, String proxyPort, String ak) throws Exception{
	
	URL url = new URL("http://api.map.baidu.com/geoconv/v1/?coords="+ longitude + "," + latitude +"&from=1&to=5&ak="+ak);
	
	String object = getJSONObjectByUrl(url,proxyAddress,proxyPort);
	
	
	//返回的json格式 {"status":0,"result":[{"x":114.23074952312,"y":29.57908262908}]}
	
	
	return object;
	
	}
	
	/**
	 * 根据不同的url获取不同的json串
	 * @param url
	 * @param proxyAddress
	 * @param proxyPort
	 * @return
	 * @throws Exception
	 */
	public static String getJSONObjectByUrl(URL url, String proxyAddress, String proxyPort) throws Exception{
	
	HttpURLConnection connection = null;
	//如果代理地址没有或是代理的端口号为0，则说明该网络不存在代理
	if("notFound".equals(proxyAddress) || "0".equals(proxyPort)){
	connection = (HttpURLConnection) url.openConnection();
	}else{
	@SuppressWarnings("static-access")
	Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyAddress, Integer.parseInt(proxyPort)));
	connection = (HttpURLConnection) url.openConnection(proxy);}
	
	connection.addRequestProperty("User-Agent", "Mozilla/4.0");
	/** 
	 * 然后把连接设为输出模式。URLConnection通常作为输入来使用，比如下载一个Web页。 
	 * 通过把URLConnection设为输出，你可以把数据向你个Web页传送。下面是如何做： 
	 */ 
	connection.setRequestMethod("POST");
	connection.setDoOutput(true);
	connection.setDoInput(true); 
	connection.setUseCaches(false);
	connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8"); 
	out.flush(); 
	out.close(); 
	//一旦发送成功，用以下方法就可以得到服务器的回应：  
	String res; 
	InputStream l_urlStream; 
	l_urlStream = connection.getInputStream(); 
	BufferedReader in = new BufferedReader(new InputStreamReader( 
	l_urlStream,"UTF-8")); 
	StringBuilder sb = new StringBuilder(""); 
	while ((res = in.readLine()) != null) { 
	sb.append(res.trim()); 
	} 
	String str = sb.toString(); 
	String obj = null;
	//StringUtils.isBlank(str)判断转化后的str是否为空字符串
		if(!isEmpty(str)){
			//将str转换为json
			Gson gson =new Gson();
			obj = gson.toJson(str);
		}
		return obj;

	}
	// 判断是否为空。
	 	public static  boolean isEmpty(String Value) {
	 			return (Value == null || Value.trim().equals("") || Value.trim().equals("null"));
	 	}
	 	
	 	
	 	
	 	public static void main(String[] args){
	 		
	 		

		 	

		 	String obj;
			try {
				 getposition("116.307175", "40.057098");
				//obj = map.getAddress("MjMuMTQwOTM3OTE1ODI4", "MTEzLjM2NDgwMjk1MjQx", "1", "0", "1");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	// json 内容为 {"status":0,"result":{"location":{"lng":96.32298699999997,"lat":39.98342407140365},"formatted_address":"甘肃省酒泉市瓜州县","business":"","addressComponent":{"country":"中国","country_code":0,"province":"甘肃省","city":"酒泉市","district":"瓜州县","adcode":"620922","street":"","street_number":"","direction":"","distance":""},"pois":[],"poiRegions":[],"sematic_description":"","cityCode":37}}
		 	
	 	}
	 	public  static void getposition(String latitude,String longitude) throws MalformedURLException{
	 	    BufferedReader in = null;
	 	    URL tirc = new URL("http://api.map.baidu.com/geocoder?location="+ latitude+","+longitude+"&output=json&key="+"E4805d16520de693a3fe707cdc962045");  
	 	         try {
	 	in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
	 	String res;  
	 	        StringBuilder sb = new StringBuilder("");  
	 	        while((res = in.readLine())!=null){  
	 	            sb.append(res.trim());  
	 	        }  
	 	        String str = sb.toString();
	 	        System.out.println(str);
	 	           
	 	} catch (UnsupportedEncodingException e) {
	 	e.printStackTrace();
	 	} catch (IOException e) {
	 	e.printStackTrace();
	 	}  
	 	         

	 	    }

	 	
	 	
	 	
}

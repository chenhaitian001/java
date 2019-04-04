package link.x86.util;

import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HTTP请求工具包
 */
public class HttpUtil {

    private static HttpUtil httpUtil = null;
    private HttpUtil(){}
    public static HttpUtil getInstance(){
        if(httpUtil == null){
            synchronized (HttpUtil.class) {
                if(httpUtil == null)
                    httpUtil = new HttpUtil();
            }
        }
        return httpUtil;
    }


    public String doGet(String uri){
        HttpGet httpGet = new HttpGet(uri);
        return sendHttpGet(httpGet);
    }

    /*
     * only one paramter's http get request
     */
    public String doGet(String uri, String paramName, String paramValue){
        HttpGet httpGet = new HttpGet(uri);
        //build get uri with params
        URIBuilder uriBuilder = new URIBuilder(httpGet.getURI()).setParameter(paramName, paramValue);
        try{
            httpGet.setURI(uriBuilder.build());
        }catch(URISyntaxException e){
            e.printStackTrace();
        }
        return sendHttpGet(httpGet);
    }

    /*
     * mulitple paramters of http get request
     */
    public String doGet(String uri, List<NameValuePair> parameters){
        HttpGet httpGet = new HttpGet(uri);
        String param = null;
        try{
            param = EntityUtils.toString(new UrlEncodedFormEntity(parameters));
            //build get uri with params
            httpGet.setURI(new URIBuilder(httpGet.getURI().toString() + "?" + param).build());
        }catch(Exception e){
            e.printStackTrace();
        }
        return sendHttpGet(httpGet);
    }


    public String doPost(String uri){
        HttpPost httpPost = new HttpPost(uri);
        return sendHttpPost(httpPost);
    }
    public String doPostJson(String uri,String json) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Content-type","application/json; charset=utf-8");

        httpPost.setHeader("Accept", "application/json");

        httpPost.setEntity(new StringEntity(json, "UTF-8"));
        return sendHttpPost(httpPost);
    }

    public String doPost(String uri, String reqXml){
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Content-Type", "application/xml");
        StringEntity entity = null;
        try{
            entity = new StringEntity(reqXml, "UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }

        httpPost.setEntity(entity);//http post with xml data
        return sendHttpPost(httpPost);
    }


    /*
     * multiple http put params
     */
    public String doPut(String uri, List<NameValuePair> parameters){
        HttpPut httpPut = new HttpPut(uri);
        String param = null;
        try{
            param = EntityUtils.toString(new UrlEncodedFormEntity(parameters));
            httpPut.setURI(new URIBuilder(httpPut.getURI().toString() + "?" + param).build());
        }catch(Exception e){
            e.printStackTrace();
        }
        return sendHttpPut(httpPut);
    }


    public String doPut(String uri, List<NameValuePair> parameters, String reqXml){
        HttpPut httpPut = new HttpPut(uri);
        String param = null;
        try{
            param = EntityUtils.toString(new UrlEncodedFormEntity(parameters));
            httpPut.setURI(new URIBuilder(httpPut.getURI().toString() + "?" + param).build());
        }catch(Exception e){
            e.printStackTrace();
        }

        StringEntity entity = null;
        try{
            entity = new StringEntity(reqXml, "UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }
        httpPut.setEntity(entity);

        return sendHttpPut(httpPut);
    }

    private String sendHttpPost(HttpPost httpPost){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try{
            httpClient = HttpClients.createDefault();
//			httpPost.setConfig(config);
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(response != null)
                    response.close();
                if(httpClient !=null)
                    httpClient.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return responseContent;
    }

    private String sendHttpGet(HttpGet httpGet){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try{
            httpClient = HttpClients.createDefault();
//			httpGet.setConfig(config);
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(response != null)
                    response.close();
                if(httpClient != null)
                    httpClient.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return responseContent;
    }


    private String sendHttpPut(HttpPut httpPut){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try{
            httpClient = HttpClients.createDefault();
//			httpPut.setConfig(config);
            response = httpClient.execute(httpPut);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }
        return responseContent;
    }
}

package com.software1411.niit.newapp.newapp.util;

import android.util.Log;

import com.software1411.niit.newapp.newapp.config.SystemConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/22.
 */
public class HttpUtil {
  public static void httpGetData_Get(final String surl){
      new Thread(new Runnable() {//==>Thread mythread =new Thread(new Runnable(){public void run(){}});mythread.start();
          HttpURLConnection httpURLConnection = null;
          @Override
          public void run() {
              //创建URL对象
              try {
                  URL url=new URL(surl);
                  //返回一个httpurlconnection对象，表示到url所引用的远程链接
                  httpURLConnection = (HttpURLConnection) url.openConnection();
                  //请求所需的apikey,api要求的固定写法
                  httpURLConnection.setRequestProperty("apikey", SystemConfig.BAIDU_APIKEY);
                  //设定请求方法，默认是get
                  httpURLConnection.setRequestMethod("GET");
                  //设置连接超时
                  httpURLConnection.setConnectTimeout(10000);
                  //设置读取数据的超时时间
                  httpURLConnection.setReadTimeout(5000);
                  //获取响应状态码
                  int responseCode=httpURLConnection.getResponseCode();
                  if (responseCode==200){
                      //定义一个输入流获取服务器返回的数据
                      InputStream is=httpURLConnection.getInputStream();
                      InputStreamReader isr=new InputStreamReader(is,"utf-8");//字节流转为字符流
                      BufferedReader br=new BufferedReader(isr);
                      /*//定义一个输出流将读取到的数据显示出来
                      OutputStream os=httpURLConnection.getOutputStream();
                      OutputStreamWriter osw=new OutputStreamWriter(os);
                      BufferedWriter bw=new BufferedWriter(osw);
                      bw.write(response);
                      bw.flush();//将缓存中的数据强制写出
                      */
                      //逐行遍历
                      String request="";//实际读取的内容
                      String response="";
                      while((request=br.readLine())!=null){
                          response+=request;
                          Log.d("==============>>>",response);
                          //打印的信息（==============>>>: {"showapi_res_code":0,"showapi_res_error":"","showapi_res_body":{"totalNum":44,"ret_code":0,"channelList":[{"channelId":"5572a108b3cdc86cf39001cd","name":"国内焦点"},{"channelId":"5572a108b3cdc86cf39001ce","name":"国际焦点"},{"channelId":"5572a108b3cdc86cf39001cf","name":"军事焦点"},{"channelId":"5572a108b3cdc86cf39001d0","name":"财经焦点"},{"channelId":"5572a108b3cdc86cf39001d1","name":"互联网焦点"},{"channelId":"5572a108b3cdc86cf39001d2","name":"房产焦点"},{"channelId":"5572a108b3cdc86cf39001d3","name":"汽车焦点"},{"channelId":"5572a108b3cdc86cf39001d4","name":"体育焦点"},{"channelId":"5572a108b3cdc86cf39001d5","name":"娱乐焦点"},{"channelId":"5572a108b3cdc86cf39001d6","name":"游戏焦点"},{"channelId":"5572a108b3cdc86cf39001d7","name":"教育焦点"},{"channelId":"5572a108b3cdc86cf39001d8","name":"女人焦点"},{"channelId":"5572a108b3cdc86cf39001d9","name":"科技焦点"},{"channelId":"5572a109b3cdc86cf39001da","name":"社会焦点"},{"channelId":"5572a109b3cdc86cf39001db","name":"国内最新"},{"channelId":"5572a109b3cdc86cf39001dc","name":"台湾最新"},{"channelId":"5572a109b3cdc86cf39001dd","name":"港澳最新"},{"channelId":"5572a109b3cdc86cf39001de","name":"国际最新"},{"channelId":"5572a109b3cdc86cf39001df","name":"军事最新"},{"channelId":"5572a109b3cdc86cf39001e0","name":"财经最新"},{"channelId":"5572a109b3cdc86cf39001e1","name":"理财最新"},{"channelId":"5572a109b3cdc86cf39001e2","name":"宏观经济最新"},{"channelId":"5572a109b3cdc86cf39001e3","name":"互联网最新"},{"channelId":"5572a109b3cdc86cf39001e4","name":"房产最新"},{"channelId":"5572a109b3cdc86cf39001e5","name":"汽车最新"},{"channelId":"5572a109b3cdc86cf39001e6","name":"体育最新"},{"channelId":"5572a10ab3cdc86cf39001e7","name":"国际足球最新"},{"channelId":"5572a10ab3cdc86cf39001e8","name":"国内足球最新"},{"channelId":"5572a10ab3cdc86cf39001e9","name":"CBA最新"},{"channelId":"5572a10ab3cdc86cf39001ea","name":"综合体育最新"},{"channelId":"5572a10ab3cdc86cf39001eb","name":"娱乐最新"},{"channelId":"5572a10ab3cdc86cf39001ec","name":"电影最新"},{"channelId":"5572a10ab3cdc86cf39001ed","name":"电视最新"},{"channelId":"5572a10ab3cdc86cf39001ee","name":"游戏最新"},{"channelId":"5572a10ab3cdc86cf39001ef","name":"教育最新"},{"channelId":"5572a10ab3cdc86cf39001f0","name":"女人最新"},{"channelId":"5572a10ab3cdc86cf39001f1","name":"美容护肤最新"},{"channelId":"5572a10ab3cdc86cf39001f2","name":"情感两性最新"},{"channelId":"5572a10ab3cdc86cf39001f3","name":"健康养生最新"},{"channelId":"5572a10ab3cdc86cf39001f4","name":"科技最新"},{"channelId":"5572a10bb3cdc86cf39001f5","name":"数码最新"},{"channelId":"5572a10bb3cdc86cf39001f6","name":"电脑最新"},{"channelId":"5572a10bb3cdc86cf39001f7","name":"科普最新"},{"channelId":"5572a10bb3cdc86cf39001f8","name":"社会最新"}]}}）
                      //创建JSONObject对象解析从网络读取的数据
                          try {
                              JSONObject jsonObject=new JSONObject(response);
                              int showapi_res_code=jsonObject.getInt("showapi_res_code");
                              if(showapi_res_code==0){
                                  JSONObject showapi_res_body=jsonObject.getJSONObject("showapi_res_body");//获取showapi_res_body的json对象
                                  JSONArray channelList=showapi_res_body.getJSONArray("channelList");
                                  for (int i=0;i<channelList.length();i++){
                                      JSONObject channel=channelList.getJSONObject(i);
                                      String channelId=channel.getString("channelId");
                                      String name=channel.getString("name");
                                      Log.i("JSON","第"+(i+1)+"个频道的ID为："+channelId+",频道的名字为："+name);
                                  }
                              }
                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                      }

                      br.close();
                      isr.close();
                      is.close();
                  }
              } catch (MalformedURLException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }finally {
                  if (httpURLConnection!=null){
                      httpURLConnection.disconnect();
                  }
              }
          }
      }).start();
  }
}

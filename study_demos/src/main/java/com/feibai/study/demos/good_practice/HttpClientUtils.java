package com.feibai.study.demos.good_practice;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HttpClientUtils {

  private String uri;

  public static CloseableHttpClient acceptsUnstrustCertsHttpClient() {
    try {
      HttpClientBuilder b = HttpClientBuilder.create();

      //setup a trust stratey that allows all certificates
      SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
      sslContextBuilder.loadTrustMaterial(null, new TrustStrategy() {
        @Override
        public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
          return true;
        }

      });
      sslContextBuilder.useProtocol("TLSv1.2");
      SSLContext sslContext = sslContextBuilder.build();

      b.setSSLContext(sslContext);

      HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
      SSLConnectionSocketFactory sslScocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
      Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http",
              PlainConnectionSocketFactory.getSocketFactory()).register("https", sslScocketFactory).build();

      //Now, we create connection-manager using our Registry
      PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
      connMgr.setMaxTotal(8);
      connMgr.setDefaultMaxPerRoute(4);
      b.setConnectionManager(connMgr);

      //finally,build the httpClient.
      CloseableHttpClient client = b.build();
      return client;

    } catch (Exception e) {
      return null;
    }
  }

  /*
   *
   * 同步get方式的请求 其中 uri 是请求的地址如：http://www.baidu.com 主要http不能省略，否则会报 没有指明协议 的错误
   * 如果需要带数据 则以uri?a=sss 形式即可
   * */
  public void doGet() throws ClientProtocolException, IOException {
    //创建CloseableHttpClient
    HttpClientBuilder builder = HttpClientBuilder.create();
    CloseableHttpClient client = builder.build();
    //执行
    HttpUriRequest httpGet = new HttpGet(uri);
    CloseableHttpResponse response = client.execute(httpGet);
    HttpEntity entity = response.getEntity();
    if (entity != null) {
      String entityStr = EntityUtils.toString(entity, "utf-8");
      System.out.println(entityStr);
    }
    //System.out.println(response.toString());
  }


  /*
   * 同步post请求方式：请求中需要带的数据通过httpPost.setEntity(new StringEntity("beppe", "UTF-8"));的方式，
   * 如果需要带的数据是对象的形式，则转化为json字符串格式
   * */
  public void doPost() throws ClientProtocolException, IOException {
    HttpClientBuilder builder = HttpClientBuilder.create();
    CloseableHttpClient client = builder.build();
    HttpPost httpPost = new HttpPost(uri);
    httpPost.setEntity(new StringEntity("beppe", "UTF-8"));
    CloseableHttpResponse response = client.execute(httpPost);
    HttpEntity entity = response.getEntity();
    if (entity != null) {
      String entityStr = EntityUtils.toString(entity, "utf-8");
      System.out.println(entityStr);
    }
    //System.out.println(response.toString());
  }


  public void doGetAsyn() throws InterruptedException, ExecutionException {
    CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
    //开启httpclient
    httpclient.start();
    //开始执行
    HttpGet httpGet = new HttpGet(uri);
    Future<HttpResponse> future = httpclient.execute(httpGet, null);
    HttpResponse httpResponse = future.get();
    System.out.println(httpResponse.getStatusLine() + "===" + httpGet.getRequestLine());
  }


  /*
   *
   * 异步的post方式请求：其中可以在回调函数中加入自己的业务逻辑
   * */
  public static void doPostAsyn(String url, String outStr) throws ParseException, IOException, InterruptedException, ExecutionException {
    CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.createDefault();
    httpAsyncClient.start();
    HttpPost httpost = new HttpPost(url);
    //httpost.addHeader(HTTP.CONTENT_TYPE, "application/json");
    StringEntity se = new StringEntity(outStr, "UTF-8");
    se.setContentType("application/json");
    se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
    httpost.setEntity(se);
    Future<HttpResponse> future = httpAsyncClient.execute(httpost, null);
    System.out.println(future.get().toString());
    //String result = EntityUtils.toString(response.getEntity(),"UTF-8");
    //jsonObject = JSONObject.fromObject(result);
  }

}

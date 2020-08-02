package com.feibai.study.demos.good_practice;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

public class RestClientUtils {
  private static RestTemplate restTemplate;
  private static RestTemplate restTemplateInsecure;

  static {
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    messageConverters.add(new FormHttpMessageConverter());
    messageConverters.add(new MappingJackson2HttpMessageConverter());

    CloseableHttpClient httpClient = HttpClientUtils.acceptsUnstrustCertsHttpClient();
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
            httpClient);

    restTemplate = new RestTemplate(messageConverters);
    restTemplate.setRequestFactory(clientHttpRequestFactory);
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

    restTemplateInsecure = new RestTemplate(messageConverters);
  }

  private RestClientUtils() {

  }

  public static RestTemplate getClient() {
    return restTemplate;
  }

  public static RestTemplate getClientInsecure() {
    return restTemplateInsecure;
  }

}
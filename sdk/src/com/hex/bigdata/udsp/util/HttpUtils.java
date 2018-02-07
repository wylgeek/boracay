package com.hex.bigdata.udsp.util;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * Created by JunjieM on 2017-12-11.
 */
public class HttpUtils {

    private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

    public static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    public static final String TEXT_JSON = "text/json;charset=UTF-8";
    public static final String TEXT_HTML = "text/html;charset=UTF-8";
    public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded;charset=UTF-8";

    public static int connectionRequestTimeout = 30000; // 链接请求超时时间（毫秒）
    public static int connectTimeout = 30000; // 链接超时时间（毫秒）
    public static int socketTimeout = 20000; // Socket超时时间（毫秒）
    public static int readTimeout = 10000; // 读取超时时间（毫秒）
    public static int waitTimeout = 60000; // 获取链接的最大等待时间（毫秒）
    public static int maxTotalpool = 400; // 总体的最大链接数
    public static int maxPerRoute = 100; // 单连接池的最大链接数
    public static PoolingHttpClientConnectionManager poolManager; // HTTP链接管理器
    public static CookieStore cookieStore = new BasicCookieStore();

    static {
        try {
            // 信任对方所有证书
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build());

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslConnectionSocketFactory).build();

            poolManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            poolManager.setMaxTotal(maxTotalpool);
            poolManager.setDefaultMaxPerRoute(maxPerRoute);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public static CloseableHttpClient getConnection() {
        return getConnection(connectionRequestTimeout, connectTimeout, socketTimeout);
    }

    public static CloseableHttpClient getConnection(int connectionRequestTimeout,
                                                    int connectTimeout, int socketTimeout) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout).build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .setConnectionManager(poolManager)
                .setDefaultRequestConfig(requestConfig).build();
        return httpClient;
    }

    /**
     * 请求Spring Security登录
     *
     * @param url
     * @param params
     * @return
     */
    public static HttpResponse requestSpringSecurityLogin(String url, List<NameValuePair> params,
                                                          Map<String, String> headers) {
        return requestSpringSecurityLogin(url, params, headers, Charset.forName("UTF-8"));
    }

    /**
     * 请求Spring Security登录
     *
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public static HttpResponse requestSpringSecurityLogin(String url, List<NameValuePair> params,
                                                          Map<String, String> headers, Charset charset) {
        LOG.info("url:" + url);
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        HttpResponse response = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED);
            if (headers != null && headers.size() >= 1) {
                for (String key : headers.keySet()) {
                    httpPost.setHeader(key, headers.get(key));
                }
            }
            String reqStr = URLEncodedUtils.format(params, charset);
            StringEntity stringEntity = new StringEntity(reqStr, charset);
            stringEntity.setContentType(APPLICATION_X_WWW_FORM_URLENCODED);
            httpPost.setEntity(stringEntity);
            httpClient = HttpUtils.getConnection();
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 这里不能关闭httpGet
//        finally {
//            if (httpGet != null) {
//                httpGet.releaseConnection();
//            }
//        }
        return response;
    }

    /**
     * 请求页面HTML
     *
     * @param url
     * @param params
     * @return
     */
    public static String requestPageHtml(String url, List<NameValuePair> params, Map<String, String> headers) {
        return requestPageHtml(url, params, headers, Charset.forName("UTF-8"));
    }

    /**
     * 请求页面HTML
     *
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public static String requestPageHtml(String url, List<NameValuePair> params,
                                         Map<String, String> headers, Charset charset) {
        HttpResponse response = requestPageResponse(url, params, headers, charset);
        return analysisContent(response);
    }

    /**
     * 请求页面Response
     *
     * @param url
     * @param params
     * @return
     */
    public static HttpResponse requestPageResponse(String url, List<NameValuePair> params,
                                                   Map<String, String> headers) {
        return requestPageResponse(url, params, headers, Charset.forName("UTF-8"));
    }

    /**
     * 请求页面Response
     *
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public static HttpResponse requestPageResponse(String url, List<NameValuePair> params,
                                                   Map<String, String> headers, Charset charset) {
        LOG.info("url:" + url);
        HttpGet httpGet = null;
        CloseableHttpClient httpClient = null;
        HttpResponse response = null;
        String uri = url;
        if (params != null && params.size() >= 1) {
            String reqStr = URLEncodedUtils.format(params, charset);
            LOG.info("params:" + reqStr);
            uri += "?" + reqStr;
        }
        try {
            httpGet = new HttpGet(uri);
            httpGet.addHeader(HTTP.CONTENT_TYPE, TEXT_HTML);
            if (headers != null && headers.size() >= 1) {
                for (String key : headers.keySet()) {
                    httpGet.setHeader(key, headers.get(key));
                }
            }
            httpClient = getConnection();
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 这里不能关闭httpGet
//        finally {
//            if (httpGet != null) {
//                httpGet.releaseConnection();
//            }
//        }
        return response;
    }

    /**
     * GET请求
     *
     * @param url
     * @param params
     * @param clazz
     * @param username
     * @param password
     * @param <T>
     * @return
     */
    public static <T> T requestGet(String url, List<NameValuePair> params, Map<String, String> headers,
                                   Class<T> clazz, String username, String password) {
        return requestGet(url, params, headers, clazz, Charset.forName("UTF-8"), username, password);
    }

    /**
     * GET请求
     *
     * @param params
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T requestGet(String url, List<NameValuePair> params, Map<String, String> headers, Class<T> clazz) {
        return requestGet(url, params, headers, clazz, Charset.forName("UTF-8"), null, null);
    }

    /**
     * PUT请求
     *
     * @param url
     * @param json
     * @param headers
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T requestPut(String url, String json, Map<String, String> headers,
                                   Class<T> clazz) {
        return requestPut(url, json, headers, clazz, Charset.forName("UTF-8"), null, null);
    }

    /**
     * PUT请求
     *
     * @param url
     * @param json
     * @param headers
     * @param clazz
     * @param username
     * @param password
     * @param <T>
     * @return
     */
    public static <T> T requestPut(String url, String json, Map<String, String> headers,
                                   Class<T> clazz, String username, String password) {
        return requestPut(url, json, headers, clazz, Charset.forName("UTF-8"), username, password);
    }

    /**
     * PUT请求
     *
     * @param url
     * @param json
     * @param headers
     * @param clazz
     * @param charset
     * @param username
     * @param password
     * @param <T>
     * @return
     */
    public static <T> T requestPut(String url, String json, Map<String, String> headers,
                                   Class<T> clazz, Charset charset, String username, String password) {
        LOG.info("url:" + url);
        LOG.info("request:" + json);
        HttpPut httpPut = null;
        CloseableHttpClient httpClient = null;
        T responseObject = null;
        try {
            httpPut = new HttpPut(url);
            httpPut.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            if (headers != null && headers.size() >= 1) {
                for (String key : headers.keySet()) {
                    httpPut.setHeader(key, headers.get(key));
                }
            }
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                String auth = "Basic " + Base64.encode((username + ":" + password).getBytes());
                httpPut.addHeader("Authorization", auth);
            }
            StringEntity stringEntity = new StringEntity(json, charset);
            stringEntity.setContentType(APPLICATION_JSON);
            httpPut.setEntity(stringEntity);
            httpClient = getConnection();
            HttpResponse response = httpClient.execute(httpPut);
            String content = analysisJsonContent(response);
            responseObject = JSON.parseObject(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpPut != null) {
                httpPut.releaseConnection();
            }
        }
        return responseObject;
    }

    /**
     * GET请求
     *
     * @param url
     * @param params
     * @param clazz
     * @param charset
     * @param username
     * @param password
     * @param <T>
     * @return
     */
    public static <T> T requestGet(String url, List<NameValuePair> params, Map<String, String> headers,
                                   Class<T> clazz, Charset charset, String username, String password) {
        LOG.info("url:" + url);
        HttpGet httpGet = null;
        CloseableHttpClient httpClient = null;
        T responseObject = null;
        String uri = url;
        if (params != null && params.size() >= 1) {
            String reqStr = URLEncodedUtils.format(params, charset);
            LOG.info("params:" + reqStr);
            uri += "?" + reqStr;
        }
        //System.out.println("uri:" + uri);
        try {
            httpGet = new HttpGet(uri);
            if (headers != null && headers.size() >= 1) {
                for (String key : headers.keySet()) {
                    httpGet.setHeader(key, headers.get(key));
                }
            }
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                String auth = "Basic " + Base64.encode((username + ":" + password).getBytes());
                httpGet.addHeader("Authorization", auth);
            }
            httpClient = getConnection();
            HttpResponse response = httpClient.execute(httpGet);
            //System.out.println("response:" + response.toString());
            String content = analysisJsonContent(response);
            responseObject = JSON.parseObject(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }
        return responseObject;
    }

    /**
     * POST请求
     *
     * @param url
     * @param json
     * @param clazz
     * @param username
     * @param password
     * @param <T>
     * @return
     */
    public static <T> T requestPost(String url, String json, Map<String, String> headers,
                                    Class<T> clazz, String username, String password) {
        return requestPost(url, json, headers, clazz, Charset.forName("UTF-8"), username, password);
    }

    /**
     * POST请求
     *
     * @param json
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T requestPost(String url, String json, Map<String, String> headers, Class<T> clazz) {
        return requestPost(url, json, headers, clazz, Charset.forName("UTF-8"), null, null);
    }

    /**
     * POST请求
     *
     * @param json
     * @param url
     * @param clazz
     * @param charset
     * @param <T>
     * @return
     */
    public static <T> T requestPost(String url, String json, Map<String, String> headers,
                                    Class<T> clazz, Charset charset, String username, String password) {
        LOG.info("url:" + url);
        LOG.info("request:" + json);
        HttpPost httpPost = null;
        CloseableHttpClient httpClient = null;
        T responseObject = null;
        try {
            httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
            if (headers != null && headers.size() >= 1) {
                for (String key : headers.keySet()) {
                    httpPost.setHeader(key, headers.get(key));
                }
            }
            if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
                String auth = "Basic " + Base64.encode((username + ":" + password).getBytes());
                httpPost.addHeader("Authorization", auth);
            }
            StringEntity stringEntity = new StringEntity(json, charset);
            stringEntity.setContentType(APPLICATION_JSON);
            httpPost.setEntity(stringEntity);
            httpClient = getConnection();
            HttpResponse response = httpClient.execute(httpPost);
            String content = analysisJsonContent(response);
            responseObject = JSON.parseObject(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return responseObject;
    }

    /**
     * 获取Response JSON内容
     *
     * @param response
     * @return
     */
    public static String analysisJsonContent(HttpResponse response) {
        return analysisJsonContent(response, Charset.forName("UTF-8"));
    }

    /**
     * 获取Response JSON内容
     *
     * @param response
     * @return
     */
    public static String analysisJsonContent(HttpResponse response, Charset charset) {
        String content = analysisContent(response, charset);
        if (StringUtils.isNotBlank(content)) {
            content = StringEscapeUtils.unescapeJava(content);
        }
        return content;
    }

    /**
     * 获取Response内容
     *
     * @param response
     * @return
     */
    public static String analysisContent(HttpResponse response) {
        return analysisContent(response, Charset.forName("UTF-8"));
    }

    /**
     * 获取Response内容
     *
     * @param response
     * @param charset
     * @return
     */
    public static String analysisContent(HttpResponse response, Charset charset) {
        if (response == null) return null;
        String content = "";
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_NOT_FOUND) {
            throw new RuntimeException("[404]请检查URL!");
        } else if (statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            throw new RuntimeException("[500]GRMP内部错误!");
        } else if (statusCode == HttpStatus.SC_BAD_REQUEST) {
            throw new RuntimeException("[400]错误的请求!");
        } else {
            InputStream is = null;
            BufferedReader br = null;
            StringBuffer sb = new StringBuffer();
            HttpEntity entity = response.getEntity();
            try {
                is = entity.getContent();
                br = new BufferedReader(new InputStreamReader(is, charset));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                content = sb.toString();
            } catch (Exception e) {
                throw new RuntimeException("解析返回结果失败!" + e.toString());
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return content;
        }
    }

}

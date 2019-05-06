package org.oasis.springbootdemo.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author tianbo
 * @date 2019-04-30
 */
public class HttpClientUtils {
    // 超时时间为5s
    private static final int TIMEOUT = 5000;
    // 连接池的最大连接数
    private static final int MAX_TOTAL = 100;
    // 每个连接的最大路由数
    private static final int DEFAULT_MAX_PER_ROUTE = 100;

    private static CloseableHttpClient httpClient;

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    static {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_TOTAL);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(TIMEOUT).build();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(TIMEOUT)
                .setSocketTimeout(TIMEOUT)
                .setConnectionRequestTimeout(TIMEOUT).build();
        httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig
                (requestConfig).setDefaultSocketConfig(socketConfig).build();
    }

    public static String doGet(String url) {
        HttpGet httpGet = new HttpGet(buildUrl(url, null));
        return execute(httpGet);
    }

    public static String doGet(String url, Map<String, String> params) {
        String buildUrl = buildUrl(url, params);
        return doGet(buildUrl);
    }

    public static String doGet(String url, Map<String, String> headers, Map<String, String> params) {
        String buildUrl = buildUrl(url, params);
        HttpGet httpGet = new HttpGet(buildUrl);
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpGet.addHeader(key, headers.get(key));
            }
        }
        return execute(httpGet);
    }

    public static String doPost(String url) {
        System.out.println(url);
        HttpPost httpPost = new HttpPost(url);
        return execute(httpPost);
    }

    public static String doPost(String url, Map<String, String> params) {
        String buildUrl = buildUrl(url, params);
        return doPost(buildUrl);
    }

    public static String doPost(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return execute(httpPost);
    }

    public static String doPost(String url, Map<String, String> headers, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, String> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), param.getValue());
        }
        ArrayList<NameValuePair> pairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        String format = URLEncodedUtils.format(pairs, "UTF-8");
        httpPost.setEntity(new StringEntity(format, "UTF-8"));
        return execute(httpPost);
    }

    public static String doPost(String url, Map<String, String> headers, String json) {
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, String> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), param.getValue());
        }

        StringEntity entity = new StringEntity(json, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        return execute(httpPost);
    }

    private static String execute(HttpRequestBase httpRequest) {
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpRequest);
            if (response != null) {
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    return StringUtils.EMPTY;
                }
                return EntityUtils.toString(entity, "UTF-8");
            }
            return StringUtils.EMPTY;
        } catch (Exception ex) {
            logger.warn("execute http request failed, raw request:{}", httpRequest);
            EntityUtils.consumeQuietly(response.getEntity());
            return StringUtils.EMPTY;
        } finally {
            // 记得关闭response
            IOUtils.closeQuietly(response);
        }
    }

    private static String buildUrl(String url, Map<String, String> params) {
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            return uriBuilder.build().toString();
        } catch (Exception ex) {
            logger.warn("build url failed", ex);
            return StringUtils.EMPTY;
        }
    }
}

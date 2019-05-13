package net.fantesy84.common.util.httpclient;

import net.fantesy84.common.util.json.JacksonUtils;
import org.apache.http.*;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.*;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 数据供应方API访问工具类
 * <p>
 * 本工具仅用于访问各种后端的数据源API
 * </p>
 * <p>
 * 可配置的系统参数(在虚拟机参数中使用-D[参数名]=[值]指定)为包括:
 * <ul>
 * <li>ReqMaxHeaderCount - 请求头部属性个数最大值, 默认100个</li>
 * <li>ReqMaxLineLength - 请求内容单行最大长度, 默认3000行</li>
 * <li>ConnPoolMax - 连接池最大连接数, 默认最大400个</li>
 * <li>ConnMaxPreRoute - 每个route(通常一个域名一个route)可分配到的最大连接数, 默认2个</li>
 * <li>ConnectTimeoutMS - 与目标服务器三次握手的超时时间(单位:毫秒), 默认1s</li>
 * <li>SocketTimeoutMS - 传输过程中数据包的间隔超时时间(单位:毫秒),间隔时间并不等于整个请求响应的总时长, 默认15s</li>
 * <li>ConnectionRequestTimeoutMS - 从连接池获取连接的超时时间(单位:毫秒), 默认20s</li>
 * </ul>
 * </p>
 *
 * @author Andronicus.Ge
 * @since 1.0.0.RELEASE
 */
public class ApacheHttpClientUtils {
    private static final Logger LOGGER = Logger.getLogger(ApacheHttpClientUtils.class.getName());
    private static final String DEFAULT_REQ_MAX_HEADER_COUNT = "100";
    private static final String DEFAULT_REQ_MAX_LINE_LENGTH = "3000";
    private static final String DEFAULT_CONN_POOL_MAX = "400";
    private static final String DEFAULT_CONN_MAX_PRE_ROUTE = "40";
    private static final String DEFAULT_CONNECT_TIMEOUT_MS = "1000";
    private static final String DEFAULT_SOCKET_TIMEOUT_MS = "15000";
    private static final String DEFAULT_CONNECTION_REQUEST_TIMEOUT_MS = "20000";

    private static ApacheHttpClientUtils inst;
    private HttpClient httpClient;
    private PoolingHttpClientConnectionManager connManager;
    private CredentialsProvider credentialsProvider;
    private RequestConfig config;

    private ApacheHttpClientUtils() {
        long start = System.currentTimeMillis();
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            sslContext = SSLContexts.createSystemDefault();
        }
        //跳过了SSL的认证过程,如果需要的话,请修改本段代码
        TrustManager skipTrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        try {
            sslContext.init(null, new TrustManager[]{skipTrustManager}, null);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
        SSLConnectionSocketFactory skipSSLConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", skipSSLConnectionSocketFactory)
                .build();
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory = ManagedHttpClientConnectionFactory.INSTANCE;
        //连接池设置
        this.connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry,
                connectionFactory,
                null,
                null,
                60L,
                TimeUnit.SECONDS);
        connManager.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());
        connManager.setValidateAfterInactivity(3000);
        MessageConstraints messageConstraints = MessageConstraints.custom()
                .setMaxHeaderCount(Integer.parseInt(System.getProperty("ReqMaxHeaderCount", DEFAULT_REQ_MAX_HEADER_COUNT)))
                .setMaxLineLength(Integer.parseInt(System.getProperty("ReqMaxLineLength", DEFAULT_REQ_MAX_LINE_LENGTH)))
                .build();
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8)
                .setMessageConstraints(messageConstraints)
                .build();
        connManager.setDefaultConnectionConfig(connectionConfig);
        connManager.setMaxTotal(Integer.parseInt(System.getProperty("ConnPoolMax", DEFAULT_CONN_POOL_MAX)));
        connManager.setDefaultMaxPerRoute(Integer.parseInt(System.getProperty("ConnMaxPreRoute", DEFAULT_CONN_MAX_PRE_ROUTE)));
        //访问凭证设置
        this.credentialsProvider = new BasicCredentialsProvider();
        //请求设置
        this.config = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Collections.singletonList(AuthSchemes.BASIC))
                //三次握手时间(ms:毫秒)
                .setConnectTimeout(Integer.parseInt(System.getProperty("ConnectTimeoutMS", DEFAULT_CONNECT_TIMEOUT_MS)))
                //数据传输过程中数据包之间间隔的最大时间(ms:毫秒)
                .setSocketTimeout(Integer.parseInt(System.getProperty("SocketTimeoutMS", DEFAULT_SOCKET_TIMEOUT_MS)))
                //从ConnectionManager那里获取Connection的时间(ms:毫秒)
                .setConnectionRequestTimeout(Integer.parseInt(System.getProperty("ConnectionRequestTimeoutMS", DEFAULT_CONNECTION_REQUEST_TIMEOUT_MS)))
                .setContentCompressionEnabled(true)
                .build();
        //应用配置
        this.httpClient = HttpClients.custom()
                .setConnectionManagerShared(false)
                .setConnectionManager(connManager)
                .setDefaultCredentialsProvider(credentialsProvider)
                .setDefaultRequestConfig(config)
                .build();
        long end = System.currentTimeMillis();
        LOGGER.finest("初始化耗时:" + (end - start) + "毫秒");
    }

    public static ApacheHttpClientUtils getInst() {
        if (inst == null) {
            synchronized (ApacheHttpClientUtils.class) {
                if (inst == null) {
                    inst = new ApacheHttpClientUtils();
                }
            }
        }
        return inst;
    }

    /**
     * 使用指定路径发起GET请求
     *
     * @param url         指定的路径
     * @param queryString 查询字符串
     * @return 响应字符串
     */
    public String doGet(String url, String queryString) {
        return doGet(url, null, null, queryString);
    }

    /**
     * 使用指定路径发起GET请求
     *
     * @param url          指定的路径
     * @param urlParamsMap URL参数Map
     * @return 响应字符串
     */
    public String doGet(String url, Map<String, String> urlParamsMap) {
        return doGet(url, null, urlParamsMap, null);
    }

    /**
     * 使用指定路径发起GET请求
     *
     * @param url         指定的路径
     * @param headerMap   请求头Map
     * @param queryString URL参数Map
     * @return 响应字符串
     */
    public String doGet(String url, Map<String, String> headerMap, String queryString) {
        return doGet(url, headerMap, null, queryString);
    }

    /**
     * 使用指定路径发起GET请求
     *
     * @param url          指定的路径
     * @param headerMap    请求头Map
     * @param urlParamsMap URL参数Map
     * @return 响应字符串
     */
    public String doGet(String url, Map<String, String> headerMap, Map<String, String> urlParamsMap) {
        return doGet(url, headerMap, urlParamsMap, null);
    }

    /**
     * 使用指定路径发起GET请求
     *
     * @param url             指定的路径
     * @param headerMap       请求头Map
     * @param urlParamsMap    URL参数Map
     * @param custQueryString 自定义查询字符串
     * @return 响应字符串
     */
    public String doGet(String url, Map<String, String> headerMap, Map<String, String> urlParamsMap, String custQueryString) {
        try {
            HttpGet get = headerAndUrlParamsHandler(HttpMethods.GET, url, headerMap, urlParamsMap, custQueryString);
            Objects.requireNonNull(get, "HttpRequest对象必须被实例化!");
            return execute(get);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用指定路径发起POST请求
     *
     * @param url 指定的路径
     * @param obj 该参数分为3种情况:
     *            <ul>
     *            <li>字符串,将直接放入body中</li>
     *            <li>Map集合, 将使用{@link UrlEncodedFormEntity}处理为form表单</li>
     *            <li>其他的对象, 将使用{@link JacksonUtils}处理为JSON字符串后放入body中</li>
     *            </ul>
     * @return 响应字符串
     */
    public String doPost(String url, Object obj) {
        return doPost(url, null, null, null, obj);
    }

    /**
     * 使用指定路径发起POST请求
     *
     * @param url       指定的路径
     * @param headerMap 请求头Map
     * @param obj       该参数分为3种情况:
     *                  <ul>
     *                  <li>字符串,将直接放入body中</li>
     *                  <li>Map集合, 将使用{@link UrlEncodedFormEntity}处理为form表单</li>
     *                  <li>其他的对象, 将使用{@link JacksonUtils}处理为JSON字符串后放入body中</li>
     *                  </ul>
     * @return 响应字符串
     */
    public String doPost(String url, Map<String, String> headerMap, Object obj) {
        return doPost(url, headerMap, null, null, obj);
    }

    /**
     * 使用指定路径发起POST请求
     *
     * @param url          指定的路径
     * @param headerMap    请求头Map
     * @param urlParamsMap URL参数Map
     * @param obj          该参数分为3种情况:
     *                     <ul>
     *                     <li>字符串,将直接放入body中</li>
     *                     <li>Map集合, 将使用{@link UrlEncodedFormEntity}处理为form表单</li>
     *                     <li>其他的对象, 将使用{@link JacksonUtils}处理为JSON字符串后放入body中</li>
     *                     </ul>
     * @return 响应字符串
     */
    public String doPost(String url, Map<String, String> headerMap, Map<String, String> urlParamsMap, Object obj) {
        return doPost(url, headerMap, urlParamsMap, null, obj);
    }

    /**
     * 使用指定路径发起POST请求
     *
     * @param url             指定的路径
     * @param headerMap       请求头Map
     * @param urlParamsMap    URL参数Map
     * @param custQueryString 自定义查询字符串
     * @param obj             该参数分为3种情况:
     *                        <ul>
     *                        <li>字符串,将直接放入body中</li>
     *                        <li>Map集合, 将使用{@link UrlEncodedFormEntity}处理为form表单</li>
     *                        <li>其他的对象, 将使用{@link JacksonUtils}处理为JSON字符串后放入body中</li>
     *                        </ul>
     * @return 响应字符串
     */
    public String doPost(String url, Map<String, String> headerMap, Map<String, String> urlParamsMap, String custQueryString, Object obj) {
        try {
            HttpPost post = headerAndUrlParamsHandler(HttpMethods.POST, url, headerMap, urlParamsMap, custQueryString);
            Objects.requireNonNull(post, "HttpRequest对象必须被实例化!");
            if (Objects.nonNull(obj)) {
                if (obj instanceof String && !(((String) obj).trim().isEmpty())) {
                    LOGGER.log(Level.FINEST, String.format("POST请求字符串参数:%s", obj));
                    post.setEntity(new StringEntity((String) obj, StandardCharsets.UTF_8));
                } else if (obj instanceof Map && !((Map) obj).isEmpty()) {
                    @SuppressWarnings("unchecked") Map<String, String> formData = (Map) obj;
                    LOGGER.log(Level.FINEST, String.format("POST请求键值对参数:%s", formData));
                    final int size = formData.size();
                    int index = 0;
                    NameValuePair[] nvps = new NameValuePair[size];
                    for (Map.Entry<String, String> entry : formData.entrySet()) {
                        nvps[index] = new BasicNameValuePair(entry.getKey(), entry.getValue());
                        index++;
                    }
                    UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(Arrays.asList(nvps), StandardCharsets.UTF_8);
                    post.setEntity(entityParam);
                } else {
                    LOGGER.log(Level.FINEST, String.format("POST请求值对象参数:%s", obj));
                    post.setEntity(new StringEntity(JacksonUtils.getInstance().toJson(obj), StandardCharsets.UTF_8));
                    post.setHeader("Content-Type", "application/json;charset=utf-8");
                }
            }
            LOGGER.finest("请求:(" + post.toString() + "),参数详情:" + post.getEntity().toString());
            return execute(post);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 处理请求头和URL查询参数
     *
     * @param <T>             请求对象(HttpGet or HttpPost)
     * @param method          请求方法(当前只分POST和GET)
     * @param url             请求路径
     * @param headerMap       请求头Map
     * @param paramMap        URL查询参数Map
     * @param custQueryString 自定义查询字符串
     * @return 请求对象
     * @throws URISyntaxException 当请求地址不符合规范时,会抛出此异常
     */
    private <T> T headerAndUrlParamsHandler(HttpMethods method,
                                            String url,
                                            Map<String, String> headerMap,
                                            Map<String, String> paramMap,
                                            String custQueryString) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (Objects.nonNull(custQueryString) && !custQueryString.isEmpty()) {
            LOGGER.log(Level.FINEST, String.format("查询字符串:[%s]", custQueryString));
            uriBuilder.setCustomQuery(custQueryString);
        }
        if (paramMap != null && !paramMap.isEmpty()) {
            LOGGER.log(Level.FINEST, String.format("键值对参数:%s", paramMap.toString()));
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                uriBuilder.addParameter(entry.getKey(), entry.getValue());
            }
        }
        HttpRequest request;
        switch (method) {
            case POST:
                request = new HttpPost(uriBuilder.build());
                break;
            case GET:
                request = new HttpGet(uriBuilder.build());
                break;
            default:
                request = new HttpGet(uriBuilder.build());
                break;
        }
        if (headerMap != null && !headerMap.isEmpty()) {
            LOGGER.log(Level.FINEST, String.format("请求头集合:%s", headerMap.toString()));
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
        //noinspection unchecked
        return (T) request;
    }

    /**
     * 执行请求并处理响应结果
     *
     * @param request {@link HttpUriRequest}对象
     * @return JSON字符串
     * @throws IOException 网络不通或者服务器无响应时将抛出此异常
     */
    private String execute(HttpUriRequest request) throws IOException {
        request.setHeader(HttpHeaders.CONNECTION, "close");
        long start = System.currentTimeMillis();
        HttpResponse response = httpClient.execute(request);
        long end = System.currentTimeMillis();
        LOGGER.finest("远程访问耗时:" + (end - start) + "毫秒");
        if (Objects.isNull(response)) {
            LOGGER.log(Level.WARNING, "响应为NULL");
            return null;
        }
        HttpEntity entity = response.getEntity();
        String respString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        EntityUtils.consume(entity);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            LOGGER.log(Level.FINEST, "数据源返回状态:200,返回内容:" + respString + "调用地址:[" + request.getURI().toASCIIString() + "]");
        } else {
            request.abort();
            LOGGER.log(Level.WARNING, String.format("响应状态:%s, 请求地址:[%s]", response.getStatusLine().getStatusCode(), request.getURI().toString()));
        }
        return respString;
    }

    /**
     * 根据需要重设HttpClient对象
     *
     * @param config 请求配置对象
     * @return 为链式调用, 返回本工具类实例
     */
    public ApacheHttpClientUtils reconfig(RequestConfig config) {
        reconfig(this.connManager, config);
        return inst;
    }

    /**
     * 根据需要重设HttpClient对象
     *
     * @param connManager 连接管理器对象
     * @param config      请求配置对象
     * @return 为链式调用, 返回本工具类实例
     */
    public ApacheHttpClientUtils reconfig(PoolingHttpClientConnectionManager connManager, RequestConfig config) {
        reconfig(connManager, this.credentialsProvider, config);
        return inst;
    }

    /**
     * 根据需要重设HttpClient对象
     *
     * @param connManager         连接管理器对象
     * @param credentialsProvider 证书供应商对象(一般用于CA验证)
     * @param config              请求配置对象
     * @return 为链式调用, 返回本工具类实例
     */
    public ApacheHttpClientUtils reconfig(PoolingHttpClientConnectionManager connManager, CredentialsProvider credentialsProvider, RequestConfig config) {
        this.httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultCredentialsProvider(credentialsProvider)
                .setDefaultRequestConfig(config)
                .build();
        return inst;
    }

    /**
     * 获取当前HttpClient类对象
     *
     * @return {@link HttpClient}对象
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * 获取当前请求配置对象
     *
     * @return {@link RequestConfig}对象
     */
    public RequestConfig getConfig() {
        return config;
    }

    /**
     * 获取当前客户端连接管理器对象
     *
     * @return {@link PoolingHttpClientConnectionManager}对象
     */
    public PoolingHttpClientConnectionManager getConnManager() {
        return connManager;
    }

    /**
     * 获取当前证书供应商
     *
     * @return {@link CredentialsProvider}对象
     */
    public CredentialsProvider getCredentialsProvider() {
        return credentialsProvider;
    }

    private enum HttpMethods {
        GET,
        POST
    }
}

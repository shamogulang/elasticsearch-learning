package cn.oddworld;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.time.Duration;

/**
 * @author ldn
 * @date 2022/05/15 10:47
 */
public class HttpClientFactory {

    private static final CloseableHttpClient instace = HttpClient.INSTANCE.getHttpClient();

    public static CloseableHttpClient getInstace() {
        return instace;
    }

    private enum HttpClient {
        INSTANCE {
            @Override
            public CloseableHttpClient getHttpClient() {
                RequestConfig requestConfig = RequestConfig.custom()
                        .setConnectionRequestTimeout(HttpConfig.CONNECTION_REQUEST_TIMEOUT)
                        .setConnectTimeout(HttpConfig.CONNECT_TIMEOUT)
                        .setSocketTimeout(HttpConfig.SOCKET_CONNECT_TIMEOUT).build();
                CloseableHttpClient httpClient = HttpClients.custom()
                        .setDefaultRequestConfig(requestConfig)
                        .setMaxConnTotal(HttpConfig.MAX_CONN_TOTAL)
                        .setMaxConnPerRoute(HttpConfig.MAX_CONN_PER_ROUTE)
                        .setConnectionManagerShared(true)
                        .setKeepAliveStrategy((httpResponse, httpContext)
                                -> Duration.ofMinutes(HttpConfig.KEEP_ALIVE_TIME_MINUTES).toMillis())
                        .build();
                return httpClient;
            }
        };
        public abstract CloseableHttpClient getHttpClient();
    }

}

package cn.oddworld;

import java.util.ResourceBundle;

/**
 * @author ldn
 * @date 2022/05/15 12:03
 */
public class HttpConfig {
    /**
     * es.keepAliveTimeMinutes=5
     * es.connectTimeout=5000
     * es.socketTimeout=5000
     * es.connectionRequestTimeout=5000
     * es.maxConnTotal=100
     * es.connPerRoute=100
     * es.httpTimeout=300000
     */
    public static final int SOCKET_CONNECT_TIMEOUT = Integer.parseInt("5000");
    public static final int CONNECT_TIMEOUT = Integer.parseInt("5000");
    public static final int CONNECTION_REQUEST_TIMEOUT = Integer.parseInt("5000");

    public static final int MAX_CONN_TOTAL = Integer.parseInt("100");

    public static final int MAX_CONN_PER_ROUTE = Integer.parseInt("100");
    public static final int KEEP_ALIVE_TIME_MINUTES = Integer.parseInt("5");

}

package cn.oddworld.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyuhao_a
 */
@Configuration
public class ElasticSearchConfiguration {

    @Value("${elasticsearch.http.maxConnect:3000}")
    private int maxConnTotal;

    @Value("${elasticsearch.http.maxConnerPerRoute:3000}")
    private int maxConnerPerRoute;

    @Value("${elasticsearch.http.keepAliveTime:20}")
    private long keepAliveTime;

    @Value("${group.elasticsearch.http.connectTimeout:5}")
    private long connectTimeout;

    @Value("${group.elasticsearch.http.socketTimeout:10}")
    private long socketTimeout;


    @Bean
    public RestHighLevelClient restClientBuilder(
                                                 ObjectProvider<RestClientBuilderCustomizer> builderCustomizers){
        List<String> uris = new ArrayList<>();
        uris.add("http://127.0.0.1:9200");
        HttpHost[] hosts = uris.stream().map(HttpHost::create).toArray(HttpHost[]::new);
        RestClientBuilder builder = RestClient.builder(hosts);
        PropertyMapper map = PropertyMapper.get();
        builder.setHttpClientConfigCallback(
                (httpClientBuilder) -> {
                    httpClientBuilder.setMaxConnTotal(maxConnTotal);
                    httpClientBuilder.setMaxConnPerRoute(maxConnerPerRoute);
                    httpClientBuilder.setKeepAliveStrategy(((httpResponse, httpContext) -> Duration.ofSeconds(keepAliveTime).toMillis()));
                    return httpClientBuilder;
                });
        builder.setRequestConfigCallback((requestConfigBuilder) -> {
            map.from(Duration.ofSeconds(connectTimeout)).whenNonNull().asInt(Duration::toMillis)
                    .to(requestConfigBuilder::setConnectTimeout);
            map.from(Duration.ofSeconds(socketTimeout)).whenNonNull().asInt(Duration::toMillis)
                    .to(requestConfigBuilder::setSocketTimeout);
            return requestConfigBuilder;
        });
        builderCustomizers.orderedStream().forEach((customizer) -> customizer.customize(builder));
        return new RestHighLevelClient(builder);
    }

}

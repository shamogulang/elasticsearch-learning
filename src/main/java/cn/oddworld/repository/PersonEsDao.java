package cn.oddworld.repository;

import cn.oddworld.Jeffchan;
import cn.oddworld.Person;
import cn.oddworld.SplitStrUtil;
import cn.oddworld.apibean.ReindexReq;
import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.InnerHitBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.elasticsearch.client.RequestOptions.DEFAULT;

@Component
public class PersonEsDao {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    private static final String[] SETTINGS_EXCLUDE_PROPERTY = {"index.creation_date", "index.provided_name",
            "index.uuid", "index.version.created"};

    public List<Person> getjeffchan(){

        jeffchan();
        System.out.println("=====");
//        jeffchan2();
//        System.out.println("=====");
//        jeffchan3();
        return null;
    }

    public void saveJeffchan(){
        IndexRequest request = new IndexRequest("jeffchan");

    }

    public void jeffchan(){
        // 1、SearchRequest
        SearchRequest searchRequest = new SearchRequest("jeffchan");

        // 2、指定查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        List<String> splitStr = SplitStrUtil.getSplitStr("44116e1234567");
        List<String> fields = new ArrayList<String>();
        for (int i = 0; i < splitStr.size(); i++){
            fields.add("name");
        }
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        for (int i = 0; i < fields.size(); i++) {
            boolQuery.must(QueryBuilders.wildcardQuery(fields.get(i), splitStr.get(i)));
        }
        // 2.1、查询条件
        searchSourceBuilder.query(boolQuery);
        // 2.2、指定高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name", 10);
//                .preTags("<font color='red'>") 这里可以指定标签
//                .postTags("</font>");

        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.size(10);
        Map<String, Object> jsonMap = new HashMap<>();
        searchSourceBuilder.runtimeMappings(jsonMap);
        searchRequest.source(searchSourceBuilder);

        String routing = "";
        for(int i =0 ;  i < Integer.MAX_VALUE; i++){
            String s = UUID.randomUUID().toString();
            routing += s;
            if(routing.getBytes().length > 4096){
                break;
            }
        }
        searchRequest.routing(routing);
        System.out.println(searchRequest.source().toString());
        try {
            MultiSearchRequest searchRequest1 = new MultiSearchRequest();
            searchRequest1.add(searchRequest);
            //SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            MultiSearchResponse msearch = restHighLevelClient.msearch(searchRequest1, DEFAULT);
            MultiSearchResponse.Item[] responses = msearch.getResponses();
            SearchResponse response = responses[0].getResponse();
            SearchHit[] hits = response.getHits().getHits();
            for(SearchHit searchHit : hits){
                Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                for(String key : highlightFields.keySet()){
                    System.out.println(key);
                    System.out.println(highlightFields.get(key).fragments()[0]);
                }
                System.out.println(searchHit.getSourceAsString());
            }
        }catch (Exception e){
            ;
            System.out.println(e.getCause());
        }
    }

    public void jeffchan2(){
        // 1、SearchRequest
        SearchRequest searchRequest = new SearchRequest("jeffchan2");

        // 2、指定查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 2.1、查询条件
        searchSourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("name", "陈建"));
        // 2.2、指定高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name", 10);
//                .preTags("<font color='red'>") 这里可以指定标签
//                .postTags("</font>");

        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.size(10);
        searchRequest.source(searchSourceBuilder);

        System.out.println(searchRequest.source().toString());
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            for(SearchHit searchHit : hits){
                Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                for(String key : highlightFields.keySet()){
                    System.out.println(key);
                    System.out.println(highlightFields.get(key).fragments()[0]);
                }
                System.out.println(searchHit.getSourceAsString());
            }
        }catch (Exception e){
            ;
            System.out.println(e.getCause());
        }
    }

    public void jeffchan3(){
        // 1、SearchRequest
        SearchRequest searchRequest = new SearchRequest("jeffchan3");

        // 2、指定查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 2.1、查询条件
        searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("name", "陈建"));
        // 2.2、指定高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name", 10);
//                .preTags("<font color='red'>") 这里可以指定标签
//                .postTags("</font>");

        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.size(10);
        searchRequest.source(searchSourceBuilder);

        System.out.println(searchRequest.source().toString());
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            for(SearchHit searchHit : hits){
                Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                for(String key : highlightFields.keySet()){
                    System.out.println(key);
                    System.out.println(highlightFields.get(key).fragments()[0]);
                }
                System.out.println(searchHit.getSourceAsString());
            }
        }catch (Exception e){
            ;
            System.out.println(e.getCause());
        }
    }


    public void reindex(ReindexReq reindexReq) throws IOException {

        /**
         * 迁移mapping
         *
         */


        final CredentialsProvider credentialsProvider1 = new BasicCredentialsProvider();
        credentialsProvider1.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "oTuay97EONvhYB40tBlv"));

        // targetClient
        RestHighLevelClient sourceClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("10.164.9.10", 9200, "http"))
                        .setHttpClientConfigCallback(httpClientBuilder ->
                                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider1)));


        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "084Dh12c5sl39JfcteMIXAnw"));

        RestHighLevelClient targetClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("ba21942c4d7b4a1ba50c40d17deba5d8.elasticsearch.cmecloud.cn", 9243, "https"))
                        .setHttpClientConfigCallback(httpClientBuilder ->
                                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)));


        GetSettingsRequest requestSetting = new GetSettingsRequest().indices(reindexReq.getSourceIndex());
        GetSettingsResponse response = sourceClient.indices().getSettings(requestSetting, RequestOptions.DEFAULT);
        Settings settings = response.getIndexToSettings().get(reindexReq.getSourceIndex());


        /**
         * "index.creation_date", "index.provided_name",
         *             "index.uuid", "index.version.created"
         */
        Settings newSetting = settings.filter(item ->
                !(item.equals("index.creation_date")
                         || item.equals("index.provided_name")
                         || item.equals("index.uuid")
                         || item.equals("index.version.created"))
        );

//        UpdateSettingsRequest requestSettingUpdate = new UpdateSettingsRequest(reindexReq.getTargetIndex());
//        requestSettingUpdate.settings(newSetting);
//        targetClient.indices().putSettings(requestSettingUpdate, RequestOptions.DEFAULT);

        // 获取源索引的mapping
        GetMappingsRequest getRequest = new GetMappingsRequest().indices(reindexReq.getSourceIndex());
        GetMappingsResponse getResponse = sourceClient.indices().getMapping(getRequest, RequestOptions.DEFAULT);
        MappingMetadata mappings = getResponse.mappings().get(reindexReq.getSourceIndex());

//        PutMappingRequest putRequest = new PutMappingRequest(reindexReq.getTargetIndex());
//        putRequest.source(mappings.getSourceAsMap());
//        try{
//            AcknowledgedResponse putResponse = targetClient.indices().putMapping(putRequest, DEFAULT);
//            log.info("mapping index detail: {}", putResponse);
//        }catch (Exception e){
//            log.warn("mapping   create error, ", e);
//        }

        // 创建索引
        try{
            CreateIndexRequest request1 = new CreateIndexRequest(reindexReq.getTargetIndex());
            // 迁移索引setting信息
            request1.settings(newSetting);
            // 迁移mapping到目标索引
            request1.mapping(mappings.getSourceAsMap());
            targetClient.indices().create(request1, RequestOptions.DEFAULT);
        }catch (Exception e){
            log.warn("index create error, ", e);
        }

        // 迁移数据
        // create a search request for the source index
        SearchRequest searchRequest = new SearchRequest(reindexReq.getSourceIndex());

        // set the scroll timeout to 1 minute
        searchRequest.scroll(TimeValue.timeValueMinutes(1L));

        // set the search source builder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(1000);

        searchRequest.source(searchSourceBuilder);

        // execute the search request
        SearchResponse searchResponse = sourceClient.search(searchRequest, RequestOptions.DEFAULT);

        // get the search hits
        SearchHits hits = searchResponse.getHits();

        // check if there are no more hits
        if (hits.getHits().length == 0) {
            return;
        }
        // create a search scroll request
        SearchScrollRequest scrollRequest = new SearchScrollRequest();
        while (true) {

            // create a bulk request for the target index
            BulkRequest bulkRequest = new BulkRequest();

            String scrollId = scrollRequest.scrollId();
            if(!StringUtils.isEmpty(scrollId)){
                scrollRequest.scroll(TimeValue.timeValueMinutes(1L));
                searchResponse = sourceClient.scroll(scrollRequest, RequestOptions.DEFAULT);
                // get the search hits
                hits = searchResponse.getHits();
            }

            // check if there are no more hits
            if (hits.getHits().length == 0) {
                break;
            }

            // iterate over the search hits and add them to the bulk request
            for (SearchHit hit : hits.getHits()) {
                IndexRequest indexRequest = new IndexRequest();
                indexRequest.id(hit.getId());
                indexRequest.index(reindexReq.getTargetIndex());
                indexRequest.source(hit.getSourceAsMap());
                String account = (String) hit.getSourceAsMap().get("ownerAccount");
                indexRequest.routing(account);
                bulkRequest.add(indexRequest);
            }

            // execute the bulk request
            BulkResponse bulkResponse = targetClient.bulk(bulkRequest, RequestOptions.DEFAULT);

            // check for errors in the bulk response
            if (bulkResponse.hasFailures()) {
                // handle the errors
                log.info("failes msg : {}", bulkResponse.hasFailures());
            }

            // create a new search scroll request
            scrollRequest.scrollId(searchResponse.getScrollId());
        }

        sourceClient.close();
        targetClient.close();
    }

    public List<Person> getPersonHighLight(){

//        BoolQueryBuilder outerBoolQuery = QueryBuilders.boolQuery();
//
//
//        outerBoolQuery.must(QueryBuilders.matchQuery("name", "剑辉"));
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.highlighterType("plain");
//        highlightBuilder.field("name");
//
//        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("", null, ScoreMode.None);
//
//        InnerHitBuilder innerHitBuilder = new InnerHitBuilder();
//        innerHitBuilder.setHighlightBuilder(highlightBuilder);
//        innerHitBuilder.addSort(SortBuilders.fieldSort("_score").order(SortOrder.DESC));
//        innerHitBuilder.addSort(SortBuilders.fieldSort("age").order(SortOrder.DESC));
//        innerHitBuilder.setTrackScores(true);
//        innerHitBuilder.setFrom(0);
//        nestedQueryBuilder.innerHit(innerHitBuilder);

        // 1、SearchRequest
        SearchRequest searchRequest = new SearchRequest("alias-person");

        // 2、指定查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 2.1、查询条件
        searchSourceBuilder.query(QueryBuilders.matchQuery("name", "剑辉"));
        // 2.2、指定高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name", 10);
//                .preTags("<font color='red'>")
//                .postTags("</font>");

        searchSourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);

        //2.4、指定高亮
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("contName", 10);
//        searchSourceBuilder.highlighter(highlightBuilder);

//        SearchRequest getRequest = new SearchRequest().source(new SearchSourceBuilder()
//                .query(outerBoolQuery.must(nestedQueryBuilder))
//                .from(0)
//                .size(10))
//                .indices("alias-person");
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            for(SearchHit searchHit : hits){
                Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                for(String key : highlightFields.keySet()){
                    System.out.println(key);
                    System.out.println(highlightFields.get(key).fragments()[0]);
                }
                System.out.println(searchHit.getSourceAsString());
            }
        }catch (Exception e){
            ;
            System.out.println(e.getCause());
        }
        return null;
    }
}

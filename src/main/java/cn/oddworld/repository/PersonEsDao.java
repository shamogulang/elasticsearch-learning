package cn.oddworld.repository;

import cn.oddworld.Jeffchan;
import cn.oddworld.Person;
import cn.oddworld.SplitStrUtil;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.InnerHitBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.elasticsearch.client.RequestOptions.DEFAULT;

@Component
public class PersonEsDao {


    @Autowired
    private RestHighLevelClient restHighLevelClient;


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

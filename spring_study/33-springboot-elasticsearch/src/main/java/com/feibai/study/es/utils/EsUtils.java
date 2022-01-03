package com.feibai.study.es.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Map;

public class EsUtils {

  public static RestHighLevelClient getEsClient() {
    // 创建客户端对象
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(new HttpHost("localhost", 9200, "http")));
    return client;
  }

  public static void releaseEsClient(RestHighLevelClient client) throws IOException {
    client.close();
  }

  /**
   * 创建索引
   */
  public static boolean createIndex(RestHighLevelClient client, String index) throws IOException {
    CreateIndexRequest request = new CreateIndexRequest(index);
    // 发送请求，获取响应
    CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
    boolean acknowledged = response.isAcknowledged();
    // 响应状态
    System.out.println("操作状态-->" + acknowledged);
    return acknowledged;
  }

  /**
   * 查看索引
   */
  public static String searchIndex(RestHighLevelClient client, String index) throws IOException {
    // 查询索引 - 请求对象
    GetIndexRequest request = new GetIndexRequest(index);
    // 发送请求，获取响应
    GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
    System.out.println("aliases:" + response.getAliases());
    System.out.println("mappings:" + response.getMappings());
    System.out.println("settings:" + response.getSettings());

    return response.toString();
  }

  /**
   * 删除索引
   */
  public static boolean deleteIndex(RestHighLevelClient client, String index) throws IOException {
    // 删除索引 - 请求对象
    DeleteIndexRequest request = new DeleteIndexRequest(index);
    // 发送请求，获取响应
    AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
    // 操作结果
    System.out.println("操作结果 : " + response.isAcknowledged());
    return response.isAcknowledged();
  }

  /**
   * 新增文档
   */
  public static String addDoc(RestHighLevelClient client, Object o) throws IOException {
    // 新增文档 - 请求对象
    IndexRequest request = new IndexRequest();
    // 设置索引及唯一性标识
    request.index("user").id("1001");
    ObjectMapper objectMapper = new ObjectMapper();
    String productJson = objectMapper.writeValueAsString(o);
    // 添加文档数据，数据格式为 JSON 格式
    request.source(productJson, XContentType.JSON);
    // 客户端发送请求，获取响应对象
    IndexResponse response = client.index(request, RequestOptions.DEFAULT);
    //3.打印结果信息
    System.out.println("_index:" + response.getIndex());
    System.out.println("_id:" + response.getId());
    System.out.println("_result:" + response.getResult());

    return response.getId();
  }

  /**
   * 更新文档
   */
  public static String updateDoc(RestHighLevelClient client, Object o) throws IOException {
    // 修改文档 - 请求对象
    UpdateRequest request = new UpdateRequest();
    // 配置修改参数
    request.index("user").id("1001");

    // 设置请求体，对数据进行修改
    request.doc(XContentType.JSON, "sex", "女");
    // 客户端发送请求，获取响应对象
    UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
    System.out.println("_index:" + response.getIndex());
    System.out.println("_id:" + response.getId());
    System.out.println("_result:" + response.getResult());
    return response.getId();
  }

  /**
   * 删除文档
   */
  public static String deleteDoc(RestHighLevelClient client) throws IOException {
    //创建请求对象
    DeleteRequest request = new DeleteRequest().index("user").id("1");
    //客户端发送请求，获取响应对象
    DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
    //打印信息
    System.out.println(response.toString());
    return response.getId();
  }

  /**
   * 批量新增
   */
  public static void batchAddDoc(RestHighLevelClient client) throws IOException {
    //创建批量新增请求对象
    BulkRequest request = new BulkRequest();
    request.add(
        new IndexRequest().index("user").id("1001").source(XContentType.JSON, "name",
            "zhangsan"));
    request.add(
        new IndexRequest().index("user").id("1002").source(XContentType.JSON, "name",
            "lisi"));
    request.add(
        new IndexRequest().index("user").id("1003").source(XContentType.JSON, "name",
            "wangwu"));
    //客户端发送请求，获取响应对象
    BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
    //打印结果信息
    System.out.println("took:" + responses.getTook());
    System.out.println("items:" + responses.getItems());
  }

  /**
   * 批量删除
   */
  public static void batchDelete(RestHighLevelClient client) throws IOException {
    //创建批量删除请求对象
    BulkRequest request = new BulkRequest();
    request.add(new DeleteRequest().index("user").id("1001"));
    request.add(new DeleteRequest().index("user").id("1002"));
    request.add(new DeleteRequest().index("user").id("1003"));
    //客户端发送请求，获取响应对象
    BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
    //打印结果信息
    System.out.println("took:" + responses.getTook());
    System.out.println("items:" + responses.getItems());
  }

  /**
   * 查询
   */
  public static SearchHit[] getAllDocumentsByIndex(RestHighLevelClient client) throws IOException {
    // 创建搜索请求对象
    SearchRequest request = new SearchRequest();
    request.indices("student");
    // 构建查询的请求体
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    // 查询所有数据
    sourceBuilder.query(QueryBuilders.matchAllQuery());
    request.source(sourceBuilder);
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 查询匹配
    SearchHits hits = response.getHits();
    System.out.println("took:" + response.getTook());
    System.out.println("timeout:" + response.isTimedOut());
    System.out.println("total:" + hits.getTotalHits());
    System.out.println("MaxScore:" + hits.getMaxScore());
    System.out.println("hits========>>");
    for (SearchHit hit : hits) { //输出每条查询的结果信息
      System.out.println(hit.getSourceAsString());
    }
    System.out.println("<<========");

    return hits.getHits();
  }

  /**
   * 条件查询
   */
  public static SearchHit[] getHitsByCondition(RestHighLevelClient client) throws IOException {
    // 创建搜索请求对象
    SearchRequest request = new SearchRequest();
    request.indices("student");
    // 构建查询的请求体
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.termQuery("age", "30"));
    request.source(sourceBuilder);
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 查询匹配
    SearchHits hits = response.getHits();
    System.out.println("took:" + response.getTook());
    System.out.println("timeout:" + response.isTimedOut());
    System.out.println("total:" + hits.getTotalHits());
    System.out.println("MaxScore:" + hits.getMaxScore());
    System.out.println("hits========>>");
    for (SearchHit hit : hits) {
      //输出每条查询的结果信息
      System.out.println(hit.getSourceAsString());
    }
    System.out.println("<<========");
    return hits.getHits();
  }

  /**
   * 分页查询
   */
  public static SearchHits getHitsWithLimit(RestHighLevelClient client) throws IOException {
    // 创建搜索请求对象
    SearchRequest request = new SearchRequest();
    request.indices("student");
    // 构建查询的请求体
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.matchAllQuery());
    // 分页查询
    // 当前页其实索引(第一条数据的顺序号)，from
    sourceBuilder.from(0);
    // 每页显示多少条 size
    sourceBuilder.size(2);
    request.source(sourceBuilder);
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 查询匹配
    SearchHits hits = response.getHits();
    System.out.println("took:" + response.getTook());
    System.out.println("timeout:" + response.isTimedOut());
    System.out.println("total:" + hits.getTotalHits());
    System.out.println("MaxScore:" + hits.getMaxScore());
    System.out.println("hits========>>");
    for (SearchHit hit : hits) {
      //输出每条查询的结果信息
      System.out.println(hit.getSourceAsString());
    }
    System.out.println("<<========");
    return response.getHits();
  }

  /**
   * 数据排序
   */
  public static SearchHits getHitsOrderBy(RestHighLevelClient client) throws IOException {
    // 创建搜索请求对象
    SearchRequest request = new SearchRequest();
    request.indices("student");

    // 构建查询的请求体
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.matchAllQuery());
    // 排序
    sourceBuilder.sort("age", SortOrder.ASC);
    request.source(sourceBuilder);
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 查询匹配
    SearchHits hits = response.getHits();

    return hits;
  }

  /**
   * 过滤字段
   */
  public static SearchHits getAndFilterHits(RestHighLevelClient client) throws IOException {
    // 创建搜索请求对象
    SearchRequest request = new SearchRequest();
    request.indices("student");

    // 构建查询的请求体
    // 查询字段过滤
    String[] excludes = {};
    // 只查询name、age字段
    String[] includes = {"name", "age"};
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.matchAllQuery());
    sourceBuilder.fetchSource(includes, excludes);
    request.source(sourceBuilder);
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 查询匹配
    SearchHits hits = response.getHits();
    System.out.println("took:" + response.getTook());
    System.out.println("timeout:" + response.isTimedOut());
    System.out.println("total:" + hits.getTotalHits());
    System.out.println("MaxScore:" + hits.getMaxScore());
    System.out.println("hits========>>");
    for (SearchHit hit : hits) {
      //输出每条查询的结果信息
      System.out.println(hit.getSourceAsString());
    }
    System.out.println("<<========");

    return hits;
  }

  /**
   * bool查询
   */
  public static SearchHits getHitsByMultiCondition(RestHighLevelClient client) throws IOException {
    // 创建搜索请求对象
    SearchRequest request = new SearchRequest();
    request.indices("student");
    // 构建查询的请求体
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    // 必须包含
    boolQueryBuilder.must(QueryBuilders.matchQuery("age", "30"));
    // 一定不含
    boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name", "zhangsan"));
    // 可能包含
    boolQueryBuilder.should(QueryBuilders.matchQuery("sex", "男"));
    sourceBuilder.query(boolQueryBuilder);
    request.source(sourceBuilder);
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 查询匹配
    SearchHits hits = response.getHits();
    System.out.println("took:" + response.getTook());
    System.out.println("timeout:" + response.isTimedOut());
    System.out.println("total:" + hits.getTotalHits());
    System.out.println("MaxScore:" + hits.getMaxScore());
    System.out.println("hits========>>");
    for (SearchHit hit : hits) {
      //输出每条查询的结果信息
      System.out.println(hit.getSourceAsString());
    }
    System.out.println("<<========");
    return hits;
  }

  /**
   * 范围查询
   */
  public static SearchHits getHitsByRange(RestHighLevelClient client) throws IOException {
    // 创建搜索请求对象
    SearchRequest request = new SearchRequest();
    request.indices("student");

    // 构建查询的请求体
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
    // 大于等于
    rangeQuery.gte("30");
    // 小于等于
    rangeQuery.lte("40");
    sourceBuilder.query(rangeQuery);
    request.source(sourceBuilder);
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 查询匹配
    SearchHits hits = response.getHits();
    System.out.println("took:" + response.getTook());
    System.out.println("timeout:" + response.isTimedOut());
    System.out.println("total:" + hits.getTotalHits());
    System.out.println("MaxScore:" + hits.getMaxScore());
    System.out.println("hits========>>");
    for (SearchHit hit : hits) {
      //输出每条查询的结果信息
      System.out.println(hit.getSourceAsString());
    }
    System.out.println("<<========");

    return hits;
  }

  /**
   * 模糊查询
   */
  public static SearchHits getHitsLikely(RestHighLevelClient client) throws IOException {
    // 创建搜索请求对象
    SearchRequest request = new SearchRequest();
    request.indices("student");

    // 构建查询的请求体
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.fuzzyQuery("name", "zhangsan").fuzziness(Fuzziness.ONE));
    request.source(sourceBuilder);
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    // 查询匹配
    SearchHits hits = response.getHits();
    System.out.println("took:" + response.getTook());
    System.out.println("timeout:" + response.isTimedOut());
    System.out.println("total:" + hits.getTotalHits());
    System.out.println("MaxScore:" + hits.getMaxScore());
    System.out.println("hits========>>");
    for (SearchHit hit : hits) {
      //输出每条查询的结果信息
      System.out.println(hit.getSourceAsString());
    }
    System.out.println("<<========");
    return hits;
  }

  /**
   * 高亮查询
   */
  public static SearchHits getHitsHighlight(RestHighLevelClient client) throws IOException {
    // 高亮查询
    SearchRequest request = new SearchRequest().indices("student");
    //2.创建查询请求体构建器
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    //构建查询方式:高亮查询
    TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name", "zhangsan");
    //设置查询方式
    sourceBuilder.query(termsQueryBuilder);

    //构建高亮字段
    HighlightBuilder highlightBuilder = new HighlightBuilder();
    highlightBuilder.preTags("<font color='red'>");
    //设置标签前缀
    highlightBuilder.postTags("</font>");
    //设置标签后缀
    highlightBuilder.field("name");
    //设置高亮字段
    //设置高亮构建对象
    sourceBuilder.highlighter(highlightBuilder);
    //设置请求体
    request.source(sourceBuilder);
    //3.客户端发送请求，获取响应对象
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    //4.打印响应结果
    SearchHits hits = response.getHits();
    System.out.println("took::" + response.getTook());
    System.out.println("time_out::" + response.isTimedOut());
    System.out.println("total::" + hits.getTotalHits());
    System.out.println("max_score::" + hits.getMaxScore());
    System.out.println("hits::::>>");
    for (SearchHit hit : hits) {
      String sourceAsString = hit.getSourceAsString();
      System.out.println(sourceAsString);
      //打印高亮结果
      Map<String, HighlightField> highlightFields = hit.getHighlightFields();
      System.out.println(highlightFields);
    }
    System.out.println("<<::::");

    return hits;
  }

  /**
   * 聚合查询--最大年龄
   */
  public static SearchHits getAggHits(RestHighLevelClient client) throws IOException {
    // 高亮查询
    SearchRequest request = new SearchRequest().indices("student");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.aggregation(AggregationBuilders.max("maxAge").field("age"));
    //设置请求体
    request.source(sourceBuilder);
    //3.客户端发送请求，获取响应对象
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    //4.打印响应结果
    SearchHits hits = response.getHits();
    System.out.println(response);

    return hits;
  }

  /**
   * 聚合查询--分组统计
   */
  public static SearchHits getHitsAndGroupby(RestHighLevelClient client) throws IOException {
    // 高亮查询
    SearchRequest request = new SearchRequest().indices("student");
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));
    //设置请求体
    request.source(sourceBuilder);
    //3.客户端发送请求，获取响应对象
    SearchResponse response = client.search(request, RequestOptions.DEFAULT);
    //4.打印响应结果
    SearchHits hits = response.getHits();
    System.out.println(response);
    return hits;
  }

}

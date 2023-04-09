//package com.feibai.study.es.utils;
//
//import static java.lang.String.format;
//
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.alibaba.fastjson.JSON;
//import com.baidu.noah.odw.libra.configuration.ElasticsearchProperties;
//import com.baidu.noah.odw.libra.domain.AbstractEsModel;
//import com.baidu.noah.odw.libra.domain.ArcherDeployProductEvent;
//import com.baidu.noah.odw.libra.domain.Bucket;
//import com.baidu.noah.odw.libra.domain.CtProductEvent;
//import com.baidu.noah.odw.libra.domain.DataDistributionProductEvent;
//import com.baidu.noah.odw.libra.domain.DataReleaseEvent;
//import com.baidu.noah.odw.libra.domain.DataTransmissionProductEvent;
//import com.baidu.noah.odw.libra.domain.DefaultEvent;
//import com.baidu.noah.odw.libra.domain.EsResponse;
//import com.baidu.noah.odw.libra.domain.EventData;
//import com.baidu.noah.odw.libra.domain.FaultHanding;
//import com.baidu.noah.odw.libra.domain.FaultManager;
//import com.baidu.noah.odw.libra.domain.Hit;
//import com.baidu.noah.odw.libra.domain.Hits;
//import com.baidu.noah.odw.libra.domain.MachineManager;
//import com.baidu.noah.odw.libra.domain.NoahWebDeployProductEvent;
//import com.baidu.noah.odw.libra.domain.ReleaseEvent;
//import com.baidu.noah.odw.libra.domain.Shards;
//import com.google.common.base.Preconditions;
//
//public class EsUtil {
//  private static final Logger LOG = LoggerFactory.getLogger(EsUtil.class);
//
//  public static final String EVENT_TYPE_FAULTMANAGER = "FaultManager";
//  public static final String EVENT_TYPE_FAULTHANDING = "FaultHanding";
//  public static final String EVENT_TYPE_RELEASEEVENT = "ReleaseEvent";
//  public static final String EVENT_TYPE_DATARELEASEEVENT = "DataReleaseEvent";
//  public static final String EVENT_TYPE_MACHINEMANAGER = "MachineManager";
//  public static final String EVENT_TYPE_DDPRODUCT = "DataDistributionProduct";
//  public static final String EVENT_TYPE_CTPRODUCT = "CTProduct";
//  public static final String EVENT_TYPE_ARCHERDEPLOYPRODUCT = "ArcherDeployProduct";
//  public static final String EVENT_TYPE_NOAHWEBDEPLOYPRODUCT = "NoahwebDeployProduct";
//  public static final String EVENT_TYPE_DATATRANSMISSIONPRODUCT = "DataTransmissionProduct";
//
//  private static final String DEFAULT_ES_URL = "http://esmaster.noah.baidu.com/";
//  public static String ES_URL = DEFAULT_ES_URL;
//  public static final String INDEX_PREFIX = "online-";
//  public static final int MAX_SEARCH_SIZE = 10000;
//
//  public static final String RANGE_EXPRESSION_LT = "lt";
//  public static final String RANGE_EXPRESSION_GT = "gt";
//  public static final String RANGE_EXPRESSION_LTE = "lte";
//  public static final String RANGE_EXPRESSION_GTE = "gte";
//
//  private static final String TAGS = "tags";
//  private static final String TAG_SIZE = "tagSize";
//
//  public static void init(ElasticsearchProperties ep) {
//    if (ep != null) {
//      String baseUrl = ep.getBaseUrl();
//      if (baseUrl != null && !"".equals(baseUrl)) {
//        LOG.info("Reset elasticsearch base url to: " + baseUrl);
//        ES_URL = baseUrl;
//      }
//    }
//  }
//
//  /**
//   * 查询所有ES事件，有多少查多少
//   *
//   * @param type
//   * @param terms
//   * @param startTime 单位：millisecond
//   * @param endTime   单位：millisecond
//   * @return
//   * @throws Exception
//   */
//  public static EventData searchAllEvent(String type, Map<String, Object> terms, long startTime, long endTime)
//      throws Exception {
//    int from = 0;
//    int size = MAX_SEARCH_SIZE;
//    EventData ed = searchEvent(type, terms, startTime, endTime, size, from);
//    from = ed.getEventList().size() + ed.getTotalErrNum();
//    while (from < ed.getTotal()) {
//      EventData nextEd = searchEvent(type, terms, startTime, endTime, size, from);
//      if (ed.getTotal() != nextEd.getTotal()) {
//        String warnMsg =
//            "Get Data size not equal[" + ed.getTotal() + ", " + nextEd.getTotal()
//                + "], may be data updated, use larger total size.";
//        LOG.warn(warnMsg);
//        ed.setTotal(Math.max(ed.getTotal(), nextEd.getTotal()));
//      }
//      ed.getEventList().addAll(nextEd.getEventList());
//      ed.setTotalErrNum(ed.getTotalErrNum() + nextEd.getTotalErrNum());
//      from = ed.getEventList().size() + ed.getTotalErrNum();
//    }
//    return ed;
//  }
//
//  /**
//   * 查询所有ES事件，最多查询{@link #MAX_SEARCH_SIZE} 个记录
//   *
//   * @param type
//   * @param terms
//   * @param startTime 单位：millisecond
//   * @param endTime   单位：millisecond
//   * @return
//   * @throws Exception
//   */
//  public static EventData searchEvent(String type, Map<String, Object> terms, long startTime, long endTime)
//      throws Exception {
//    return searchEvent(null, null, type, terms, startTime, endTime, MAX_SEARCH_SIZE, 0, null, false);
//  }
//
//  /**
//   * 查询所有ES事件，最多查询{@link #MAX_SEARCH_SIZE} 个记录
//   *
//   * @param esUrl
//   * @param index
//   * @param type
//   * @param terms
//   * @param startTime 单位：millisecond
//   * @param endTime   单位：millisecond
//   * @return
//   * @throws Exception
//   */
//  public static EventData searchEvent(String esUrl, String index, String type, Map<String, Object> terms,
//                                      long startTime, long endTime) throws Exception {
//    return searchEvent(esUrl, index, type, terms, startTime, endTime, MAX_SEARCH_SIZE, 0, null, false);
//  }
//
//  /**
//   * 查询ES事件
//   *
//   * @param type
//   * @param terms
//   * @param startTime 单位：millisecond
//   * @param endTime   单位：millisecond
//   * @param size
//   * @param from
//   * @return
//   * @throws Exception
//   */
//  public static EventData searchEvent(String type, Map<String, Object> terms, long startTime, long endTime, int size,
//                                      int from) throws Exception {
//    return searchEvent(null, null, type, terms, startTime, endTime, size, from, null, false);
//  }
//
//  /**
//   * 查询ES事件
//   *
//   * @param type
//   * @param terms
//   * @param startTime 单位：millisecond
//   * @param endTime   单位：millisecond
//   * @param size
//   * @param from
//   * @param isShould
//   * @return
//   * @throws Exception
//   */
//  public static EventData searchEvent(String type, Map<String, Object> terms, long startTime, long endTime, int size,
//                                      int from, boolean isShould) throws Exception {
//    if (isShould) {
//      return searchEvent(null, null, type, terms, startTime, endTime, size, from, true, null, false);
//    }
//    return searchEvent(null, null, type, terms, startTime, endTime, size, from, null, false);
//  }
//
//  /**
//   * 查询ES事件
//   *
//   * @param esUrl
//   * @param index
//   * @param type
//   * @param terms
//   * @param startTime 单位：millisecond
//   * @param endTime   单位：millisecond
//   * @param size
//   * @param from
//   * @return
//   * @throws Exception
//   */
//  public static EventData searchEvent(String esUrl, String index, String type, Map<String, Object> terms,
//                                      long startTime, long endTime, int size, int from) throws Exception {
//    return searchEvent(esUrl, index, type, terms, startTime, endTime, size, from, null, false);
//  }
//
//  /**
//   * 查询ES事件
//   *
//   * @param esUrl
//   * @param index
//   * @param type
//   * @param terms
//   * @param startTime 单位：millisecond
//   * @param endTime   单位：millisecond
//   * @param size
//   * @param from
//   * @param sortField
//   * @param asc
//   * @return EventData
//   * @throws Exception
//   */
//  public static EventData searchEvent(String esUrl, String index, String type, Map<String, Object> terms,
//                                      long startTime, long endTime, int size, int from, String sortField, boolean asc) throws Exception {
//    EventData ed = new EventData();
//
//    Map<String, Object> query = buildNormalQuery(startTime, endTime, terms, size, from, sortField, asc);
//    String queryJsonStr = JSON.toJSONString(query);
//    if (StringUtils.isBlank(index)) {
//      index = getIndex(startTime, endTime);
//    }
//    if (esUrl == null || "".equals(esUrl.trim())) {
//      esUrl = ES_URL;
//    }
//    String url = esUrl + index + "/" + type + "/_search";
//    EsResponse esResponse = searchEventResponse(url, queryJsonStr);
//    setHits(ed, type, esResponse.getHits());
//    return ed;
//  }
//
//  /**
//   * 查询ES事件
//   *
//   * @param esUrl
//   * @param index
//   * @param type
//   * @param terms
//   * @param startTime 单位：millisecond
//   * @param endTime   单位：millisecond
//   * @param size
//   * @param from
//   * @param sortField
//   * @param asc
//   * @param isShould
//   * @return EventData
//   * @throws Exception
//   */
//  public static EventData searchEvent(String esUrl, String index, String type, Map<String, Object> terms,
//                                      long startTime, long endTime, int size, int from, boolean isShould, String sortField, boolean asc)
//      throws Exception {
//    EventData ed = new EventData();
//    Map<String, Object> query;
//    if (isShould) {
//      query = buildShouldQuery(startTime, endTime, terms, size, from, sortField, asc);
//    } else {
//      query = buildNormalQuery(startTime, endTime, terms, size, from, sortField, asc);
//    }
//    String queryJsonStr = JSON.toJSONString(query);
//    if (StringUtils.isBlank(index)) {
//      index = getIndex(startTime, endTime);
//    }
//    if (esUrl == null || "".equals(esUrl.trim())) {
//      esUrl = ES_URL;
//    }
//    String url = esUrl + index + "/" + type + "/_search";
//    EsResponse esResponse = searchEventResponse(url, queryJsonStr);
//    setHits(ed, type, esResponse.getHits());
//    return ed;
//  }
//
//  public static EventData searchEvent(String esUrl, String index, String type, long startTime, long endTime,
//                                      Map<String, Object> query) throws Exception {
//    EventData ed = new EventData();
//    String queryJsonStr = JSON.toJSONString(query);
//    if (StringUtils.isBlank(index)) {
//      index = getIndex(startTime, endTime);
//    }
//    if (esUrl == null || "".equals(esUrl.trim())) {
//      esUrl = ES_URL;
//    }
//    String url = esUrl + index + "/" + type + "/_search";
//    EsResponse esResponse = searchEventResponse(url, queryJsonStr);
//    setHits(ed, type, esResponse.getHits());
//    return ed;
//  }
//
//  /**
//   * 根据id查询ES事件
//   *
//   * @param type
//   * @param eventIds
//   * @return EventData
//   * @throws Exception
//   */
//  public static EventData searchEvent(String type, List<String> eventIds) throws Exception {
//    EventData ed = new EventData();
//    if (eventIds != null && eventIds.size() > 0) {
//      List<Object> filter = new ArrayList<Object>();
//      filter.add(buildTermsFilter("_id", eventIds));
//      Map<String, Object> query = buildQuery(filter, null, null, eventIds.size(), 0, null, true);
//      String queryJsonStr = JSON.toJSONString(query);
//      String index = INDEX_PREFIX + "201*";
//      String url = ES_URL + index + "/" + type + "/_search";
//      EsResponse esResponse = searchEventResponse(url, queryJsonStr);
//      setHits(ed, type, esResponse.getHits());
//    }
//    return ed;
//  }
//
//  @SuppressWarnings("unchecked")
//  private static void setHits(EventData ed, String type, Hits hits) {
//    if (hits != null) {
//      ed.setTotal(hits.getTotal());
//      ed.setEventList(new ArrayList<AbstractEsModel>());
//      List<Hit> hitList = hits.getHits();
//      if (hitList != null && hitList.size() > 0) {
//        for (Hit hit : hitList) {
//          AbstractEsModel ae = null;
//          try {
//            if (type.equals(EVENT_TYPE_FAULTMANAGER)) {
//              ae = JSON.parseObject(hit.getSource(), FaultManager.class);
//              FaultManager fm = (FaultManager) ae;
//              fm.setFaultType(fm.getType());
//            } else if (type.equals(EVENT_TYPE_FAULTHANDING)) {
//              ae = JSON.parseObject(hit.getSource(), FaultHanding.class);
//            } else if (type.equals(EVENT_TYPE_RELEASEEVENT)) {
//              ae = JSON.parseObject(hit.getSource(), ReleaseEvent.class);
//            } else if (type.equals(EVENT_TYPE_DATARELEASEEVENT)) {
//              ae = JSON.parseObject(hit.getSource(), DataReleaseEvent.class);
//            } else if (type.equals(EVENT_TYPE_MACHINEMANAGER)) {
//              ae = JSON.parseObject(hit.getSource(), MachineManager.class);
//              MachineManager mm = (MachineManager) ae;
//              mm.setDataType(mm.getType());
//            } else if (type.equals(EVENT_TYPE_DDPRODUCT)) {
//              ae = JSON.parseObject(hit.getSource(), DataDistributionProductEvent.class);
//            } else if (type.equals(EVENT_TYPE_DATATRANSMISSIONPRODUCT)) {
//              ae = JSON.parseObject(hit.getSource(), DataTransmissionProductEvent.class);
//            } else if (type.equals(EVENT_TYPE_CTPRODUCT)) {
//              ae = JSON.parseObject(hit.getSource(), CtProductEvent.class);
//            } else if (type.equals(EVENT_TYPE_ARCHERDEPLOYPRODUCT)) {
//              ae = JSON.parseObject(hit.getSource(), ArcherDeployProductEvent.class);
//            } else if (type.equals(EVENT_TYPE_NOAHWEBDEPLOYPRODUCT)) {
//              ae = JSON.parseObject(hit.getSource(), NoahWebDeployProductEvent.class);
//            } else {
//              LOG.warn("Unknown ES type, Using default: " + hit);
//              ae = new DefaultEvent();
//            }
//          } catch (Exception e) {
//            LOG.warn("Parse Json error: " + e.getMessage() + " with id: " + hit.getId());
//            ed.setTotalErrNum(ed.getTotalErrNum() + 1);
//            continue;
//          }
//          Map<String, Object> source = JSON.parseObject(hit.getSource(), Map.class);
//          if (source.get("startTime") instanceof BigDecimal) {
//            source.put("startTime", ((BigDecimal) source.get("startTime")).longValue());
//          }
//          if (source.get("endTime") instanceof BigDecimal) {
//            source.put("endTime", ((BigDecimal) source.get("endTime")).longValue());
//          }
//          ae.setSource(source);
//          ae.setStartTime(ae.getStartTime() * 1000);
//          ae.setEndTime(ae.getEndTime() * 1000);
//          ae.setId(hit.getId());
//          ae.setIndex(hit.getIndex());
//          ae.setType(hit.getType());
//          ed.getEventList().add(ae);
//        }
//        if (ed.getTotalErrNum() > 0) {
//          LOG.warn("Get total error event num: " + ed.getTotalErrNum());
//        }
//      }
//    }
//  }
//
//  private static EsResponse searchEventResponse(String url, String queryJsonStr) throws Exception {
//    try {
//      String response = executeInEs(url, queryJsonStr);
//      EsResponse esResponse = JSON.parseObject(response, EsResponse.class);
//      Shards shards = esResponse.getShards();
//      if (shards != null) {
//        int failed = shards.getFailed();
//        if (failed > 0) {
//          int total = shards.getTotal();
//          int successful = shards.getSuccessful();
//          LOG.warn(format("Search Event shards failed: [total=%d, success=%d, failed=%d]", total, successful,
//              failed));
//        }
//      } else {
//        LOG.warn("Search Event error: no shards scan.");
//      }
//      return esResponse;
//    } catch (Exception e) {
//      LOG.error("Search Event error: " + e.getMessage(), e);
//      throw e;
//    }
//  }
//
//  public static String executeInEs(String url, String jsonStr) throws Exception {
//    try {
//      LOG.debug("Execute Event url: " + url);
//      LOG.debug("Execute Event queryStr: " + jsonStr);
//      String response = HttpClientUtil.postJson(url, jsonStr);
//      LOG.debug(format("Execute Event result: %s", Util.getLogStr(response)));
//      return response;
//    } catch (Exception e) {
//      LOG.error("Execute Event error: " + e.getMessage(), e);
//      throw e;
//    }
//  }
//
//  private static Map<String, Object> buildNormalQuery(long startTime, long endTime, Map<String, Object> terms,
//                                                      int size, int from, String sortField, boolean asc) {
//    Map<String, List<Object>> res = buildQuery(startTime, endTime, terms, size, from, sortField, asc);
//    List<Object> filter = res.get("filter");
//    List<Object> query = res.get("query");
//    Map<String, Object> queryMap = buildQuery(filter, query, null, size, from, sortField, asc);
//    return queryMap;
//  }
//
//  public static Map<String, Object> buildQuery(List<Object> filters, List<Object> queries, List<Object> shoulds,
//                                               int size, int from, String sortField, boolean asc) {
//    Map<String, Object> filteredMap = buildMap("filter", buildMap("bool", buildMap("must", filters)));
//    Map<String, Object> queryBool = null;
//    if (queries != null && queries.size() > 0) {
//      queryBool = buildMap("must", queries);
//    }
//    if (shoulds != null && shoulds.size() > 0) {
//      if (queryBool == null) {
//        queryBool = buildMap("should", shoulds);
//      } else {
//        queryBool.put("should", shoulds);
//      }
//    }
//    if (queryBool != null) {
//      filteredMap.put("query", buildMap("bool", queryBool));
//    }
//    Map<String, Object> queryMap = buildMap("query", buildMap("filtered", filteredMap));
//    if (size > 0) {
//      queryMap.put("size", size);
//    }
//    if (from > 0) {
//      queryMap.put("from", from);
//    }
//    if (sortField != null) {
//      queryMap.put("sort", buildMap(sortField, buildMap("order", asc ? "asc" : "desc")));
//    } else {
//      queryMap.put("sort", buildMap("startTime", buildMap("order", asc ? "asc" : "desc")));
//    }
//    return queryMap;
//  }
//
//  public static Map<String, List<Object>> buildQuery(long startTime, long endTime, Map<String, Object> terms,
//                                                     int size, int from, String sortField, boolean asc) {
//    List<Object> filter = new ArrayList<Object>();
//    List<Object> query = new ArrayList<Object>();
//    Map<String, Object> startTimeRangeFilter = buildRangeFilter("startTime", RANGE_EXPRESSION_LT, endTime / 1000);
//    filter.add(startTimeRangeFilter);
//    Map<String, Object> endTimeRangeFilter = buildRangeFilter("startTime", RANGE_EXPRESSION_GTE, startTime / 1000);
//    filter.add(endTimeRangeFilter);
//
//    boolean tagSizeAutoCompute = false;
//    int tagSize = 0;
//    if (terms != null && terms.size() > 0) {
//      for (String term : terms.keySet()) {
//        Object termValue = terms.get(term);
//        if (isTagSizeAutoCompute(term, termValue)) {
//          tagSizeAutoCompute = true;
//        }
//        if (termValue != null) {
//          if (isRegexSearch(termValue)) {
//            Map<String, Object> regexQuery = buildRegexQuery(term, termValue);
//            if (regexQuery != null) {
//              query.add(regexQuery);
//            }
//          } else {
//            Map<String, Object> termsFilter = buildTermsFilter(term, termValue);
//            if (termsFilter != null) {
//              filter.add(termsFilter);
//              if (TAGS.equals(term)) {
//                tagSize++;
//              }
//            }
//          }
//        }
//      }
//    }
//    if (tagSizeAutoCompute && tagSize > 0) {
//      filter.add(buildTermsFilter(TAG_SIZE, tagSize));
//    }
//
//    Map<String, List<Object>> result = new HashMap<String, List<Object>>();
//    result.put("filter", filter);
//    result.put("query", query);
//    return result;
//  }
//
//  @SuppressWarnings("unchecked")
//  private static boolean isTagSizeAutoCompute(String term, Object termValue) {
//    if (TAG_SIZE.equals(term)) {
//      Object tagSizeObj = termValue;
//      if (tagSizeObj instanceof Collection) {
//        Object ts = ((Collection<Object>) tagSizeObj).iterator().next();
//        if (ts instanceof Number && ((Number) ts).intValue() == -1) {
//          return true;
//        }
//      }
//    }
//    return false;
//  }
//
//  private static Map<String, Object> buildShouldQuery(long startTime, long endTime, Map<String, Object> terms,
//                                                      int size, int from, String sortField, boolean asc) {
//    Map<String, List<Object>> res = buildQuery(startTime, endTime, terms, size, from, sortField, asc);
//    List<Object> filter = res.get("filter");
//    List<Object> query = res.get("query");
//    Map<String, Object> queryMap = buildQuery(filter, null, query, size, from, sortField, asc);
//    return queryMap;
//  }
//
//  @SuppressWarnings("unchecked")
//  private static boolean isRegexSearch(Object searchValue) {
//    if (searchValue instanceof Collection) {
//      Collection<Object> objs = (Collection<Object>) searchValue;
//      if (objs.size() == 1) {
//        Object obj = objs.iterator().next();
//        if (obj instanceof String && ((String) obj).indexOf(".*") != -1) {
//          return true;
//        }
//      }
//    } else if (searchValue instanceof String && ((String) searchValue).indexOf(".*") != -1) {
//      return true;
//    }
//    return false;
//  }
//
//  public static Map<String, Object> buildTermsFilter(String terms, Object termValue) {
//    Map<String, Object> termsFilter = null;
//    if (termValue instanceof Collection) {
//      termsFilter = buildMap("terms", buildMap(terms, termValue));
//    } else {
//      List<Object> value = new ArrayList<Object>();
//      value.add(termValue);
//      termsFilter = buildMap("terms", buildMap(terms, value));
//    }
//    return termsFilter;
//  }
//
//  public static Map<String, Object> buildRangeFilter(String property, String expression, Object value) {
//    Map<String, Object> rangeFilter = buildMap("range", buildMap(property, buildMap(expression, value)));
//    ;
//    return rangeFilter;
//  }
//
//  public static Map<String, Object> buildLtRangeFilter(String property, Object value) {
//    return buildRangeFilter(property, RANGE_EXPRESSION_LT, value);
//  }
//
//  public static Map<String, Object> buildGtRangeFilter(String property, Object value) {
//    return buildRangeFilter(property, RANGE_EXPRESSION_GT, value);
//  }
//
//  public static Map<String, Object> buildLteRangeFilter(String property, Object value) {
//    return buildRangeFilter(property, RANGE_EXPRESSION_LTE, value);
//  }
//
//  public static Map<String, Object> buildGteRangeFilter(String property, Object value) {
//    return buildRangeFilter(property, RANGE_EXPRESSION_GTE, value);
//  }
//
//  @SuppressWarnings("unchecked")
//  public static Map<String, Object> buildRegexQuery(String key, Object regexValue) {
//    Map<String, Object> regexQuery = null;
//    if (regexValue instanceof Collection && ((Collection<Object>) regexValue).size() == 1) {
//      regexQuery = buildMap("regexp", buildMap(key, ((Collection<Object>) regexValue).iterator().next()));
//    } else {
//      regexQuery = buildMap("regexp", buildMap(key, regexValue));
//    }
//    return regexQuery;
//  }
//
//  public static final Map<String, Object> buildMap(String key, Object value) {
//    Map<String, Object> map = new HashMap<String, Object>();
//    map.put(key, value);
//    return map;
//  }
//
//  /**
//   * 根据开始结束时间，获取对应的index
//   *
//   * @param startTimeInMilliSeconds
//   * @param endTimeInMilliSeconds
//   * @return string
//   */
//  public static final String getIndex(long startTimeInMilliSeconds, long endTimeInMilliSeconds) {
//    String err =
//        format("end time[%d] must be after start time[%d].", startTimeInMilliSeconds, endTimeInMilliSeconds);
//    Preconditions.checkArgument(endTimeInMilliSeconds >= startTimeInMilliSeconds, err);
//    long now = new Date().getTime();
//    Preconditions.checkArgument(startTimeInMilliSeconds < now, "start time must be before current time.");
//    long etInMilliSeconds = Math.min(endTimeInMilliSeconds, now);
//    List<String> indexList = new ArrayList<String>();
//    SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
//    Date end = new Date(etInMilliSeconds);
//    String et = INDEX_PREFIX + format.format(end);
//
//    long time = startTimeInMilliSeconds;
//    while (time <= etInMilliSeconds) {
//      Calendar c = Calendar.getInstance();
//      c.setTimeInMillis(time);
//      // ES index time must be great or equal than 2016-06
//      if (time >= 1464710400000L) {
//        indexList.add(INDEX_PREFIX + format.format(c.getTime()));
//      }
//      c.add(Calendar.MONTH, 1);
//      time = c.getTimeInMillis();
//    }
//    if (!indexList.contains(et)) {
//      indexList.add(et);
//    }
//    String index = StringUtils.join(indexList, ",");
//    return index;
//  }
//
//  /**
//   * Group search from ES.
//   *
//   * @param type
//   * @param termField
//   * @param startTime
//   * @param endTime
//   * @return
//   * @throws Exception
//   */
//  public static final Map<String, Integer> groupSearch(String type, String termField, long startTime, long endTime)
//      throws Exception {
//    Map<String, Object> aggr = buildAggr(termField);
//    String queryJsonStr = JSON.toJSONString(aggr);
//    String index = getIndex(startTime, endTime);
//    String url = ES_URL + index + "/" + type + "/_search?search_type=count";
//    EsResponse esResponse = searchEventResponse(url, queryJsonStr);
//    List<Bucket> buckets = esResponse.getAggregations().getTmp().getBuckets();
//
//    Map<String, Integer> result = new HashMap<String, Integer>();
//    for (Bucket bk : buckets) {
//      result.put(bk.getKey(), bk.getDocCount());
//    }
//    return result;
//  }
//
//  /**
//   * Build aggregation map.
//   *
//   * @param termField
//   * @return
//   */
//  private static final Map<String, Object> buildAggr(String termField) {
//    Map<String, Object> terms = buildMap("size", 0);
//    terms.put("field", termField);
//    Map<String, Object> aggr = buildMap("aggs", buildMap("tmp", buildMap("terms", terms)));
//    return aggr;
//  }
//}

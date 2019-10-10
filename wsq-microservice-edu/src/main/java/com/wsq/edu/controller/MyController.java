package com.wsq.edu.controller;



import com.wsq.edu.entity.Book;
import com.wsq.edu.entity.EsEntity;
import com.wsq.edu.util.EsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * es7.x :
 *
 *  text：默认会进行分词，支持模糊查询（5.x之后版本string类型已废弃，请大家使用text）。
 *  keyword：不进行分词；keyword类型默认开启doc_values来加速聚合排序操作，占用了大量磁盘io 如非必须可以禁用doc_values。
 *  number：如果只有过滤场景 用不到range查询的话，使用keyword性能更佳，另外数字类型的doc_values比字符串更容易压缩。
 *  array：es不需要显示定义数组类型，只需要在插入数据时用'[]'表示即可，'[]'中的元素类型需保持一致。
 *  range：对数据的范围进行索引；目前支持 number range、date range 、ip range。
 *  boolean: 只接受true、false 也可以是字符串类型的“true”、“false”
 *  date：支持毫秒、根据指定的format解析对应的日期格式，内部以long类型存储。
 *  geo_point：存储经纬度数据对。
 *  ip：将ip数据存储在这种数据类型中，方便后期对ip字段的模糊与范围查询。
 *  nested：嵌套类型，一种特殊的object类型，存储object数组，可检索内部子项。
 *  object：嵌套类型，不支持数组。
 *
 * @author xyzzg
 * @since 2019-08-15
 */
@RestController
@RequestMapping("/edu/my")
public class MyController {


    @Autowired
    private EsUtil esUtil;

    /**
     * @param id 获取某一个
     */
    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") int id) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(new TermQueryBuilder("id", id));
        List<Book> res = esUtil.search(EsUtil.INDEX_NAME, builder, Book.class);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取全部
     */
    @GetMapping("/")
    public List<Book> getAll() {
        return esUtil.search(EsUtil.INDEX_NAME, new SearchSourceBuilder(), Book.class);
    }

    /**
     * 根据关键词搜索某用户下的书
     *
     * @param content 关键词
     */
    @GetMapping("/search")
    public List<Book> searchByUserIdAndName(int userId, String content) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("userId", userId));
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", content));
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(10).query(boolQueryBuilder);
        return esUtil.search(EsUtil.INDEX_NAME, builder, Book.class);
    }

    /**
     * 单个插入
     *
     * @param book book
     */
    @PutMapping("/")
    public void putOne(@RequestBody Book book) {
        EsEntity<Book> entity = new EsEntity<>(book.getId().toString(), book);
        esUtil.insertOrUpdateOne(EsUtil.INDEX_NAME, entity);
    }

    /**
     * 批量插入
     *
     * @param books books
     */
    @PutMapping("/many")
    public void putList(@RequestBody List<Book> books) {
        List<EsEntity> list = new ArrayList<>();
        books.forEach(item -> list.add(new EsEntity<>(item.getId().toString(), item)));
        esUtil.insertBatch(EsUtil.INDEX_NAME, list);
    }

    /**
     * 批量删除
     *
     * @param list list
     */
    @DeleteMapping("/deleteBatch")
    public void deleteBatch(List<Integer> list) {
        esUtil.deleteBatch(EsUtil.INDEX_NAME, list);
    }

    /**
     * delete by query 根据用户id删除数据
     *
     * @param userId userId
     */
    @DeleteMapping("/userId/{userId}")
    public void deleteByUserId(@PathVariable("userId") int userId) {
        esUtil.deleteByQuery(EsUtil.INDEX_NAME, new TermQueryBuilder("userId", userId));
    }





//    @GetMapping("/order/getById/{id}")
//    public Map<String,Object> getOrder(@PathVariable("id")String id) {
//        GetRequest getRequest = new GetRequest("order", "_doc", id);
//        Map map = new HashMap();
//        GetResponse response = null;
//        try {
//            response = client.get(getRequest, RequestOptions.DEFAULT);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (response.isExists()) {
//            map.put("success", true);
//            map.put("data", response.getSource());
//        } else {
//            map.put("success", false);
//        }
//        return map;
//    }
}


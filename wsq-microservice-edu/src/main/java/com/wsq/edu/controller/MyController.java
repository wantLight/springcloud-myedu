package com.wsq.edu.controller;


import com.wsq.edu.rabbitmq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private Sender sender;

    @RequestMapping("/send/{name}")
    public String helloworld(@PathVariable String name) {
        return sender.send(name);
    }
}


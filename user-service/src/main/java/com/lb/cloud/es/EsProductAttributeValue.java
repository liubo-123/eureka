//package com.lb.cloud.es;
//
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.io.Serializable;
//
///**
// * 搜索中的商品属性信息
// * Created by macro on 2018/6/27.
// */
//public class EsProductAttributeValue implements Serializable {
//    private static final long serialVersionUID = 1L;
//    private Long id;
//    @Field(type=FieldType.Keyword)
//    private String name;
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//
//
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}

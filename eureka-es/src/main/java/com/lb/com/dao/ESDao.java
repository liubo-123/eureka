package com.lb.com.dao;

import com.lb.com.dto.ESInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ESDao {
    List<ESInfo> getAllEsProductList(Integer id);
}

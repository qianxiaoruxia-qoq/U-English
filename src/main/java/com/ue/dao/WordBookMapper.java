package com.ue.dao;

import com.ue.pojo.WordBook;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface WordBookMapper extends Mapper<WordBook> {
    List<WordBook> selectLearnWordBooks(Map<String, Integer> map);

    List<WordBook> selectNotLearnWordBooks(Integer userId);
}

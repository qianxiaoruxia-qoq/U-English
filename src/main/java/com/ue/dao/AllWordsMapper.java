package com.ue.dao;

import com.ue.pojo.AllWords;
import com.ue.pojo.NewWord;
import com.ue.pojo.Symbol;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface AllWordsMapper extends Mapper<AllWords> {
    List<AllWords> findWordsByBookId(Map<String, Integer> map);

    List<AllWords> findBookWordById(Integer bookId);

    List<AllWords> selectNewWordByUserId(Integer userId);
}

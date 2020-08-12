package com.ue.dao;

import com.ue.pojo.Article;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ArticleMapper extends Mapper<Article> {
    List<Article> selectPageByUserIdAndSign(Map<String, Integer> map);

    Integer countByUserIdAndSign(Map<String, Integer> map);
}

package com.ue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ue.dao.AllWordsMapper;
import com.ue.dao.ArticleMapper;
import com.ue.pojo.AllWords;
import com.ue.pojo.Article;
import com.ue.service.AllWordsService;
import com.ue.service.ArticleService;
import com.ue.service.SymbolService;
import com.ue.util.PageResult;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("allWordsService")
public class AllWordsServiceImpl implements AllWordsService {

    @Autowired
    private AllWordsMapper allWordsMapper;

    @Override
    public List<AllWords> findWordsByBookId(Integer bookId, Integer start) {
        Map<String, Integer> map = new HashMap<>();
        map.put("bookId", bookId);
        map.put("start", start);
        return allWordsMapper.findWordsByBookId(map);
    }

    @Override
    public List<AllWords> findWords() {
        PageHelper.startPage(1, 10);
        //过滤
        Example example = new Example(AllWords.class);
        return allWordsMapper.selectByExample(example);
    }

    @Override
    public List<AllWords> findWordsByName(String wordEnglish) {
        Condition condition = new Condition(AllWords.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("wordEnglish", wordEnglish);
        return allWordsMapper.selectByExample(condition);
    }

    @Override
    public List<AllWords> findBookWordById(Integer bookId) {
        return allWordsMapper.findBookWordById(bookId);
    }

    @Override
    public List<AllWords> findNewWordByUserId(Integer userId) {
        return allWordsMapper.selectNewWordByUserId(userId);
    }
}

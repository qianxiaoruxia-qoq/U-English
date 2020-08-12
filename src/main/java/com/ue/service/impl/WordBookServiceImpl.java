package com.ue.service.impl;

import com.github.pagehelper.PageHelper;
import com.ue.dao.AllWordsMapper;
import com.ue.dao.WordBookMapper;
import com.ue.pojo.AllWords;
import com.ue.pojo.WordBook;
import com.ue.service.AllWordsService;
import com.ue.service.WordBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("wordBookService")
public class WordBookServiceImpl implements WordBookService {

    @Autowired
    private WordBookMapper wordBookMapper;

    @Override
    public List<WordBook> findLearnWordBooks(Integer userId, Integer islearn) {
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", userId);
        map.put("islearn", islearn);
        return wordBookMapper.selectLearnWordBooks(map);
    }

    @Override
    public List<WordBook> findNotLearnWordBooks(Integer userId) {
        return wordBookMapper.selectNotLearnWordBooks(userId);
    }
}

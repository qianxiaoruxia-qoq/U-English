package com.ue.service.impl;

import com.ue.dao.NewWordMapper;
import com.ue.pojo.AllWords;
import com.ue.pojo.NewWord;
import com.ue.service.NewWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("newWordService")
public class NewWordServiceImpl implements NewWordService {

    @Autowired
    private NewWordMapper newWordMapper;

    @Override
    public void addNewWord(NewWord newWord) {
        newWordMapper.insert(newWord);
    }
}

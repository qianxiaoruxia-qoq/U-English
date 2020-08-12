package com.ue.service.impl;

import com.ue.dao.LearnBookMapper;
import com.ue.pojo.LearnBook;
import com.ue.service.LearnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("learnBookService")
public class LearnBookServiceImpl implements LearnBookService {

    @Autowired
    private LearnBookMapper learnBookMapper;

    @Override
    public List<LearnBook> findByUserId(Integer userId, Integer islearn) {
        Condition condition = new Condition(LearnBook.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("islearn", islearn);
        condition.orderBy("createTime");
        return learnBookMapper.selectByExample(condition);
    }

    @Override
    public void saveLearnBook(LearnBook learnBook) {
        learnBookMapper.insert(learnBook);
    }

    @Override
    public void updateLearnBook(LearnBook learnBook) {
        learnBookMapper.updateByPrimaryKey(learnBook);
    }

    @Override
    public List<LearnBook> findByUserIdAndBookId(Integer userId, Integer bookId) {
        Condition condition = new Condition(LearnBook.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("bookId", bookId);
        return learnBookMapper.selectByExample(condition);
    }
}

package com.ue.service.impl;

import com.ue.dao.LearnBookMapper;
import com.ue.dao.LearnCourseMapper;
import com.ue.pojo.LearnBook;
import com.ue.pojo.LearnCourse;
import com.ue.service.LearnBookService;
import com.ue.service.LearnCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("learnCourseService")
public class LearnCourseServiceImpl implements LearnCourseService {

    @Autowired
    private LearnCourseMapper learnCourseMapper;

    @Override
    public void saveLearnCourse(LearnCourse learnCourse) {
        learnCourseMapper.insert(learnCourse);
    }

    @Override
    public LearnCourse findByUserIdAndCourseId(Integer userId, Integer courseId) {
        Condition condition = new Condition(LearnCourse.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("courseId", courseId);
        List<LearnCourse> learnCourses = learnCourseMapper.selectByExample(condition);
        return learnCourses.size() == 0 ? null : learnCourses.get(0);
    }
}

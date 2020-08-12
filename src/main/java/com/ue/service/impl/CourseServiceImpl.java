package com.ue.service.impl;

import com.ue.dao.CourseMapper;
import com.ue.dao.LearnBookMapper;
import com.ue.pojo.Course;
import com.ue.pojo.LearnBook;
import com.ue.service.CourseService;
import com.ue.service.LearnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findByFlag(Integer flag) {
        Condition condition = new Condition(Course.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("flag", flag);
        return courseMapper.selectByExample(condition);
    }

    @Override
    public Course findById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Course> findMyCourseByUserId(Integer userId) {
        return courseMapper.selectMyCourseByUserId(userId);
    }
}

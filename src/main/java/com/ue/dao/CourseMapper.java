package com.ue.dao;

import com.ue.pojo.Course;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CourseMapper extends Mapper<Course> {
    List<Course> selectMyCourseByUserId(Integer id);
}

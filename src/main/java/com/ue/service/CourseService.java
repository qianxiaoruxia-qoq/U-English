package com.ue.service;

import com.ue.pojo.Course;

import java.util.List;

public interface CourseService {
    List<Course> findByFlag(Integer flag);

    Course findById(Integer id);

    List<Course> findMyCourseByUserId(Integer userId);
}

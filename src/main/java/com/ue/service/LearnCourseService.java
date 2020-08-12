package com.ue.service;

import com.ue.pojo.LearnCourse;

public interface LearnCourseService {
    void saveLearnCourse(LearnCourse learnCourse);

    LearnCourse findByUserIdAndCourseId(Integer userId, Integer courseId);
}

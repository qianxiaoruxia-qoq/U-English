package com.ue.service;

import com.ue.pojo.LearnBook;

import java.util.List;

public interface LearnBookService {
    List<LearnBook> findByUserId(Integer userId, Integer islearn);

    void saveLearnBook(LearnBook learnBook);

    void updateLearnBook(LearnBook learnBook);

    List<LearnBook> findByUserIdAndBookId(Integer userId, Integer bookId);
}

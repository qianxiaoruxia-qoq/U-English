package com.ue.service;

import com.ue.pojo.WordBook;

import java.util.List;

public interface WordBookService {
    List<WordBook> findLearnWordBooks(Integer userId, Integer islearn);

    List<WordBook> findNotLearnWordBooks(Integer userId);
}

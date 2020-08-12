package com.ue.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImageResult {

    // 错误代码，0 表示没有错误。
    private Integer errno;
    // 已上传的图片路径
    private List<String> data = new ArrayList<>();
}

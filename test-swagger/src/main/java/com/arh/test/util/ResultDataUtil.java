package com.arh.test.util;

import com.arh.test.model.ResultData;

/**
 * @author: chenli
 * @Date: 2021/5/7 17:22
 */
public class ResultDataUtil {

    public static ResultData newSuccessInstance(Object obj) {
        return new ResultData(0, "success", obj);
    }

    public static ResultData newErrorInstance(Object obj) {
        return new ResultData(1, "error", obj);
    }
}

package com.arh.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: chenli
 * @Date: 2021/5/7 17:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultData implements Serializable {

    private int code;

    private String msg;

    private Object obj;

}

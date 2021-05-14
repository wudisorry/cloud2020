package com.arh.test.controller;

import com.arh.test.model.ResultData;
import com.arh.test.util.ResultDataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2021/5/7 17:03
 */
@RestController
@RequestMapping("/msg")
@Api(value = "ShowMsgController介绍")
public class ShowMsgController {

    @GetMapping("/show/{msg}")
    @ApiOperation(value = "显示信息", notes = "显示信息note")
    @ApiImplicitParams(@ApiImplicitParam(paramType = "String", name = "msg"))
    public ResultData showMsg(@PathVariable String msg) {
        return ResultDataUtil.newSuccessInstance(msg);
    }
}

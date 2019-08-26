package com.wsq.ucenter.controller.admin;

import com.wsq.common.vo.R;
import com.wsq.ucenter.service.MemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-26 21:08
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/ucenter/member")
public class MemberAdminController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "今日注册数")
    @GetMapping(value = "count-register/{day}")
    public R registerCount(
            @ApiParam(name = "day", value = "统计日期")
            @PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }
}

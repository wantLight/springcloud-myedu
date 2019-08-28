package com.wsq.statistics.controller.admin;

import com.wsq.common.vo.R;
import com.wsq.statistics.service.DailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-28 18:27
 */
@Api(description="统计分析")
@CrossOrigin
@RestController
@RequestMapping("/admin/statistics/daily")
public class DailyAdminController {

    @Autowired
    private DailyService dailyService;

    @ApiOperation(value = "每天注册数")
    @ApiParam(name = "day", value = "日期", required = true)
    @GetMapping("{day}")
    public R createStatisticsByDate(@PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }

    @GetMapping("show-chart/{begin}/{end}/{type}")
    public R showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return R.ok().data(map);
    }
}

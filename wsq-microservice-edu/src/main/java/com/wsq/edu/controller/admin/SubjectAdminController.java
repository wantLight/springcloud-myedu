package com.wsq.edu.controller.admin;

import com.wsq.common.constants.ResultCodeEnum;
import com.wsq.common.exception.MyException;
import com.wsq.common.vo.R;
import com.wsq.edu.service.SubjectService;
import com.wsq.edu.vo.SubjectNestedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-21 20:46
 */
@Api(description="课程分类管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/edu/subject")
public class SubjectAdminController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("import")
    public R batchImport(
            @ApiParam(name = "file", value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) {

        try{
            List<String> errorMsg = subjectService.batchImport(file);
            if(errorMsg.size() == 0){
                return R.ok().message("批量导入成功");
            }else{
                return R.error().message("部分数据导入失败").data("errorMsgList", errorMsg);
            }
        }catch (Exception e){
            //无论哪种异常，只要是在excel导入时发生的，一律用转成MyException抛出
            throw new MyException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("")
    public R nestedList(){

        List<SubjectNestedVo> subjectNestedVoList = subjectService.nestedList();
        return R.ok().data("items", subjectNestedVoList);
    }

}

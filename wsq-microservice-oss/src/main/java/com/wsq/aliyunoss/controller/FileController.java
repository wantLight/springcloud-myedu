package com.wsq.aliyunoss.controller;

import com.wsq.aliyunoss.service.FileService;
import com.wsq.aliyunoss.util.ConstantPropertiesUtil;
import com.wsq.common.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-20 18:39
 */
@Api(description="阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/oss/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file,

            @ApiParam(name = "host", value = "文件上传路径", required = false)
            @RequestParam(value = "host", required = false) String host) {

        if(!StringUtils.isEmpty(host)){
            ConstantPropertiesUtil.FILE_HOST = host;
        }
        String uploadUrl = fileService.upload(file);
        //返回r对象
        return R.ok().message("文件上传成功").data("url", uploadUrl);

    }
}

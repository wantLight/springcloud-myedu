package com.wsq.edu.service;

import com.wsq.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wsq.edu.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-15
 */
public interface SubjectService extends IService<Subject> {

    List<String> batchImport(MultipartFile file) throws Exception;

    List<SubjectNestedVo> nestedList();
}

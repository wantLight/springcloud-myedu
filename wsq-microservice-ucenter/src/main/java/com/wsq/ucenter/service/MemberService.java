package com.wsq.ucenter.service;

import com.wsq.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-26
 */
public interface MemberService extends IService<Member> {

    Integer countRegisterByDay(String day);

}

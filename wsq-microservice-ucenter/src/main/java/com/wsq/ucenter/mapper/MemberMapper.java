package com.wsq.ucenter.mapper;

import com.wsq.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-26
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer selectRegisterCount(String day);

}

package com.wsq.ucenter.service.impl;

import com.wsq.ucenter.entity.Member;
import com.wsq.ucenter.mapper.MemberMapper;
import com.wsq.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-26
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public Integer countRegisterByDay(String day) {
        return baseMapper.selectRegisterCount(day);
    }

}

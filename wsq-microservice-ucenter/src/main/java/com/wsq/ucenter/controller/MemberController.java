package com.wsq.ucenter.controller;


import com.wsq.common.vo.R;
import com.wsq.ucenter.util.JwtUtils;
import com.wsq.ucenter.vo.LoginInfoVo;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xyzzg
 * @since 2019-08-26
 */
@RestController
@RequestMapping("/ucenter/member")
public class MemberController {

    @PostMapping("parse-jwt")
    @ResponseBody
    public R getLoginInfoByJwtToken(@RequestBody String jwtToken){

        Claims claims = JwtUtils.checkJWT(jwtToken);

        String id = (String)claims.get("id");
        String nickname = (String)claims.get("nickname");
        String avatar = (String)claims.get("avatar");

        LoginInfoVo loginInfoVo = new LoginInfoVo();
        loginInfoVo.setId(id);
        loginInfoVo.setNickname(nickname);
        loginInfoVo.setAvatar(avatar);

        return R.ok().data("loginInfo", loginInfoVo);

    }
}


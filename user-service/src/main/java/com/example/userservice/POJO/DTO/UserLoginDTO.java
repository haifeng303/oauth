package com.example.userservice.POJO.DTO;

import com.example.userservice.POJO.DO.JWT;
import com.example.userservice.POJO.EO.UserEO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author rhf30
 * @Title: haifeng
 * @ProjectName oauth
 * @date 2019/5/2911:10
 * @Description: TODO
 */
@Data
public class UserLoginDTO implements Serializable {
    private JWT jwt;
    private UserEO user;
}

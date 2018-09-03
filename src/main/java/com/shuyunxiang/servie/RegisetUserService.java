package com.shuyunxiang.servie;

import com.shuyunxiang.pojo.Result;
import com.shuyunxiang.pojo.UserAdmin;

public interface RegisetUserService {

    //判断用户名是否可用
    Result getResult(String username);

    //进行注册操作
    Result insertUser(UserAdmin userAdmin);

}

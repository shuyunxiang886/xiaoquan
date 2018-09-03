package com.shuyunxiang.servie.servletImpl;

import com.shuyunxiang.dao.UserMapper;
import com.shuyunxiang.pojo.Result;
import com.shuyunxiang.pojo.User;
import com.shuyunxiang.servie.LoginUserService;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result loginUser(User user) {

        //查询出真实user
        User realerUser = userMapper.getUser(user.getUsername());

        try {
            if (realerUser.getPassword().equals(user.getPassword())) {
                Result result = new Result();
                result.setFlag(1);
                return result;
            }else{
                Result result = new Result();
                result.setFlag(0);
                result.setMessage("用户名或密码错误");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result();
            result.setFlag(0);
            result.setMessage("用户名或密码错误");
            return result;
        }
    }
}

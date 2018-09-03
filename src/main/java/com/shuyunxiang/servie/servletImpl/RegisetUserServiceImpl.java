package com.shuyunxiang.servie.servletImpl;

import com.shuyunxiang.dao.UserMapper;
import com.shuyunxiang.pojo.Result;
import com.shuyunxiang.pojo.User;
import com.shuyunxiang.pojo.UserAdmin;
import com.shuyunxiang.servie.RegisetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisetUserServiceImpl implements RegisetUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result getResult(String username) {
        //通过正则表达式判断用户名是否符合规范,然后回显信息
        return getResultByRegEx(username);

    }

    @Override
    public Result insertUser(UserAdmin userAdmin) {

        //获取请求表单数据
        String username = userAdmin.getUsername();
        String pwd1 = userAdmin.getPwd1();
        String pwd2 = userAdmin.getPwd2();

        //对账户再次判断,确保数据是符合要求
        Result resultByRegEx = getResultByRegEx(username);
        if (resultByRegEx.getFlag()==0){
            return resultByRegEx;
        }

        //对密码进行判断是否为6位数字
        if (pwd1.length()==6) {

            //判断密码是否符合正则表达式
            String regEx = "^[0-9]*$";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(pwd1);
            boolean rs = matcher.matches();
            if (rs==true) {
                if (pwd1.equals(pwd2)) {
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(pwd1);
                    userMapper.insertUser(user);

                    Result result = new Result();
                    result.setFlag(1);
                    result.setPasswordMessage("注册成功");
                    return result;
                }else {
                    Result result = new Result();
                    result.setFlag(0);
                    result.setPasswordMessage("两次密码输入不一致,请重新输入");
                    return result;
                }
            }else {
                Result result = new Result();
                result.setFlag(0);
                result.setPasswordMessage("只能输入6位数字作为密码");
                return result;
            }

        }else{
            Result result = new Result();
            result.setFlag(0);
            result.setPasswordMessage("只能输入6位数字作为密码");
            return result;
        }
    }

    /**
     * 通过正则表达式判断用户名是否符合规范
     * @param username
     * @return
     */
    private Result getResultByRegEx(String username) {
        //判断用户名位数是否符合规范
        if (username.length()>=10&&username.length()<=20){
            String regEx = "^[A-Za-z0-9]+$";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(username);
            boolean rs = matcher.matches();

            //位数规范,判断是否符合正则表达式
            if (rs==true) {
                //符合正则表达式,进行判断用户名是否存在
                Result resultByUserIsOrNotExist = getResultByUserIsOrNotExist(username);
                return resultByUserIsOrNotExist;
            }else {
                Result result = new Result();
                result.setFlag(0);
                result.setMessage("用户名格式不正确,请输入10到20位英文和数字");
                return result;
            }

        }else {
            Result result = new Result();
            result.setFlag(0);
            result.setMessage("用户名格式不正确,请输入10到20位英文和数字");
            return result;
        }
    }

    /**
     * 通过查询判断用户是否已经被注册
     * @param username
     * @return 返回结果信息显示在页面上
     */
    private Result getResultByUserIsOrNotExist(String username) {
        //查询出来是否有已存在的用户
        User user = userMapper.getUser(username);

        //用来封装返回信息
        Result result = new Result();

        //判断用户名是否存在
        if (null==user) {
            result.setFlag(1);
            result.setMessage("用户名可以使用");
        } else{
            result.setFlag(0);
            result.setMessage("用户名已存在");
        }
        return result;
    }

}

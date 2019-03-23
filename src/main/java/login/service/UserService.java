package login.service;

import login.entity.User;



public interface UserService {
    //通过用户名密码查询用户
    User selectUserByNP(String user_name,String password);
    //通过用户名查询用户
    User selectUserByName(String user_name);
    /* 新建用户 */
    int createUser(User user);
}

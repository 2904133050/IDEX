package login.service.impl;

import login.entity.User;
import login.mapper.UserMapper;
import login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int createUser(User user) {
        return userMapper.createUser(user);
    }

    @Override
    public User selectUserByNP(String user_name, String password) {
        return userMapper.selectUserByNP(user_name, password);
    }

    @Override
    public User selectUserByName(String user_name) {
        return userMapper.selectUserByName(user_name);
    }
}

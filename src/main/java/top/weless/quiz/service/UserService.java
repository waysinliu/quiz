package top.weless.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import top.weless.quiz.dao.UserMapper;
import top.weless.quiz.entity.User;

@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    public User queryUserByUserId(Long userId) {
        return mapper.selectUsersByUserId(userId);
    }

    public User queryUserByEmail(String email) {
        return mapper.selectUsersByEmail(email);
    }

    public int createUser(User user) {
        user.setName(user.getEmail());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return mapper.insertUsers(user);
    }
}

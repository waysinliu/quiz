package top.weless.quiz.dao;

import top.weless.quiz.entity.User;

public interface UserMapper {
    User selectUsersByUserId(Long userId);

    User selectUsersByEmail(String email);

    int insertUsers(User user);
}

package top.weless.quiz.controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.weless.quiz.common.CommonException;
import top.weless.quiz.common.CommonResult;
import top.weless.quiz.entity.User;
import top.weless.quiz.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private StringRedisTemplate template;

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody User user) {
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user = service.queryUserByEmail(user.getEmail());
        if (user == null) {
            throw new CommonException("邮箱尚未注册.");
        }
        if (!user.getPassword().equals(password)) {
            throw new CommonException("邮箱与密码不匹配.");
        }
        String token = UUID.randomUUID().toString();
        template.opsForValue().set(token, user.getUserId().toString(), 1, TimeUnit.HOURS);
        return CommonResult.success(token);
    }

    @PostMapping("/signup")
    public Object signup(@RequestBody User user) {
        if (service.createUser(user) == 0) {
            throw new CommonException("注册失败.");
        }
        return login(user);
    }

    @GetMapping
    public CommonResult<User> getUserInfo(@RequestHeader("token") String token) {
        Long userId = Long.valueOf(template.opsForValue().get(token));
        User user = service.queryUserByUserId(userId);
        if (user == null) {
            throw new CommonException("用户不存在.");
        }
        return CommonResult.success(user);
    }

    @GetMapping("/test")
    public CommonResult<String> test() {
        return CommonResult.success("Test");
    }
}

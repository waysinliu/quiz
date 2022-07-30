package top.weless.quiz.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("top.weless.quiz.dao")
public class MyBatisConfig {
}

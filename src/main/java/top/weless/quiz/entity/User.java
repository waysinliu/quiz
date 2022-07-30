package top.weless.quiz.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;

@Data
public class User {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String name;
    private String email;
    private String password;
}

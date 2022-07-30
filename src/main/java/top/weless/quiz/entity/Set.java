package top.weless.quiz.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;

@Data
public class Set {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long setId;
    private String title;
    private String description;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private Integer count;
}

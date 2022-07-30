package top.weless.quiz.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;

@Data
public class Card {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long cardId;
    private String front;
    private String back;
    private Short progress;
    private LocalDate nextStudyDate;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long setId;
}

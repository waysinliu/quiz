package top.weless.quiz.dao;

import java.util.List;

import top.weless.quiz.entity.Set;

public interface SetMapper {
    Set selectSetsBySetId(Long setId);

    List<Set> selectSetsByUserId(Long userId);

    int updateSetsBySetId(Set set);
}

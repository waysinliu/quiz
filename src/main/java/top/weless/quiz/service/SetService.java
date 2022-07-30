package top.weless.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.weless.quiz.dao.SetMapper;
import top.weless.quiz.entity.Set;

@Service
public class SetService {
    @Autowired
    private SetMapper mapper;

    public Set querySetBySetId(Long setId) {
        return mapper.selectSetsBySetId(setId);
    }

    public List<Set> querySetsByUserId(Long userId) {
        return mapper.selectSetsByUserId(userId);
    }

    public int updateSetBySetId(Set set) {
        return mapper.updateSetsBySetId(set);
    }
}

package top.weless.quiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.weless.quiz.common.CommonException;
import top.weless.quiz.common.CommonResult;
import top.weless.quiz.entity.Set;
import top.weless.quiz.service.SetService;

@RestController
@RequestMapping("/set")
public class SetController {
    @Autowired
    private SetService service;
    @Autowired
    private StringRedisTemplate template;

    @GetMapping("/{setId}")
    public CommonResult<Set> getSetInfo(@PathVariable String setId) {
        Set set = service.querySetBySetId(Long.valueOf(setId));
        if (set == null) {
            throw new CommonException("集合不存在.");
        }
        return CommonResult.success(set);
    }

    @GetMapping
    public CommonResult<List<Set>> getSetList(@RequestHeader("token") String token) {
        Long userId = Long.valueOf(template.opsForValue().get(token));
        List<Set> sets = service.querySetsByUserId(userId);
        if (sets == null) {
            throw new CommonException("集合不存在.");
        }
        return CommonResult.success(sets);
    }

    @PutMapping
    public CommonResult<Integer> getSetInfo(@RequestBody Set set) {
        int res = service.updateSetBySetId(set);
        if (res == 0) {
            throw new CommonException("修改失败.");
        }
        return CommonResult.success(res);
    }
}

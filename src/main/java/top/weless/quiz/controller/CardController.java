package top.weless.quiz.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.weless.quiz.common.CommonException;
import top.weless.quiz.common.CommonResult;
import top.weless.quiz.entity.Card;
import top.weless.quiz.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService service;

    @GetMapping("/{setId}")
    public CommonResult<List<Card>> getCardList(@PathVariable String setId) {
        List<Card> cards = service.queryCardsBySetId(Long.valueOf(setId));
        if (cards == null) {
            throw new CommonException("卡组不存在.");
        }
        return CommonResult.success(cards);
    }

    @GetMapping("/{setId}/study")
    public CommonResult<List<Card>> getStudyList(@PathVariable String setId) {
        List<Card> cards = service.queryCardsBySetIdBeforeToday(Long.valueOf(setId));
        if (cards == null) {
            throw new CommonException("卡组不存在.");
        }
        return CommonResult.success(cards);
    }

    @PostMapping
    public CommonResult<Card> createCard(@RequestBody Card card) {
        if (service.createCard(card) == 0) {
            throw new CommonException("创建失败.");
        }
        List<Card> cards = service.queryCardsBySetId(card.getSetId());
        return CommonResult.success(cards.get(cards.size() - 1));
    }

    @PutMapping
    public CommonResult<Integer> updateCard(@RequestBody Card card) {
        int res = service.updateCardByCardId(card);
        if (res == 0) {
            throw new CommonException("修改失败.");
        }
        return CommonResult.success(res);
    }

    @PutMapping("/progress")
    public CommonResult<Integer> updateCardProgress(@RequestBody Card card) {
        LocalDate date = LocalDate.now();
        switch (card.getProgress()) {
            case 0:
            case 1:
                date = date.plusDays(1);
                break;
            case 2:
                date = date.plusDays(2);
                break;
            case 3:
                date = date.plusDays(4);
                break;
            case 4:
                date = date.plusWeeks(1);
                break;
            case 5:
                date = date.plusWeeks(2);
                break;
            case 6:
                date = date.plusMonths(1);
                break;
            case 7:
                date = date.plusMonths(3);
                break;
            default:
                date = date.plusMonths(6);
                break;
        }
        card.setNextStudyDate(date);
        int res = service.updateCardByCardId(card);
        if (res == 0) {
            throw new CommonException("修改失败.");
        }
        return CommonResult.success(res);
    }

    @DeleteMapping("/{cardId}")
    public CommonResult<Integer> deleteCard(@PathVariable String cardId) {
        int res = service.deleteCardByCardId(Long.valueOf(cardId));
        if (res == 0) {
            throw new CommonException("删除失败.");
        }
        return CommonResult.success(res);
    }
}

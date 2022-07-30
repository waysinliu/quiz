package top.weless.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.weless.quiz.dao.CardMapper;
import top.weless.quiz.entity.Card;

@Service
public class CardService {
    @Autowired
    private CardMapper mapper;

    public List<Card> queryCardsBySetId(Long setId) {
        return mapper.selectCardsBySetId(setId);
    }

    public List<Card> queryCardsBySetIdBeforeToday(Long setId) {
        return mapper.selectCardsBySetIdBeforeToday(setId);
    }

    public int createCard(Card card) {
        return mapper.insertCards(card);
    }

    public int updateCardByCardId(Card card) {
        return mapper.updateCardsByCardId(card);
    }

    public int updateCardProgressByCardId(Card card) {
        return mapper.updateCardsByCardId(card);
    }

    public int deleteCardByCardId(Long cardId) {
        return mapper.deleteCardsByCardId(cardId);
    }
}

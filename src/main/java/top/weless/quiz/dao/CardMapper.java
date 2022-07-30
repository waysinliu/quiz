package top.weless.quiz.dao;

import java.util.List;

import top.weless.quiz.entity.Card;

public interface CardMapper {
    List<Card> selectCardsBySetId(Long setId);

    List<Card> selectCardsBySetIdBeforeToday(Long setId);

    int insertCards(Card card);

    int updateCardsByCardId(Card card);

    int deleteCardsByCardId(Long cardId);
}

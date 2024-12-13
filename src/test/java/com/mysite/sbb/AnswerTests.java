package com.mysite.sbb;

import com.mysite.sbb.Answer.Answer;
import com.mysite.sbb.Answer.AnswerRepository;
import com.mysite.sbb.Question.Question;
import com.mysite.sbb.Question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AnswerTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    @DisplayName("답글 존재 테스트")
    void t001() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(1, a.getQuestion().getId());
    }

    @Transactional
    @Test
    @DisplayName("답글 존재 테스트2 (질문글을 통해)")
    void t002() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("sbb는 sbb입니다.", answerList.get(0).getContent());
    }
}

package com.mysite.sbb;

import com.mysite.sbb.Answer.Answer;
import com.mysite.sbb.Answer.AnswerRepository;
import com.mysite.sbb.Question.Question;
import com.mysite.sbb.Question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class CreateNewAnswer {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    @DisplayName("답글 생성")
    void t001() {
        Optional<Question> oq = this.questionRepository.findById(1);
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("sbb는 sbb입니다.");
        a.setCreateDate(LocalDateTime.now());
        a.setQuestion(q);
        this.answerRepository.save(a);
    }
}

package com.mysite.sbb;

import com.mysite.sbb.Question.QuestionRepository;
import com.mysite.sbb.Question.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CreateNewQuestion {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("질문 게시글 추가")
    void t001() {
        Question q = new Question();
        q.setSubject("테스트 질문글");
        q.setContent("테스트 질문글 내용");
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }

}

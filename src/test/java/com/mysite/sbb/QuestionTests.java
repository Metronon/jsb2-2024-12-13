package com.mysite.sbb;

import com.mysite.sbb.Question.Question;
import com.mysite.sbb.Question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class QuestionTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    @DisplayName("목록 ALL 조회 테스트")
    void t001() {
        List<Question> lq = this.questionRepository.findAll();
        assertEquals(4, lq.size());

        Question q = lq.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    @DisplayName("특정 게시글 조회 테스트")
    void t002() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }

    @Test
    @DisplayName("제목으로 조회 테스트")
    void t003() {
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("제목과 내용으로 조회 테스트")
    void t004() {
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?",
                "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("질문이 제목 일부를 포함하는지를 테스트")
    void t005() {
        Question q = this.questionRepository.findBySubjectLike("%sbb%");
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("질문글의 제목 수정")
    void t006() {
        Optional<Question> oq = this.questionRepository.findById(3);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정 테스트");
        this.questionRepository.save(q);
    }

    @Test
    @DisplayName("수정 테스트 검증")
    void t007() {
        Optional<Question> oq = this.questionRepository.findById(3);
        Question q = oq.get();
        assertEquals("수정 테스트", q.getSubject());
    }

    @Test
    @DisplayName("삭제 테스트")
    void t008() {
        Optional<Question> oq = this.questionRepository.findById(4);
        Question q = oq.get();
        this.questionRepository.delete(q);
    }

    @Test
    @DisplayName("삭제 테스트 검증")
    void t009() {
        Optional<Question> oq = this.questionRepository.findById(4);
        assertTrue(!oq.isPresent());
    }
}
package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mallapi.domain.Todo;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class TodoRepsotiryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void test1() {

        Assertions.assertNotNull(todoRepository);

        log.info("TEST :: {}", todoRepository.getClass().getName());

    }

    @Test
    void testInsert() {

        for (int i = 0; i < 100; i++) {

            final Todo todo = Todo.builder().title("Title.." + i).content("Content ...." + i).dueDate(LocalDate.of(2023, 12, 30)).build();
            Todo result = todoRepository.save(todo);
            log.info("RESULT :: {} ", result);
        }


    }

    @Test
    void testRead() {

        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        log.info("READ :: {} ", todo);

    }

    @Test
    public void testUpdate() {

        // 먼저 로딩 하고 엔티티 객체를 변경 /setter
        Long tno = 1L;

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();

        todo.changeTitle("Update Title");
        todo.changeContent("Update content");
        todo.changeCompleted(true);

        todoRepository.save(todo);

    }

    @Test
    public void testPaging() {

        // 페이지 번호는 0부터
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        log.info("PAGE :: {}", result.getTotalElements());

        log.info("RESULT :: {}", result.getContent());
    }

    /*@Test
    void testSearch1() {
        todoRepository.search1();
    }*/


}

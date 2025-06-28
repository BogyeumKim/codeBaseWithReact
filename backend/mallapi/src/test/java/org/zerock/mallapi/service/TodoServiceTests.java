package org.zerock.mallapi.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    TodoService todoService;

    @Test
    public void testGet() {
        Long tno = 50L;
        TodoDTO todoDTO = todoService.get(50L);
        log.info("TODO DTO : {}", todoDTO);
    }


    @Test
    void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder().title("Title ...")
                .content("Content ...")
                .dueDate(LocalDate.now())
                .build();

        Long register = todoService.register(todoDTO);
        log.info("REGISTER : {}", register);
    }

    @Test
    void testGetList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(11).build();

        PageResponseDTO<TodoDTO> list = todoService.getList(pageRequestDTO);
        log.info("LIST : {}", list);
    }
}

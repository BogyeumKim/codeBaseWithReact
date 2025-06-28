package org.zerock.mallapi.service;


import org.springframework.transaction.annotation.Transactional;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

@Transactional
public interface TodoService {

    TodoDTO get(Long tno);

    Long register(TodoDTO dto);

    void modify(TodoDTO dto);

    void remove(Long tno);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    default TodoDTO entityToDTO(Todo todo) {
        return TodoDTO.builder().tno(todo.getTno()).title(todo.getTitle()).content(todo.getContent()).completed(todo.isCompleted()).dueDate(todo.getDueDate()).build();
    }

    default Todo dtoToEntity(TodoDTO todoDTO) {
        return Todo.builder().tno(todoDTO.getTno()).title(todoDTO.getTitle()).content(todoDTO.getContent()).completed(todoDTO.isCompleted()).dueDate(todoDTO.getDueDate()).build();
    }
}

package com.nisanth.todo.service;

import com.nisanth.todo.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodoById(Long id);

     List<TodoDto> getAllTodos();


     TodoDto updateTodos(TodoDto todoDto,Long id);

     void deleteTodo(Long id);

     TodoDto completeTodo(Long id);

     TodoDto inCompleteTodo(Long id);
}

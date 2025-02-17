package com.nisanth.todo.service.impl;

import com.nisanth.todo.dto.TodoDto;
import com.nisanth.todo.entity.Todo;
import com.nisanth.todo.exception.ResouceNotFoundException;
import com.nisanth.todo.repository.TodoRepository;
import com.nisanth.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert TodoDto into Todo Jpa Entity
        Todo todo=modelMapper.map(todoDto,Todo.class);

        // Todo jpa entity
        Todo savedTodo=todoRepository.save(todo);

        // convert saved Jpa entity object to TodoDto object
        TodoDto savedDto=modelMapper.map(savedTodo,TodoDto.class);
        return savedDto;
    }

    @Override
    public TodoDto getTodoById(Long id) {

       Todo todo= todoRepository.findById(id).orElseThrow(
               ()->new ResouceNotFoundException("Todo not found with this iD"));

        return modelMapper.map(todo,TodoDto.class);
    }


    public List<TodoDto> getAllTodos() {

       List<Todo> todoDtos= todoRepository.findAll();
        return todoDtos.stream().map((todo)->modelMapper.map(todo,TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodos(TodoDto todoDto, Long id) {

        Todo todo= todoRepository.findById(id).orElseThrow(
                ()->new ResouceNotFoundException("Todo not found with this iD"));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todo.isCompleted());

        Todo updatedTodo =todoRepository.save(todo);

        return modelMapper.map(updatedTodo,TodoDto.class);

    }

    @Override
    public void deleteTodo(Long id) {

        Todo todo= todoRepository.findById(id).orElseThrow(
                ()->new ResouceNotFoundException("Todo not found with this iD"));
        todoRepository.deleteById(id);

    }

    @Override
    public TodoDto completeTodo(Long id) {

        Todo todo= todoRepository.findById(id).orElseThrow(
                ()->new ResouceNotFoundException("Todo not found with this iD"));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo=todoRepository.save(todo);

        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo= todoRepository.findById(id).orElseThrow(
                ()->new ResouceNotFoundException("Todo not found with this iD"));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo=todoRepository.save(todo);

        return modelMapper.map(updatedTodo,TodoDto.class);

    }


}

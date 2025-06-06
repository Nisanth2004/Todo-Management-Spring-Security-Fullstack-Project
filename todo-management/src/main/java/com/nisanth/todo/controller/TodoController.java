package com.nisanth.todo.controller;

import com.nisanth.todo.dto.TodoDto;
import com.nisanth.todo.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todos")
@CrossOrigin("*")
public class TodoController {

    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto)
    {
       TodoDto todoDto1= todoService.addTodo(todoDto);
       return new ResponseEntity<>(todoDto1, HttpStatus.CREATED);
    }

    // build get todo rest api
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id)
    {
       TodoDto todoDto= todoService.getTodoById(id);
       return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }


    // Build get all todos
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos()
    {
       List<TodoDto> todos= todoService.getAllTodos();
       return new ResponseEntity<>(todos,HttpStatus.OK);
    }

    // build update employee REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateEmployee(@RequestBody TodoDto todoDto,@PathVariable Long id)
    {
       TodoDto updatedDto= todoService.updateTodos(todoDto,id);
       return ResponseEntity.ok(updatedDto);
    }


    // build delete REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodos(@PathVariable Long id)
    {
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo Deleted Successfully");
    }

    // Build Completed Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> CompletedTodo(@PathVariable Long id)
    {
        // PATCH- upadte the existing resource partially

       TodoDto todoDto= todoService.completeTodo(id);
       return ResponseEntity.ok(todoDto);
    }

    // Build Completed Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> InCompletedTodo(@PathVariable Long id)
    {
        // PATCH- upadte the existing resource partially

        TodoDto todoDto= todoService.inCompleteTodo(id);
        return ResponseEntity.ok(todoDto);
    }


}

package com.gl.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.todo.dto.TodoDTO;
import com.gl.todo.service.TodoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@PostMapping
	public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO){
		
		TodoDTO createTodoDTO = todoService.createTodo(todoDTO);
		
		return new ResponseEntity<TodoDTO>(createTodoDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TodoDTO> getTodoById(@PathVariable("id") long id){
		TodoDTO getTodoDTO = todoService.getTodoById(id);
		
		return ResponseEntity.ok(getTodoDTO);
	}

	
	@PutMapping("{id}")
	public ResponseEntity<TodoDTO> updateTodoById(@RequestBody TodoDTO todoDTO, @PathVariable("id") long id){
		
		TodoDTO updateTodoDTO = todoService.updateTodo(todoDTO, id);
		
		return ResponseEntity.ok(updateTodoDTO);
		
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodoById(@PathVariable("id") long id){
		
		todoService.deleteTodo(id);
		
		return ResponseEntity.ok("Todo is Deleted Successfully");
	}
	
	
	@GetMapping
	public List<TodoDTO> getAllTodos(){
		return todoService.getAllTodos();
	}
	
	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDTO> completeTodo(@PathVariable("id") long id){
		TodoDTO updateTodo = todoService.completeTodo(id);
		
		return ResponseEntity.ok(updateTodo);
	}
	
	@PatchMapping("{id}/in-complete")
	public ResponseEntity<TodoDTO> incompleteTodo(@PathVariable("id") long id){
		TodoDTO updateTodo = todoService.incompleteTodo(id);		
		return ResponseEntity.ok(updateTodo);
	}
	
	
}

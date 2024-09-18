package com.gl.todo.service;

import java.util.List;

import com.gl.todo.dto.TodoDTO;

public interface TodoService {

	public TodoDTO createTodo(TodoDTO todoDTO);
	public TodoDTO getTodoById(long id);
	public TodoDTO updateTodo(TodoDTO todoDTO, long id);
	public void deleteTodo(long id);
	public List<TodoDTO> getAllTodos();
	
	public TodoDTO completeTodo(long id);
	public TodoDTO incompleteTodo(long id);
}

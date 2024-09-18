package com.gl.todo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.todo.dto.TodoDTO;
import com.gl.todo.entity.Todo;
import com.gl.todo.exception.ResourceNotFoundException;
import com.gl.todo.mapper.TodoMapper;
import com.gl.todo.repository.TodoRepository;
import com.gl.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	TodoRepository todoRepository;

	@Override
	public TodoDTO createTodo(TodoDTO todoDTO) {
		
		Todo todo = TodoMapper.maptoTodo(todoDTO);
		Todo saveTodo = todoRepository.save(todo);
		
		return TodoMapper.mapToDTO(saveTodo);
	}

	@Override
	public TodoDTO getTodoById(long id) {
		
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo with id "+id+" Doesn't exists"));
		
		return TodoMapper.mapToDTO(todo);
	}

	@Override
	public TodoDTO updateTodo(TodoDTO todoDTO, long id) {
		
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo with id "+id+" Doesn't exists"));
		
		todo.setTitle(todoDTO.getTitle());
		todo.setDescription(todoDTO.getDescription());
		todo.setCompleted(todoDTO.getCompleted());
		
		Todo saveTodo = todoRepository.save(todo);
		
		return TodoMapper.mapToDTO(saveTodo);
	}

	@Override
	public void deleteTodo(long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo with id "+id+" Doesn't exists"));
		
		todoRepository.deleteById(id);

	}

	@Override
	public List<TodoDTO> getAllTodos() {
		
		List<Todo> todos = todoRepository.findAll();
		
		return todos.stream().map((todo) -> TodoMapper.mapToDTO(todo)).toList();
	}

	@Override
	public TodoDTO completeTodo(long id) {
		
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo with id "+id+" Doesn't exists"));
		
		todo.setCompleted("Yes");
		
		Todo updateTodo = todoRepository.save(todo);
		
		return TodoMapper.mapToDTO(updateTodo);
	}

	@Override
	public TodoDTO incompleteTodo(long id) {
		
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo with id "+id+" Doesn't exists"));
		
		todo.setCompleted("No");
		
		Todo updateTodo = todoRepository.save(todo);
		
		return TodoMapper.mapToDTO(updateTodo);
	}

}

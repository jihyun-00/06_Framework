package edu.kh.todo.model.service;

import java.util.List;

import edu.kh.todo.model.dto.Todo;

public interface RestService {

	List<Todo> selectTodoList();

	int changeYN(String complete, int todoNo);

	int deleteTodoList(int todoNo);

	List<Todo> addTodoList(String todoTitle, String todoContent);

	

}

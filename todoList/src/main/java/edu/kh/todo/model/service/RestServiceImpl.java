package edu.kh.todo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.todo.model.dao.TodoDAO;
import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.mapper.RestMapper;

@Transactional(rollbackFor = Exception.class)
@Service
public class RestServiceImpl implements RestService {
	
	@Autowired // TodoDAO와 같은 타입/상속관계 Bean 의존성 주입(DI)
	private TodoDAO dao;
	
	@Autowired
	private RestMapper mapper;

	@Override
	public List<Todo> selectTodoList() {
		return mapper.selectTodoList();
	}

	@Override
	public int changeYN(String complete, int todoNo) {
		return mapper.changeYN(complete, todoNo);
	}

	@Override
	public int deleteTodoList(int todoNo) {
		return mapper.deleteTodoList(todoNo);
	}

	@Override
	public List<Todo> addTodoList(String todoTitle, String todoContent) {
		return mapper.addTodoList(todoTitle, todoContent);
	}

	

}

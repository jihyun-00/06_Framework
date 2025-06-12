package edu.kh.todo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import edu.kh.todo.model.dto.Todo;

@Mapper
public interface RestMapper {

	List<Todo> selectTodoList();

	int changeYN(@Param("complete") String complete, @Param("todoNo") int todoNo);

	int deleteTodoList(int todoNo);

	List<Todo> addTodoList(String todoTitle, String todoContent);


}

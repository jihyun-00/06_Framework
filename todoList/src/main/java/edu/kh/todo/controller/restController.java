package edu.kh.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.RestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController // 비동기 컨트롤러
@CrossOrigin(origins="http://localhost:5173" /*, allowCredentials = "true"*/ )
// , allowCredentials = "true" 클라이언트로부터 들어오는 쿠키 허용
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class restController {
	
	private final RestService service;
	
		@GetMapping("select")
		public ResponseEntity<Object> selectTodoList() {
			try {
				List<Todo> todo = service.selectTodoList();
				return ResponseEntity.status(HttpStatus.OK).body(todo);
				
			} catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}
		
		@PutMapping("change")
		public ResponseEntity<Object> changeYN(@RequestBody Todo todo) {
			try {
				int todoList = service.changeYN(todo.getComplete(), todo.getTodoNo());
				return ResponseEntity.status(HttpStatus.OK).body(todoList);
				
			} catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}
		
		@PutMapping("delete")
		public ResponseEntity<Object> deleteTodoList(@RequestBody Todo todo) {
			try {
				int result = service.deleteTodoList(todo.getTodoNo());
				
				if(result>0) {
					return ResponseEntity.status(HttpStatus.OK).body(todo.getTodoNo() + "번 할 일 삭제 완료");
					
				} else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(todo.getTodoNo() + "번 할 일 삭제 실패");
					
				}
				
			} catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}
		
		@PostMapping("add")
		public ResponseEntity<Object> addTodoList(@RequestBody Todo todo) {
			try {
				List<Todo> todoList = service.addTodoList(todo.getTodoTitle(), todo.getTodoContent());
				return ResponseEntity.status(HttpStatus.OK).body(todoList);
				
			} catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		}

}

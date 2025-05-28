package edu.kh.project.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;
import edu.kh.project.board.model.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper mapper;

	// 게시판 종류 조회 서비스
	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		return mapper.selectBoardTypeList();
	}

	// 특정 게시판의 지정된 페이지 목록 조회 서비스
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {
		
		// 1. 지정된 게시판(boardCode)에서
		// 	  삭제되지 않은 게시글 수 조회
		int listCount = mapper.getListCount(boardCode);
		
		// 2. 1번의 결과 + cp를 이용해서
		// Pagination 객체 생성
		// * Pagination 객체 : 게시글 목록 구성에 필요한 값에 저장한 객체
		Pagination pagination = new Pagination(cp, listCount);
		
		// 3. 특정 게시판의 지정된 페이지 목록 조회
		/*
		 * ROWBOUNDS 객체 (Mybatis 제공 객체)
		 * : 지정된 크기만큼 건너 뛰고(offset)
		 * 제한된 크기만큼(limit)의 행 조회하는 객체
		 * 
		 * --> 페이징 처리가 굉장히 간단해짐
		 * 
		 */
		int limit = pagination.getLimit(); // 10개씩 조회
		int offset = (cp - 1) * limit; // cp : 현재 페이지
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		// Mapper 메서드 호출 시 원래 전달할 수 있는 매개변수 1개
		// -> 두개를 전달할 수 있는 경우가 있음
		// rowBounds를 이용할 때!
		// -> 첫번째 매개변수 -> SQL에 전달할 파라미터
		// -> 두번째 매개변수 -> RowBounds 객체 전달
		List<Board> boardList = mapper.selectBoardList(boardCode, rowBounds);
		
		log.debug("boardList 결과 : {}", boardList);
		
		// 4. 목록 조회 결과 + Pagination 객체를 Map으로 묶어서 반환
		Map<String, Object> map = new HashMap<>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;
	}

	// 게시글 상세 조회
	@Override
	public Board selectOne(Map<String, Integer> map) {
		
		// 여러 SQL을 실행하는 방법
		// 1. 하나의 Servic 메서드에서
		// 	  여러 mapper 메서드를 호출하는 방법
		
		// 2. 수행하려는 SQL이
		//  1) 모두 SELECT이면서
		//	2) 먼저 조회된 결과 중 일부를 이용해서
		//     나중에 수행되는 SQL의 조건으로 삼을 수 있는 경우
		// -> Mybatis의 <resultMap>, <collection> 태그를 이용해서
		// mapper 메서드 1회 호출로 여러 SELECT 한번에 수행 가능
		
		return mapper.selectOne(map);
	}

	// 게시글 좋아요 체크/해제
	@Override
	public int boardLike(Map<String, Integer> map) {
		
		int result = 0;
		
		
		
		if(map.get("likeCheck")==1) {
			// 1. 좋아요가 체크된 상태인 경우
			// -> BOARD_LIKE 테이블에 DELETE 수행
			
			result = mapper.deleteBoardLike(map);
			
		} else {
			// 2. 좋아요가 해제된 상태인 경우(likeCheck == 0)
			// -> BOARD_LIKE 테이블에 INSERT 수행
			result = mapper.insertBoardLike(map);
			
		}
		
		// 3. 다시 해당 게시글의 좋아요 개수를 조회해서 반환
		if(result >0) {
			return mapper.selectLikeCount(map.get("boardNo"));
		}
		
		
		return -1; // 좋아요 처리 실패(체크/해제 모두)
	}
	

}

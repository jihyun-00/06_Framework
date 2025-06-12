package edu.kh.project.admin.model.service;

import java.util.List;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;

public interface AdminService {

	// 관리자 로그인
	Member login(Member inputMember);

	// 최대 조회수 게시글 조회
	Board maxReadCount();

	// 최대 좋아요수 게시글 조회
	Board maxLikeCount();

	// 최대 댓글수 게시글 조회
	Board maxCommentCount();

	// 7일 이내 신규 가입 회원 정보 조회
	List<Member> newMemberCount();

	// 탈퇴한 회원 목록 조회
	List<Member> selectWithdrawnMemberList();

	// 삭제된 게시글 목록 조회
	List<Board> selectDeleteBoardList();

	// 탈퇴 회원 복구
	int restoreMember(int memberNo);

	// 삭제된 게시글 복구
	int restoreBoard(int boardNo);

	// 관리자 이메일 중복 여부 검사
	int checkEmail(String memberEmail);

	// 관리자 계정 발급
	String createAdminAccount(Member member);

	// 관리자 계정 목록 조회
	List<Member> getAdminList();

}

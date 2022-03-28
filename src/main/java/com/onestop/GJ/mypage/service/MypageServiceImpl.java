package com.onestop.GJ.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.board.QNA.dao.QnaDAO;
import com.onestop.GJ.board.QNA.vo.QnaVO;
import com.onestop.GJ.member.vo.MemberVO;
import com.onestop.GJ.mypage.dao.MypageDAO;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MypageServiceImpl implements MypageService {
	@Autowired
	private MypageDAO mypageDAO;

	// 비밀번호 재확인 창
	@Override
	public MemberVO pwdConfirm(MemberVO memberVO) throws Exception {
		return mypageDAO.pwdConfirm(memberVO);
	}

	// 회원 정보 수정
	@Override
	public MemberVO modifyMember(Map memberMap) throws Exception {
		String member_id = (String) memberMap.get("member_id");
		mypageDAO.updateMember(memberMap);
		return mypageDAO.selectMemberById(member_id);
	}

	// 회원 탈퇴
	@Override
	public int deleteMember(String member_id) throws Exception {
		return mypageDAO.deleteMember(member_id);
	}

	// 비밀번호 재확인 기능
	@Override
	public boolean checkPwd(String member_id, String member_pw) throws Exception {
		return mypageDAO.checkPwd(member_id, member_pw);
	}
	
	// 상담글-월세지원 카테고리만 조회
	@Override
	public List<QnaVO> selectMonthQnasList(String member_id) throws Exception {
		List<QnaVO> monthQnaList = mypageDAO.selectMonthQnasList(member_id);
		return monthQnaList;
	}

	// 상담글-전월세보증금 카테고리만 조회
	@Override
	public List<QnaVO> selectRentQnasList(String member_id) throws Exception {
		List<QnaVO> rentQnaList = mypageDAO.selectRentQnasList(member_id);
		return rentQnaList;
	}

	// 상담글-전세반환보증금 카테고리만 조회
	@Override
	public List<QnaVO> selectReturnQnasList(String member_id) throws Exception {
		List<QnaVO> returnQnaList = mypageDAO.selectReturnQnasList(member_id);
		return returnQnaList;
	}

	// 상담글-신혼부부전세자금 카테고리만 조회
	@Override
	public List<QnaVO> selectWeddingQnasList(String member_id) throws Exception {
		List<QnaVO> weddingQnaList = mypageDAO.selectWeddingQnasList(member_id);
		return weddingQnaList;
	}

	// 상담글-청년희망주택 카테고리만 조회
	@Override
	public List<QnaVO> selectShareQnasList(String member_id) throws Exception {
		List<QnaVO> shareQnaList = mypageDAO.selectShareQnasList(member_id);
		return shareQnaList;
	}

}

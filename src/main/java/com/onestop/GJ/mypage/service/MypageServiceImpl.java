package com.onestop.GJ.mypage.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

//	// 아이디로 조회하여 회원 선택
//	@Override
//	public MemberVO selectMemberById(String member_id) throws Exception {
//		return mypageDAO.selectMemberById(member_id);
//	}

//	// 회원 정보 수정 (int)
//	@Override
//	public int updateMember(MemberVO memberVO) throws Exception {
//		return mypageDAO.updateMember(memberVO);
//	}
//
	// 회원 정보 수정
	@Override
	public MemberVO modifyMember(Map memberMap) throws Exception {
		String member_id = (String) memberMap.get("member_id");
		mypageDAO.updateMember(memberMap);
		return mypageDAO.selectMemberById(member_id);
	}
	
//	// 회원 정보 수정 (void)
//	@Override
//	public void updateMember(MemberVO memberVO) throws Exception {
//		mypageDAO.updateMember(memberVO);
//	}
	
	

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

}

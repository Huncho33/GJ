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

	// ��й�ȣ ��Ȯ�� â
	@Override
	public MemberVO pwdConfirm(MemberVO memberVO) throws Exception {
		return mypageDAO.pwdConfirm(memberVO);
	}

	// ȸ�� ���� ����
	@Override
	public MemberVO modifyMember(Map memberMap) throws Exception {
		String member_id = (String) memberMap.get("member_id");
		mypageDAO.updateMember(memberMap);
		return mypageDAO.selectMemberById(member_id);
	}
	

	// ȸ�� Ż��
	@Override
	public int deleteMember(String member_id) throws Exception {
		return mypageDAO.deleteMember(member_id);
	}

	// ��й�ȣ ��Ȯ�� ���
	@Override
	public boolean checkPwd(String member_id, String member_pw) throws Exception {
		return mypageDAO.checkPwd(member_id, member_pw);
	}

}

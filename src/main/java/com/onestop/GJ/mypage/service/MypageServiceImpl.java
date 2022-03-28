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
	
	// ����-�������� ī�װ��� ��ȸ
	@Override
	public List<QnaVO> selectMonthQnasList(String member_id) throws Exception {
		List<QnaVO> monthQnaList = mypageDAO.selectMonthQnasList(member_id);
		return monthQnaList;
	}

	// ����-������������ ī�װ��� ��ȸ
	@Override
	public List<QnaVO> selectRentQnasList(String member_id) throws Exception {
		List<QnaVO> rentQnaList = mypageDAO.selectRentQnasList(member_id);
		return rentQnaList;
	}

	// ����-������ȯ������ ī�װ��� ��ȸ
	@Override
	public List<QnaVO> selectReturnQnasList(String member_id) throws Exception {
		List<QnaVO> returnQnaList = mypageDAO.selectReturnQnasList(member_id);
		return returnQnaList;
	}

	// ����-��ȥ�κ������ڱ� ī�װ��� ��ȸ
	@Override
	public List<QnaVO> selectWeddingQnasList(String member_id) throws Exception {
		List<QnaVO> weddingQnaList = mypageDAO.selectWeddingQnasList(member_id);
		return weddingQnaList;
	}

	// ����-û��������� ī�װ��� ��ȸ
	@Override
	public List<QnaVO> selectShareQnasList(String member_id) throws Exception {
		List<QnaVO> shareQnaList = mypageDAO.selectShareQnasList(member_id);
		return shareQnaList;
	}

}

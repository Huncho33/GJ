package com.onestop.GJ.admin.member.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.onestop.GJ.admin.member.dao.AdminMemberDAOImpl;
import com.onestop.GJ.member.vo.MemberVO;


@Service("adminMemberService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminMemberServiceImpl implements AdminMemberService {
	@Autowired
	private AdminMemberDAOImpl adminMemberDAO;
	
	
	@Override
	public Map listMembers(Map pagingMap) throws DataAccessException {
		
		Map membersMap = new HashMap();
		List<MemberVO> membersList = adminMemberDAO.selectAllMemberList(pagingMap);
		int totMembers = adminMemberDAO.selectTotMembers();
		membersMap.put("membersList", membersList);
		membersMap.put("totMembers", totMembers);
		return membersMap;
//		List membersList = null;
	}
	
	   //�˻�â
	   @Override
		public Map searchMemberList(Map pagingMap) throws Exception{
		   Map membersMap = new HashMap();
			List<MemberVO> membersList=adminMemberDAO.selectMemberListBySearchMember(pagingMap);
			int searchTotMembers = adminMemberDAO.selectSearchTotMembers(pagingMap);
			int totMembers = adminMemberDAO.selectTotMembers();
			membersMap.put("membersList", membersList);
			membersMap.put("searchTotMembers", searchTotMembers);
			membersMap.put("totMembers", totMembers);
//		      articlesMap.put("searchTotArticles", 170);
			return membersMap;
		}
	   
	   //ȸ�� ��
	   @Override
	   public Map viewMember(String member_id) throws Exception {
	      Map membersMap = new HashMap();
	      MemberVO memberVO = adminMemberDAO.selectMember(member_id);
	      membersMap.put("member", memberVO);
	      Collection<String> value = membersMap.values();      
	      return membersMap;
	      
	   }
	
	@Override
	public int addMember(MemberVO member) throws DataAccessException {
		return adminMemberDAO.insertMember(member);
	}
	
	
	//������ ���VO ��������
	@Override
	public MemberVO selectMemberId(String member_id) throws DataAccessException {
		return adminMemberDAO.selectMember(member_id);
	}
	
	
	// ȸ�� ���� ����
	@Override
	public MemberVO modifyMember_adm(Map membersMap) throws Exception {
		String member_id = (String) membersMap.get("member_id");
		adminMemberDAO.updateMember_adm(membersMap);
		return adminMemberDAO.selectMember(member_id);
	}
	
		
	//ȸ�� ���� ����
		@Override
	public void removeMember(String member_id) throws DataAccessException {
			System.out.println(member_id);
		adminMemberDAO.deleteMember(member_id);
	}
}
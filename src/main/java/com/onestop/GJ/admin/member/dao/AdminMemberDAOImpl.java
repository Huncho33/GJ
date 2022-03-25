package com.onestop.GJ.admin.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.admin.member.vo.AdminMemberVO;


@Repository("adminMemberDAO")
public class AdminMemberDAOImpl implements AdminMemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllMemberList(Map pagingMap) throws DataAccessException {
		List<AdminMemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.adminMember.selectAllMemberList_adm", pagingMap);
		return membersList;
	}
	
	 //�˻�â
 	@Override
 	public List selectMemberListBySearchMember(Map pagingMap) throws DataAccessException{
 		List<AdminMemberVO> membersList=sqlSession.selectList("mapper.adminMember.selectMemberListBySearchMember", pagingMap);
 	     System.out.println("�˻�"+ membersList.size());
 		 return membersList;
 	}
	
 	//�� ȸ����
	@Override
	public int selectTotMembers() throws DataAccessException {
		int totMembers = sqlSession.selectOne("mapper.adminMember.selectTotMembers");
		return totMembers;

	}
	
    //�˻�â �� �˻���
   	@Override
   	public int selectSearchTotMembers(Map pagingMap) throws DataAccessException{
   		int searchTotMembers = sqlSession.selectOne("mapper.adminMember.selectSearchTotMembers", pagingMap);
   		System.out.println("�˻�ȸ����"+ searchTotMembers);
   		return searchTotMembers;
   	}
   	
//  ȸ�� �󼼺���
  @Override
  public AdminMemberVO selectMember(String member_id) throws DataAccessException {
     return sqlSession.selectOne("mapper.adminMember.selectMember", member_id);
  }

	@Override
	public int insertMember(AdminMemberVO adminMemberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.adminMember.insertMember_adm", adminMemberVO);
		return result;
	}
	
	// ȸ�� ���� ����
		public void updateMember_adm(Map membersMap) throws DataAccessException {
			sqlSession.update("mapper.adminMember.updateMember_adm", membersMap);
			System.out.println("ȸ������" + membersMap);
		}
	
	

	@Override
	public int deleteMember(String member_id) throws DataAccessException {
		int result = sqlSession.delete("mapper.adminMember.deleteMember_adm", member_id);
		return result;
	}
}

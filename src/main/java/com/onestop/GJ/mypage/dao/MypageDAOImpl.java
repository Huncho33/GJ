package com.onestop.GJ.mypage.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.apply.back.vo.ApplyBackFileVO;
import com.onestop.GJ.apply.back.vo.ApplyBackVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentFileVO;
import com.onestop.GJ.apply.rent.vo.ApplyRentVO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnFileVO;
import com.onestop.GJ.apply.rent_return.vo.ApplyRentReturnVO;
import com.onestop.GJ.apply.share.vo.ApplyShareFileVO;
import com.onestop.GJ.apply.share.vo.ApplyShareVO;
import com.onestop.GJ.board.QNA.vo.QnaVO;
import com.onestop.GJ.member.vo.MemberVO;

@Repository
public class MypageDAOImpl implements MypageDAO {
	@Autowired
	private SqlSession sqlSession;

	// 비밀번호 재확인
	@Override
	public MemberVO pwdConfirm(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.mypage.pwdConfirm", memberVO);
		return vo;
	}

//	// 아이디로 조회하여 회원 선택
	@Override
	public MemberVO selectMemberById(String id) throws DataAccessException {
		MemberVO memberVO = (MemberVO) sqlSession.selectOne("mapper.mypage.selectMemberById", id);
		return memberVO;
	}

	// 회원 정보 수정
	public void updateMember(Map memberMap) throws DataAccessException {
		sqlSession.update("mapper.mypage.updateMember", memberMap);
	}

	// 회원 탈퇴
	@Override
	public int deleteMember(String member_id) throws DataAccessException {
		int result = sqlSession.delete("mapper.mypage.deleteMember", member_id);
		return result;
	}

	// 비밀번호 재확인을 위한 조회 기능
	@Override
	public boolean checkPwd(String member_id, String member_pw) throws DataAccessException {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_id", member_id);
		map.put("member_pw", member_pw);
		int count = sqlSession.selectOne("mapper.mypage.checkPwd", map);
		if (count == 1) {
			result = true;
		}
		return result;
	}

	// 마이페이지 내가 쓴 글 조회
	@Override
	public List selectMyBoardList(String member_id) throws Exception {
		List<QnaVO> monthQnaList = sqlSession.selectList("mapper.mypage.selectMyBoardList", member_id);
		return monthQnaList;
	}

	// ----------------------------신청 내역 삭제 기능---------------------------------
	@Override
	public void removeApply(int mo_no) throws DataAccessException {
		sqlSession.delete("mapper.mypage.removeApply", mo_no);
	}

	@Override
	public void removeRentApply(int rent_no) throws DataAccessException {
		sqlSession.delete("mapper.mypage.removeRentApply", rent_no);
	}

	@Override
	public void removeRetApply(int ret_no) throws DataAccessException {
		sqlSession.delete("mapper.mypage.removeRetApply", ret_no);
	}

	@Override
	public void removeBackApply(int ba_no) throws DataAccessException {
		sqlSession.delete("mapper.mypage.removeBackApply", ba_no);
	}

	@Override
	public void removeShareApply(int sh_no) throws DataAccessException {
		sqlSession.delete("mapper.mypage.removeShareApply", sh_no);
	}

	// --------------------------------신청현황 조회-----------------------------------
	@Override
	public List selectMonthApplyList(String member_id) throws Exception {
		List<ApplyMonVO> monthQnaList = sqlSession.selectList("mapper.mypage.selectMonthApplyList", member_id);
		return monthQnaList;
	}

	@Override
	public List selectRentApplyList(String member_id) throws Exception {
		List<ApplyRentVO> rentApplyList = sqlSession.selectList("mapper.mypage.selectRentApplyList", member_id);
		return rentApplyList;
	}

	@Override
	public List selectRentReturnApplyList(String member_id) throws Exception {
		List<ApplyRentReturnVO> rentReturnApplyList = sqlSession.selectList("mapper.mypage.selectRentReturnApplyList",
				member_id);
		return rentReturnApplyList;
	}

	@Override
	public List selectBackApplyList(String member_id) throws Exception {
		List<ApplyBackVO> backApplyList = sqlSession.selectList("mapper.mypage.selectBackApplyList", member_id);
		return backApplyList;
	}

	@Override
	public List selectShareApplyList(String member_id) throws Exception {
		List<ApplyShareVO> shareApplyList = sqlSession.selectList("mapper.mypage.selectShareApplyList", member_id);
		return shareApplyList;
	}

	// --------------------------------카테고리별 신청상세정보--------------------------------
	@Override
	public ApplyMonVO selectViewMonthApply(String member_id) throws Exception {
		return sqlSession.selectOne("mapper.mypage.selectMonthApplyList", member_id);
	}

	@Override
	public ApplyRentVO selectViewRentApply(String member_id) throws Exception {
		return sqlSession.selectOne("mapper.mypage.selectRentApplyList", member_id);
	}

	@Override
	public ApplyRentReturnVO selectViewRetApply(String member_id) throws Exception {
		return sqlSession.selectOne("mapper.mypage.selectRentReturnApplyList", member_id);
	}

	@Override
	public ApplyBackVO selectViewBackApply(String member_id) throws Exception {
		return sqlSession.selectOne("mapper.mypage.selectBackApplyList", member_id);
	}

	@Override
	public ApplyShareVO selectViewShareApply(String member_id) throws Exception {
		return sqlSession.selectOne("mapper.mypage.selectShareApplyList", member_id);
	}

	// --------------------------신청상세정보:첨부파일정보조회-----------------------------------
	@Override
	public List selectMonthFileList(int mo_no) throws Exception {
		List<ApplyMonFileVO> monthFileList = null;
		monthFileList = sqlSession.selectList("mapper.mypage.selectMonthFileList", mo_no);
		return monthFileList;
	}

	@Override
	public List selectRentFileList(int rent_no) throws Exception {
		List<ApplyRentFileVO> rentFileList = null;
		rentFileList = sqlSession.selectList("mapper.mypage.selectRentFileList", rent_no);
		return rentFileList;
	}

	@Override
	public List selectRetFileList(int ret_no) throws Exception {
		List<ApplyRentReturnFileVO> retFileList = null;
		retFileList = sqlSession.selectList("mapper.mypage.selectRetFileList", ret_no);
		return retFileList;
	}

	@Override
	public List selectBackFileList(int ba_no) throws Exception {
		List<ApplyBackFileVO> backFileList = null;
		backFileList = sqlSession.selectList("mapper.mypage.selectBackFileList", ba_no);
		return backFileList;
	}

	@Override
	public List selectShareFileList(int sh_no) throws Exception {
		List<ApplyShareFileVO> shareFileList = null;
		shareFileList = sqlSession.selectList("mapper.mypage.selectShareFileList", sh_no);
		return shareFileList;
	}

	// ------------------------------상담글:카테고리별조회-------------------------------------
	@Override
	public List selectMonthQnasList(String member_id) throws Exception {
		List<QnaVO> monthQnaList = sqlSession.selectList("mapper.mypage.selectMonthQnasList", member_id);
		return monthQnaList;
	}

	@Override
	public List selectRentQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectRentQnasList", member_id);
		return QnasList;
	}

	@Override
	public List selectReturnQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectReturnQnasList", member_id);
		return QnasList;
	}

	@Override
	public List selectWeddingQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectWeddingQnasList", member_id);
		return QnasList;
	}

	@Override
	public List selectShareQnasList(String member_id) throws Exception {
		List<QnaVO> QnasList = sqlSession.selectList("mapper.mypage.selectShareQnasList", member_id);
		return QnasList;
	}

	// -------------------------------신청서:수정기능-----------------------------------------

	// ------------------------------청년월세지원 수정
	@Override
	public void updateApply(Map ViewMonthMap) throws DataAccessException {
		Collection<String> value = ViewMonthMap.values();
		sqlSession.update("mapper.mypage.updateApply", ViewMonthMap);
	}

	@Override
	public void updateImageFile(Map ViewMonthMap) throws DataAccessException {

		List<ApplyMonFileVO> monthFileList = (ArrayList) ViewMonthMap.get("monthFileList");
		int mo_no = Integer.parseInt((String) ViewMonthMap.get("mo_no"));

		for (int i = monthFileList.size() - 1; i >= 0; i--) {
			ApplyMonFileVO applyMonFileVO = monthFileList.get(i);
			String up_fileName = applyMonFileVO.getUp_filename();
			if (up_fileName == null) { // 기존에 이미지를 수정하지 않는 경우 파일명이 null 이므로 수정할 필요가 없다.
				monthFileList.remove(i);
			} else {
				applyMonFileVO.setMo_no(mo_no);
			}
		}

		if (monthFileList != null && monthFileList.size() != 0) {
			sqlSession.update("mapper.mypage.updateImageFile", monthFileList);
		}
	}

	@Override
	public void insertModNewImage(Map articleMap) throws DataAccessException {
		List<ApplyMonFileVO> modAddimageFileList = (ArrayList<ApplyMonFileVO>) articleMap.get("modAddimageFileList");
		int mo_no = Integer.parseInt((String) articleMap.get("mo_no"));
		int up_fileno = selectNewImageFileNO();

		for (ApplyMonFileVO applyMonFileVO : modAddimageFileList) {
			applyMonFileVO.setMo_no(mo_no);
			applyMonFileVO.setUp_fileno(++up_fileno);
		}

		sqlSession.insert("mapper.mypage.insertModNewImage", modAddimageFileList);

	}

	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.selectNewImageFileNO");
	}

	// ----------------------------전월세이자지원 수정
	@Override
	public void updateRentApply(Map ViewRentMap) throws DataAccessException {
		Collection<String> value = ViewRentMap.values();
		sqlSession.update("mapper.mypage.updateApply", ViewRentMap);
	}

	@Override
	public void updateRentFile(Map ViewRentMap) throws DataAccessException {

		List<ApplyRentFileVO> rentFileList = (ArrayList) ViewRentMap.get("rentFileList");
		int rent_no = Integer.parseInt((String) ViewRentMap.get("rent_no"));

		for (int i = rentFileList.size() - 1; i >= 0; i--) {
			ApplyRentFileVO applyRentFileVO = rentFileList.get(i);
			String up_fileName = applyRentFileVO.getUp_filename();
			if (up_fileName == null) { // 기존에 이미지를 수정하지 않는 경우 파일명이 null 이므로 수정할 필요가 없다.
				rentFileList.remove(i);
			} else {
				applyRentFileVO.setRent_no(rent_no);
			}
		}
		if (rentFileList != null && rentFileList.size() != 0) {
			sqlSession.update("mapper.mypage.updateRentFile", rentFileList);
		}
	}

	@Override
	public void insertModNewRentFile(Map ViewRentMap) throws DataAccessException {
		List<ApplyRentFileVO> modAddrentFileList = (ArrayList<ApplyRentFileVO>) ViewRentMap.get("modAddrentFileList");
		int rent_no = Integer.parseInt((String) ViewRentMap.get("rent_no"));
		int up_fileno = selectNewRentFileNO();

		for (ApplyRentFileVO applyRentFileVO : modAddrentFileList) {
			applyRentFileVO.setRent_no(rent_no);
			applyRentFileVO.setUp_fileno(++up_fileno);
		}
		sqlSession.insert("mapper.mypage.insertModNewRentFile", modAddrentFileList);

	}

	private int selectNewRentFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.selectNewRentFileNO");
	}

	// --------------------------전세전환보증금지원 수정
	@Override
	public void updateRetApply(Map ViewRetMap) throws DataAccessException {
		Collection<String> value = ViewRetMap.values();
		sqlSession.update("mapper.mypage.updateApply", ViewRetMap);
	}

	@Override
	public void updateRetFile(Map ViewRetMap) throws DataAccessException {
		List<ApplyRentReturnFileVO> retFileList = (ArrayList) ViewRetMap.get("retFileList");
		int ret_no = Integer.parseInt((String) ViewRetMap.get("ret_no"));
		for (int i = retFileList.size() - 1; i >= 0; i--) {
			ApplyRentReturnFileVO applyRentReturnFileVO = retFileList.get(i);
			String up_fileName = applyRentReturnFileVO.getUp_filename();
			if (up_fileName == null) { // 기존에 이미지를 수정하지 않는 경우 파일명이 null 이므로 수정할 필요가 없다.
				retFileList.remove(i);
			} else {
				applyRentReturnFileVO.setRet_no(ret_no);
			}
		}
		if (retFileList != null && retFileList.size() != 0) {
			sqlSession.update("mapper.mypage.updateRetFile", retFileList);
		}
	}

	@Override
	public void insertModNewRetFile(Map ViewRetMap) throws DataAccessException {
		List<ApplyRentReturnFileVO> modAddretFileList = (ArrayList<ApplyRentReturnFileVO>) ViewRetMap
				.get("modAddretFileList");
		int ret_no = Integer.parseInt((String) ViewRetMap.get("ret_no"));
		int up_fileno = selectNewRetFileNO();

		for (ApplyRentReturnFileVO applyRentReturnFileVO : modAddretFileList) {
			applyRentReturnFileVO.setRet_no(ret_no);
			applyRentReturnFileVO.setUp_fileno(++up_fileno);
		}

		sqlSession.insert("mapper.mypage.insertModNewRetFile", modAddretFileList);

	}

	private int selectNewRetFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.selectNewRetFileNO");
	}

	// ----------------------------귀환신혼부부지원 수정
	@Override
	public void updateBackApply(Map ViewBackMap) throws DataAccessException {
		Collection<String> value = ViewBackMap.values();
		sqlSession.update("mapper.mypage.updateBackApply", ViewBackMap);
	}

	@Override
	public void updateBackFile(Map ViewBackMap) throws DataAccessException {
		List<ApplyBackFileVO> backFileList = (ArrayList) ViewBackMap.get("backFileList");
		int ba_no = Integer.parseInt((String) ViewBackMap.get("ba_no"));
		for (int i = backFileList.size() - 1; i >= 0; i--) {
			ApplyBackFileVO applyBackFileVO = backFileList.get(i);
			String up_fileName = applyBackFileVO.getUp_filename();
			if (up_fileName == null) { // 기존에 이미지를 수정하지 않는 경우 파일명이 null 이므로 수정할 필요가 없다.
				backFileList.remove(i);
			} else {
				applyBackFileVO.setBa_no(ba_no);
			}
		}
		if (backFileList != null && backFileList.size() != 0) {
			sqlSession.update("mapper.mypage.updateBackFile", backFileList);
		}
	}

	@Override
	public void insertModNewBackFile(Map articleMap) throws DataAccessException {
		List<ApplyBackFileVO> modAddbackFileList = (ArrayList<ApplyBackFileVO>) articleMap.get("modAddbackFileList");
		int ba_no = Integer.parseInt((String) articleMap.get("ba_no"));
		int up_fileno = selectNewBackFileNO();

		for (ApplyBackFileVO applyBackFileVO : modAddbackFileList) {
			applyBackFileVO.setBa_no(ba_no);
			applyBackFileVO.setUp_fileno(++up_fileno);
		}
		sqlSession.insert("mapper.mypage.insertModNewBackFile", modAddbackFileList);

	}

	private int selectNewBackFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.selectNewBackFileNO");
	}

	// ----------------------------청년희망주택지원 수정
	@Override
	public void updateShareApply(Map ViewShareMap) throws DataAccessException {
		Collection<String> value = ViewShareMap.values();
		sqlSession.update("mapper.mypage.updateApply", ViewShareMap);
	}

	@Override
	public void updateShareFile(Map ViewShareMap) throws DataAccessException {
		List<ApplyShareFileVO> shareFileList = (ArrayList) ViewShareMap.get("shareFileList");
		int sh_no = Integer.parseInt((String) ViewShareMap.get("sh_no"));
		for (int i = shareFileList.size() - 1; i >= 0; i--) {
			ApplyShareFileVO applyShareFileVO = shareFileList.get(i);
			String up_fileName = applyShareFileVO.getUp_filename();
			if (up_fileName == null) { // 기존에 이미지를 수정하지 않는 경우 파일명이 null 이므로 수정할 필요가 없다.
				shareFileList.remove(i);
			} else {
				applyShareFileVO.setSh_no(sh_no);
			}
		}
		if (shareFileList != null && shareFileList.size() != 0) {
			sqlSession.update("mapper.mypage.updateShareFile", shareFileList);
		}
	}

	@Override
	public void insertModNewShareFile(Map articleMap) throws DataAccessException {
		List<ApplyShareFileVO> modAddshareFileList = (ArrayList<ApplyShareFileVO>) articleMap
				.get("modAddshareFileList");
		int sh_no = Integer.parseInt((String) articleMap.get("sh_no"));
		int up_fileno = selectNewShareFileNO();
		for (ApplyShareFileVO applyShareFileVO : modAddshareFileList) {
			applyShareFileVO.setSh_no(sh_no);
			applyShareFileVO.setUp_fileno(++up_fileno);
		}
		sqlSession.insert("mapper.mypage.insertModNewShareFile", modAddshareFileList);
	}

	private int selectNewShareFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.selectNewShareFileNO");
	}

	// 알람게시판으로 이동
	@Override
	public void modifyAlarm(Map ViewMap) throws DataAccessException {
		int alarm_no = selectNewAlaramNO();
		ViewMap.put("alarm_no", alarm_no);
		sqlSession.insert("mapper.mypage.modifyAlarm", ViewMap);
	}
	
	
	
	private int selectNewAlaramNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.selectNewAlaramNO");
	}

}

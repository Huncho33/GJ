package com.onestop.GJ.mypage.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import com.onestop.GJ.board.notice.vo.BoardNoticeImageVO;
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

	// ��û ���� ���
	@Override
	public void removeApply(int mo_no) throws Exception {
		mypageDAO.removeApply(mo_no);
	}
	
	@Override
	public void removeRentApply(int rent_no) throws Exception {
		mypageDAO.removeRentApply(rent_no);
	}
	
	@Override
	public void removeRetApply(int ret_no) throws Exception {
		mypageDAO.removeRetApply(ret_no);
	}
	
	@Override
	public void removeBackApply(int ba_no) throws Exception {
		mypageDAO.removeBackApply(ba_no);
	}
	
	@Override
	public void removeShareApply(int sh_no) throws Exception {
		mypageDAO.removeShareApply(sh_no);
	}

	// ���������� ���� �� �� ��ȸ
	@Override
	public List<QnaVO> selectMyBoardList(String member_id) throws Exception {
		List<QnaVO> monthQnaList = mypageDAO.selectMyBoardList(member_id);
		return monthQnaList;
	}

	// -------------------------------��û��Ȳ ��ȸ-------------------------------------
	@Override
	public List<ApplyMonVO> selectMonthApplyList(String member_id) throws Exception {
		List<ApplyMonVO> monthApplyList = mypageDAO.selectMonthApplyList(member_id);
		return monthApplyList;
	}

	@Override
	public List<ApplyRentVO> selectRentApplyList(String member_id) throws Exception {
		List<ApplyRentVO> rentApplyList = mypageDAO.selectRentApplyList(member_id);
		return rentApplyList;
	}

	@Override
	public List<ApplyRentReturnVO> selectRentReturnApplyList(String member_id) throws Exception {
		List<ApplyRentReturnVO> rentReturnApplyList = mypageDAO.selectRentReturnApplyList(member_id);
		return rentReturnApplyList;
	}

	@Override
	public List<ApplyBackVO> selectBackApplyList(String member_id) throws Exception {
		List<ApplyBackVO> backApplyList = mypageDAO.selectBackApplyList(member_id);
		return backApplyList;
	}

	@Override
	public List<ApplyBackVO> selectShareApplyList(String member_id) throws Exception {
		List<ApplyBackVO> shareApplyList = mypageDAO.selectShareApplyList(member_id);
		return shareApplyList;
	}

	// ------------------------------ī�װ��� ��û������-------------------------------
	@Override
	public Map selectViewMonthApply(String member_id) throws Exception {
		Map ViewMonthMap = new HashMap();
		ApplyMonVO applyMonVO = mypageDAO.selectViewMonthApply(member_id);
		int mo_no = applyMonVO.getMo_no();
		List<ApplyMonFileVO> monthFileList = mypageDAO.selectMonthFileList(mo_no);
		ViewMonthMap.put("applyMon", applyMonVO);
		ViewMonthMap.put("monthFileList", monthFileList);
		Collection<String> value = ViewMonthMap.values();

		return ViewMonthMap;
	}

	@Override
	public Map selectViewRentApply(String member_id) throws Exception {
		Map ViewRentMap = new HashMap();
		ApplyRentVO applyRentVO = mypageDAO.selectViewRentApply(member_id);
		int rent_no = applyRentVO.getRent_no();
		List<ApplyRentFileVO> rentFileList = mypageDAO.selectRentFileList(rent_no);
		ViewRentMap.put("applyRent", applyRentVO);
		ViewRentMap.put("rentFileList", rentFileList);
		Collection<String> value = ViewRentMap.values();
		System.out.println("mypageSERVICE rentFileList VALUE:" + value);

		return ViewRentMap;
	}

	@Override
	public Map selectViewRetApply(String member_id) throws Exception {
		Map ViewRetMap = new HashMap();
		ApplyRentReturnVO applyRentReturnVO = mypageDAO.selectViewRetApply(member_id);
		int ret_no = applyRentReturnVO.getRet_no();
		List<ApplyRentReturnFileVO> retFileList = mypageDAO.selectRetFileList(ret_no);
		ViewRetMap.put("applyRentReturn", applyRentReturnVO);
		ViewRetMap.put("retFileList", retFileList);
		Collection<String> value = ViewRetMap.values();
		System.out.println("mypageSERVICE retFileList VALUE:" + value);

		return ViewRetMap;
	}

	@Override
	public Map selectViewBackApply(String member_id) throws Exception {
		Map ViewBackMap = new HashMap();
		ApplyBackVO applyBackVO = mypageDAO.selectViewBackApply(member_id);
		int ba_no = applyBackVO.getBa_no();
		List<ApplyBackFileVO> backFileList = mypageDAO.selectBackFileList(ba_no);
		ViewBackMap.put("applyBack", applyBackVO);
		ViewBackMap.put("backFileList", backFileList);
		Collection<String> value = ViewBackMap.values();
		System.out.println("mypageSERVICE backFileList VALUE:" + value);

		return ViewBackMap;
	}

	@Override
	public Map selectViewShareApply(String member_id) throws Exception {
		Map ViewShareMap = new HashMap();
		ApplyShareVO applyShareVO = mypageDAO.selectViewShareApply(member_id);
		int sh_no = applyShareVO.getSh_no();
		List<ApplyShareFileVO> shareFileList = mypageDAO.selectShareFileList(sh_no);
		ViewShareMap.put("applyShare", applyShareVO);
		ViewShareMap.put("shareFileList", shareFileList);
		Collection<String> value = ViewShareMap.values();
		System.out.println("mypageSERVICE shareFileList VALUE:" + value);

		return ViewShareMap;
	}

	// ----------------------------��û����������------------------------------
	@Override
	public void modApply(Map ViewMonthMap) throws Exception {
		List<ApplyMonFileVO> monthFileList = (List<ApplyMonFileVO>) ViewMonthMap.get("monthFileList");
		List<ApplyMonFileVO> modAddimageFileList = (List<ApplyMonFileVO>) ViewMonthMap
				.get("modAddimageFileList");

		if (monthFileList != null && monthFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String) ViewMonthMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String) ViewMonthMap.get("pre_img_num"));

			if (pre_img_num < added_img_num) {
				mypageDAO.updateImageFile(ViewMonthMap); // ���� �̹����� �����ϰ� �� �̹����� �߰��� ���
				mypageDAO.insertModNewImage(ViewMonthMap);
			} else {
				mypageDAO.updateImageFile(ViewMonthMap); // ������ �̹����� ������ �� ���
			}
		} else if (modAddimageFileList != null && modAddimageFileList.size() != 0) { // �� �̹����� �߰��� ���
			mypageDAO.insertModNewImage(ViewMonthMap);
		}

		if (monthFileList != null && monthFileList.size() != 0) {
			mypageDAO.updateImageFile(ViewMonthMap); // ���� ������ ������ ���
		}
	}
	
	@Override
	public void modRentApply(Map ViewRentMap) throws Exception {
		List<ApplyRentFileVO> rentFileList = (List<ApplyRentFileVO>) ViewRentMap.get("rentFileList");
		List<ApplyRentFileVO> modAddrentFileList = (List<ApplyRentFileVO>) ViewRentMap
				.get("modAddrentFileList");

		if (rentFileList != null && rentFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String) ViewRentMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String) ViewRentMap.get("pre_img_num"));

			if (pre_img_num < added_img_num) {
				mypageDAO.updateRentFile(ViewRentMap); // ���� �̹����� �����ϰ� �� �̹����� �߰��� ���
				mypageDAO.insertModNewRentFile(ViewRentMap);
			} else {
				mypageDAO.updateRentFile(ViewRentMap); // ������ �̹����� ������ �� ���
			}
		} else if (modAddrentFileList != null && modAddrentFileList.size() != 0) { // �� �̹����� �߰��� ���
			mypageDAO.insertModNewRentFile(ViewRentMap);
		}

		if (rentFileList != null && rentFileList.size() != 0) {
			mypageDAO.updateRentFile(ViewRentMap); // ���� ������ ������ ���
		}
	}
	
	@Override
	public void modRetApply(Map ViewRetMap) throws Exception {
		List<ApplyRentReturnFileVO> retFileList = (List<ApplyRentReturnFileVO>) ViewRetMap.get("retFileList");
		List<ApplyRentReturnFileVO> modAddretFileList = (List<ApplyRentReturnFileVO>) ViewRetMap
				.get("modAddretFileList");

		if (retFileList != null && retFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String) ViewRetMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String) ViewRetMap.get("pre_img_num"));

			if (pre_img_num < added_img_num) {
				mypageDAO.updateRetFile(ViewRetMap); // ���� �̹����� �����ϰ� �� �̹����� �߰��� ���
				mypageDAO.insertModNewImage(ViewRetMap);
			} else {
				mypageDAO.updateRetFile(ViewRetMap); // ������ �̹����� ������ �� ���
			}
		} else if (modAddretFileList != null && modAddretFileList.size() != 0) { // �� �̹����� �߰��� ���
			mypageDAO.insertModNewRetFile(ViewRetMap);
		}

		if (retFileList != null && retFileList.size() != 0) {
			mypageDAO.updateRetFile(ViewRetMap); // ���� ������ ������ ���
		}
	}
	
	@Override
	public void modBackApply(Map ViewBackMap) throws Exception {
		List<ApplyBackFileVO> backFileList = (List<ApplyBackFileVO>) ViewBackMap.get("backFileList");
		List<ApplyBackFileVO> modAddbackFileList = (List<ApplyBackFileVO>) ViewBackMap
				.get("modAddbackFileList");

		if (backFileList != null && backFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String) ViewBackMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String) ViewBackMap.get("pre_img_num"));

			if (pre_img_num < added_img_num) {
				mypageDAO.updateBackFile(ViewBackMap); // ���� �̹����� �����ϰ� �� �̹����� �߰��� ���
				mypageDAO.insertModNewBackFile(ViewBackMap);
			} else {
				mypageDAO.updateBackFile(ViewBackMap); // ������ �̹����� ������ �� ���
			}
		} else if (modAddbackFileList != null && modAddbackFileList.size() != 0) { // �� �̹����� �߰��� ���
			mypageDAO.insertModNewBackFile(ViewBackMap);
		}

		if (backFileList != null && backFileList.size() != 0) {
			mypageDAO.updateBackFile(ViewBackMap); // ���� ������ ������ ���
		}
	}
	
	@Override
	public void modShareApply(Map ViewShareMap) throws Exception {
		List<ApplyShareFileVO> shareFileList = (List<ApplyShareFileVO>) ViewShareMap.get("shareFileList");
		List<ApplyShareFileVO> modAddshareFileList = (List<ApplyShareFileVO>) ViewShareMap
				.get("modAddshareFileList");

		if (shareFileList != null && shareFileList.size() != 0) {
			int added_img_num = Integer.parseInt((String) ViewShareMap.get("added_img_num"));
			int pre_img_num = Integer.parseInt((String) ViewShareMap.get("pre_img_num"));

			if (pre_img_num < added_img_num) {
				mypageDAO.updateShareFile(ViewShareMap); // ���� �̹����� �����ϰ� �� �̹����� �߰��� ���
				mypageDAO.insertModNewShareFile(ViewShareMap);
			} else {
				mypageDAO.updateShareFile(ViewShareMap); // ������ �̹����� ������ �� ���
			}
		} else if (modAddshareFileList != null && modAddshareFileList.size() != 0) { // �� �̹����� �߰��� ���
			mypageDAO.insertModNewShareFile(ViewShareMap);
		}

		if (shareFileList != null && shareFileList.size() != 0) {
			mypageDAO.updateShareFile(ViewShareMap); // ���� ������ ������ ���
		}
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

	
	// �˶��Խ������� �̵�
	@Override
	public void modifyAlarm(Map ViewMap) throws Exception {
		mypageDAO.modifyAlarm(ViewMap);
	}
	

}

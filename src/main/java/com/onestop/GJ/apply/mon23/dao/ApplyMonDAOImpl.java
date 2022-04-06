package com.onestop.GJ.apply.mon23.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.onestop.GJ.apply.mon23.vo.ApplyMonFileVO;
import com.onestop.GJ.apply.mon23.vo.ApplyMonVO;

@Repository("ApplyMonDAOImpl")
public class ApplyMonDAOImpl implements ApplyMonDAO {
   @Autowired
   private SqlSession sqlSession;

   @Override
   public ApplyMonVO selectResult(Map resultMap) throws DataAccessException {
      ApplyMonVO applymonVO = (ApplyMonVO) sqlSession.selectOne("mapper.apply.selectResult", resultMap);
      return applymonVO;
   }

   // ��û��� �� �ֱ�.
   @Override
   public int insertResult(Map articleMap) throws DataAccessException {
      int mo_no = insertResultNO();
      articleMap.put("mo_no", mo_no);
      System.out.println("�׽�Ʈ11 : " + articleMap);

      sqlSession.insert("mapper.apply.insertResult", articleMap);
      System.out.println("�׽�Ʈ22 : " + articleMap);
      return mo_no;

   }

   // ��û��ȣ
   public int insertResultNO() throws DataAccessException {
      return sqlSession.selectOne("mapper.apply.insertResultNO");
   }

   // ���� ���� ���ε�
   @Override
   public void insertNewFile(Map articleMap) throws DataAccessException {
      List<ApplyMonFileVO> monApplyFileList = (ArrayList) articleMap.get("imageFileList");
      System.out.println("why? : " + monApplyFileList);
      int mo_no = (Integer) articleMap.get("mo_no");

      int MO_FILENO = selectNewImageFileNO();

      if (monApplyFileList != null && monApplyFileList.size() != 0) {

         for (ApplyMonFileVO applyMonFileVO : monApplyFileList) {
            applyMonFileVO.setUp_fileno(++MO_FILENO);
            applyMonFileVO.setMo_no(mo_no);
         }

      }
      System.out.println("imageFileList" + monApplyFileList);
      sqlSession.insert("mapper.apply.insertNewImage", monApplyFileList);
   }

   // ���� ��ȣ����
   private int selectNewImageFileNO() {
      return sqlSession.selectOne("mapper.apply.selectMoFileNO");
   }

   // ���������(memberVO id���� ���� member���̺� ��ȸ)
   @Override
   public ApplyMonVO findAll(String member_id) {
      ApplyMonVO list = null;
      list = sqlSession.selectOne("mapper.apply.all", member_id);
      return list;
   }

   // ���������(mo_no���� ���� applyMon���̺� ��ȸ)
   @Override
   public ApplyMonVO findNo(int mo_no) {
      ApplyMonVO month = sqlSession.selectOne("mapper.apply.findNo", mo_no);
      return month;
   }

}
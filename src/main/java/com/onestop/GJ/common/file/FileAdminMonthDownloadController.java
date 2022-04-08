
package com.onestop.GJ.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileAdminMonthDownloadController {
	private static String admin_monthApply_REPO = "C:\\GJ\\file_repo\\admin\\apply\\month";
	@RequestMapping("/adminMonthDownload.do")
	protected void download(@RequestParam("up_filename") String up_filename, @RequestParam("mo_no") String mo_no,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			
			System.out.println("�Դٰ�");
			String originFileName = null;
			originFileName = URLDecoder.decode(up_filename, "UTF-8");
            String onlyFileName = originFileName.substring(originFileName.lastIndexOf("_") + 1);
            
         //   headers.add("Content-Dispostion", "attachment; filename=" + new String(resourceName.getBytes("UTF-8"),"���v-8859-1"));
            String filePath = admin_monthApply_REPO + "\\" + mo_no + "\\" + up_filename;

            File file = new File(filePath);
            System.out.println("����: "+file);
            if(file.exists()) {
                String agent = request.getHeader("User-Agent");

                //�������� �ѱ����� �� ó��
                if(agent.contains("Trident"))//Internet Explore
                    onlyFileName = URLEncoder.encode(onlyFileName, "UTF-8").replaceAll("\\+", " ");
                    
                else if(agent.contains("Edge")) //Micro Edge
                    onlyFileName = URLEncoder.encode(onlyFileName, "UTF-8");
                    
                else //Chrome
                    onlyFileName = new String(onlyFileName.getBytes("UTF-8"), "ISO-8859-1");
                //�������� �ѱ����� �� ó��

                response.setHeader("Content-Type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" + onlyFileName);

                InputStream is = new FileInputStream(file);
                OutputStream os = response.getOutputStream();

                int length;
                byte[] buffer = new byte[1024];

                while( (length = is.read(buffer)) != -1){
                    os.write(buffer, 0, length);
                }

                os.flush();
                os.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

package com.onestop.GJ.common.file.apply;

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
public class FileRentApplyDownloadController {
	private static String rentApply_REPO = "C:\\GJ\\file_repo\\apply\\rent";
	@RequestMapping("/rentApplyDownload.do")
	protected void download(@RequestParam("up_filename") String up_filename, @RequestParam("rent_no") String rent_no,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			
			System.out.println("왔다감");
			String originFileName = null;
			originFileName = URLDecoder.decode(up_filename, "UTF-8");
            String onlyFileName = originFileName.substring(originFileName.lastIndexOf("_") + 1);
            
            String filePath = rentApply_REPO + "\\" + rent_no + "\\" + up_filename;

            File file = new File(filePath);
            System.out.println("파일: "+file);
            if(file.exists()) {
                String agent = request.getHeader("User-Agent");

                //브라우저별 한글파일 명 처리
                if(agent.contains("Trident"))//Internet Explore
                    onlyFileName = URLEncoder.encode(onlyFileName, "UTF-8").replaceAll("\\+", " ");
                    
                else if(agent.contains("Edge")) //Micro Edge
                    onlyFileName = URLEncoder.encode(onlyFileName, "UTF-8");
                    
                else //Chrome
                    onlyFileName = new String(onlyFileName.getBytes("UTF-8"), "ISO-8859-1");
                //브라우저별 한글파일 명 처리

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

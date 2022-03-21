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
public class FileDownloadController {
	private static String ARTICLE_IMAGE_REPO = "C:\\GJ\\file_repo\\board\\notice";
	@RequestMapping("/download.do")
	protected void download(@RequestParam("up_fileName") String up_fileName, @RequestParam("noti_NO") String noti_NO,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
			
			System.out.println("왔다감");
			String originFileName = null;
			originFileName = URLDecoder.decode(up_fileName, "UTF-8");
            String onlyFileName = originFileName.substring(originFileName.lastIndexOf("_") + 1);
            
         //   headers.add("Content-Dispostion", "attachment; filename=" + new String(resourceName.getBytes("UTF-8"),"ㅑ냬-8859-1"));
            String filePath = ARTICLE_IMAGE_REPO + "\\" + noti_NO + "\\" + up_fileName;

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
		
//		OutputStream out = response.getOutputStream();
//		String filePath = ARTICLE_IMAGE_REPO + "\\" + noti_NO + "\\" + up_fileName;
//		File image = new File(filePath);
//
	//	response.setContentType("application/download; UTF-8");
	//	response.setHeader("Cache-Control", "no-cache");
	//	response.addHeader("Content-disposition", "attachment; fileName=" + up_fileName);
	//	FileInputStream in = new FileInputStream(image);
	//	byte[] buffer = new byte[1024 * 8];
	//	while (true) {
	//		int count = in.read(buffer); // 버퍼에 읽어들인 문자개수
	//		if (count == -1) // 버퍼의 마지막에 도달했는지 체크
	//			break;
	//		out.write(buffer, 0, count);
	//	}
	//	in.close();
	//	out.close();
//
//	}

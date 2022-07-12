package com.lec.pet.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.pet.dao.PDogDao;
import com.lec.pet.dto.PDogDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class DogInsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("dogImg");
		int maxSize = 1024*1024;
		String dPhoto = "";
		try {
				MultipartRequest dRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
				Enumeration<String> params = dRequest.getFileNames();
					String param = params.nextElement();
					dPhoto = dRequest.getFilesystemName(param);
				String dName = dRequest.getParameter("dName");
				String dSize = dRequest.getParameter("dSize");
				String dCategory = dRequest.getParameter("dCategory");
				String dContent = dRequest.getParameter("dContent");
				dPhoto = dPhoto==null ? "noImg.png" : dPhoto;
				PDogDao pDDao = PDogDao.getInstance();
				
				PDogDto dog = new PDogDto(0, dName, dSize, dCategory, dContent, dPhoto);
				System.out.println(dName);
				System.out.println(dSize);
				System.out.println(dCategory);
				System.out.println(dContent);
				System.out.println(dPhoto);
				int result = pDDao.dogInsert(dog);
				
				System.out.println(result);
				if(result == PDogDao.SUCCESS) {
					HttpSession session = request.getSession();
					session.setAttribute("DogInsert", result);
					request.setAttribute("DogInsertResult", "강아지 추가 성공");
				}else {
					request.setAttribute("insertResult", result);
					
				}
				
				
				
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
		
		File serverFile = new File(path + "/" + dPhoto);
		if(serverFile.exists() && !dPhoto.equals("noImg.png")) {
				InputStream is = null;
				OutputStream os = null;
				try {
						is = new FileInputStream(serverFile);
						os = new FileOutputStream("C:\\webpro\\source\\06_JSP\\project_pet_0624\\WebContent\\dogImg\\"+dPhoto);
						byte[] bs = new byte[(int) serverFile.length()];
						while(true) {
								int readByteCnt = is.read(bs);
								if(readByteCnt==-1) break;
								os.write(bs, 0, readByteCnt);
						}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}finally {
						try {
								if(os!=null) os.close();
								if(is!=null) is.close();
						} catch (Exception e) {
							
						
				}
		}
	}

}
}























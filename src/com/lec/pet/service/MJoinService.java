package com.lec.pet.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.pet.dao.PMemberDao;
import com.lec.pet.dto.PMemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
public class MJoinService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("memberPhotoUp");
		int maxSize = 1024*1024; // 사진 업로드 제한 용량 : 1M
		String mPhoto = ""; // 첨부된 파일이 저장된 파일 이름
		try {
			// mRequest 객체 생성 후 mPhoto 파일이름 얻어옴
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize,
											"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			//while(params.hasMoreElements()) {
				String param = params.nextElement();
				mPhoto = mRequest.getFilesystemName(param);
			//}
			// mRequest을 이용하여 파라미터 받아와서 DB 저장
			String mId = mRequest.getParameter("mId");
			String mPw = mRequest.getParameter("mPw");
			String mName = mRequest.getParameter("mName");
			String mEmail = mRequest.getParameter("mEmail");
			mPhoto = mPhoto==null ? "NOIMG.JPG" : mPhoto;
			String mBirthStr = mRequest.getParameter("mBirth");
			Date mBirth = null;
			if(!mBirthStr.equals("")) {
				mBirth = Date.valueOf(mBirthStr);
			}
			String mAddress = mRequest.getParameter("mAddress");
			PMemberDao mDao = PMemberDao.getInstance();
			PMemberDto member = new PMemberDto(mId, mPw, mName, mEmail, 
					mPhoto, mBirth, mAddress, null);
			// 회원가입
			int result = mDao.joinMember(member);
			if(result == PMemberDao.SUCCESS) {
				HttpSession session = request.getSession(); // 세션은 request로 부터
				session.setAttribute("mId", mId);
				request.setAttribute("joinResult", "회원가입이 완료되었습니다");
			}else {
				request.setAttribute("joinErrorMsg", "정보가 너무 길어서 회원가입 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
		// 서버에 업로드된 파일을 소스 폴더로 복사
		File serverFile = new File(path + "/" + mPhoto);
		if(serverFile.exists() && !mPhoto.equals("NOIMG.JPG")) {
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:\\webpro\\source\\06_JSP\\project_pet_0624\\WebContent\\memberPhotoUp\\"+mPhoto);
				byte[] bs = new byte[(int) serverFile.length()];
				while(true) {
					int readByteCnt = is.read(bs);
					if(readByteCnt==-1) break;
					os.write(bs, 0, readByteCnt);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		}
	}

}

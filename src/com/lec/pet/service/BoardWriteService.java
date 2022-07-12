package com.lec.pet.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.pet.dao.PFileboardDao;
import com.lec.pet.dto.PMemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("freeBoardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String fFileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			fFileName = mRequest.getFilesystemName(param);
			// mId, fTitle, fContent,  fileName, fIp
			HttpSession httpSession = request.getSession();
			PMemberDto member = (PMemberDto)httpSession.getAttribute("member");
			if(member!=null) {
				String mId = member.getmId(); // 로그인 한 사람의 mId
				String fTitle = mRequest.getParameter("fTitle");
				String fContent = mRequest.getParameter("fContent");
				fFileName = fFileName==null ? "noImg.png" : fFileName;
				String fIp = request.getRemoteAddr();
				PFileboardDao boardDao = PFileboardDao.getInstance();
				int result = boardDao.writeBoard(mId, fTitle, fContent, fFileName, fIp);
				// joinMember결과에 따라 적절히 request.setAttribute
				if(result == PFileboardDao.SUCCESS) { // 회원가입 진행
					request.setAttribute("boaredResult", "글쓰기 성공");
				}else {
					request.setAttribute("boaredResult", "글쓰기 실패");
				}
			}else {
				request.setAttribute("boaredResult", "로그인 한 사람만 글쓸 수 있어요");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("boaredResult", "글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		if(fFileName!=null) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+fFileName);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("C:\\webpro\\source\\06_JSP\\project_pet_0624\\WebContent\\freeBoardUp\\"+fFileName);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int nByteCnt = is.read(bs);
					if(nByteCnt==-1) break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} // try
		}// 파일 복사 if
	}
}
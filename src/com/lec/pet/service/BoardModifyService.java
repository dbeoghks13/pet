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

import com.lec.pet.dao.PFileboardDao;
import com.lec.pet.service.Service;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("freeBoardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		String fFileName = "", dbFileName = null;
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			fFileName = mRequest.getFilesystemName(param);
			dbFileName = mRequest.getParameter("dbFileName");
			if(fFileName==null) {
				fFileName = dbFileName;
			}
			// mId, fTitle, fContent,  fileName, fIp
			int fId = Integer.parseInt(mRequest.getParameter("fId"));
			String fTitle = mRequest.getParameter("fTitle");
			String fContent = mRequest.getParameter("fContent");
			String fIp = request.getRemoteAddr();
			PFileboardDao boardDao = PFileboardDao.getInstance();
			int result = boardDao.modifyBoard(fId, fTitle, fContent, fFileName, fIp);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == PFileboardDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("boaredResult", "글수정 성공");
			}else {
				request.setAttribute("boaredResult", "글수정 실패");
			}
			// mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("boaredResult", "글수정 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy (파일 수정을 안 했거나, 예외가 떨어질 경우 복사 안 함)
		if(dbFileName!=null && !fFileName.equals(dbFileName)) { 
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
package com.biz.bbs.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

/*
 * summernote에서 dragAnddrop으로 이미지 파일을 업로드 하면
 * 일단 서버에 파일을 업로드 하고
 * 팡리 이름을 다시 내려보내서 base64로 encoding된 파일 정보를
 * img src="저장된경로/파일이름" 형식으로 변경한다.
 */
@RequiredArgsConstructor
@Service
public class FileService {

	// servlet-context.xml에 설정된 path파일 저장 경로 정보를 가져와서 사용하기
	private final String filePath;
	
	/*
	 * 브라우저에서 파일이 전송되어 오면
	 * 원래 파일 이름을 UUID 부착된 파일 이름을 변경하고
	 * 변경된 이름으로 서버의 filePath에 저장하고 변경된 파일 이름을 return
	 */
	public String fileUp(MultipartFile upFile) {
		String upLoadFile = "";
		
		// 파일 이름을 추출
		String originalFileName = upFile.getOriginalFilename();
		
		// UUID(Universal Unified IDenfitication 범우주적으로 유일한 값)가 부착된 새로운 이름을 생성
		String strUUID = UUID.randomUUID().toString();
		strUUID += originalFileName; // (파일명 ==> UUID + 파일이름.jpg)
		
		// filePath와 변경된 파일이름을 결합하여
		// File 객체를 생성
		File serverFile = new File(filePath,strUUID);
		
		// upload할 filePath가 있는지 확인을 하고
		// 없으면 폴더를 생성.
		File dir = new File(filePath);
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		// 업로드된 파일을 serverFile 이름으로 복사 수행
		try {
			upFile.transferTo(serverFile);
			return strUUID;
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
}

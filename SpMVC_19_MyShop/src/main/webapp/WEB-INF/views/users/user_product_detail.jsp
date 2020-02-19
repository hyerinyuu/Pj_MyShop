<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section>
	<div class="table-view">
		<table class="table table-striped">
		 	
		 	<thead class="thead-dark" >
				<tr data-seq="${emailVO.emsSeq}">
					<th width="20%">NO</th><td colspan="4">${emailVO.emsSeq}</td>
				</tr>
				
				<tr>
					<th width="20%">보낸 날짜</th><td colspan="4">${emailVO.sendDate}</td>
				</tr>
				
				<tr>
					<th width="20%">보낸 시간</th><td colspan="4">${emailVO.sendTime}</td>
				</tr>
				
				<tr>
					<th width="20%">보낸 사람</th><td colspan="2">${emailVO.fromName}</td>
					<th>보낸 사람 주소</th><td colspan="2">${emailVO.fromEmail}</td>
				</tr>
				
				<tr>
					<th width="20%">받는 사람</th><td colspan="2">${emailVO.to_name}</td>
					<th>받는 사람 주소</th><td colspan="2">${emailVO.to_email}</td>
				</tr>
				
				<tr>
					<th width="20%">제목</th><td colspan="4">${emailVO.subject}</td>
				</tr>
				
				<tr>
					<th class="align-middle">내용</th><td colspan="6">${emailVO.content}</td>
				</tr>
			</thead>
				
		</table>
		
		<div class="d-flex">
			<button class="btn btn-success ml-auto" data-seq="${emailVO.emsSeq}" id="btn-update" onclick="location.href='${rootPath}/ems/update'">메일수정</button>
			<button class="btn btn-success" data-seq="${emailVO.emsSeq}" id="btn-delete" onclick="location.href='${rootPath}/ems/delete'">메일삭제</button>
		</div>	
		
	</div>	
</section>	
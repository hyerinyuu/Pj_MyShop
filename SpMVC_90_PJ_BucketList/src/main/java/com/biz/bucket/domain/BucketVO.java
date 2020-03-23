package com.biz.bucket.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("bucketVO")
public class BucketVO {
	
	private long bk_seq;	//number
	private String bk_subject;	//	nVARCHAR2(255)
	private String bk_text;	//	nVARCHAR2(1000)
	private String bk_date;
	private String bk_time;	//	nVARCHAR2(30)
	private String bk_complete;	//	VARCHAR2(1)

}

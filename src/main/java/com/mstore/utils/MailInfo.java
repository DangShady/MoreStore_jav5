package com.mstore.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MailInfo {
	String to;
	String cc;
	String bcc;
	String subject;
	String body;
	String files;
	
	
	public MailInfo(String to,String subject,String body) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}

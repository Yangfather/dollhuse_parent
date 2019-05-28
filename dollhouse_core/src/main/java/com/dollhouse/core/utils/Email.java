package com.dollhouse.core.utils;

import java.io.Serializable;
import java.util.Map;

import com.dollhouse.core.entity.BaseEntity;

public class Email extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 4146022057821767273L;
	
	private Long memberId;
	
	private String memberName;
	
	private Integer type;

	private String host;
	
	private Integer port;
	
	private String userName;
	
	private String password;
	
	private String nickName;
	
	private String from;
	
	private String fromAlais;
	
	private String[] to;
	
	private String[] cc;
	
	private String subject;
	
	private String content;
	
	private Integer templateType;
	
	private Map<String, String> templateParam;

	public Long getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId 接收用户ID
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName 接收用户
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getType() {
		return type;
	}

	/**
	 * @param type 邮件类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	/**
	 * @param host 邮箱服务器
	 */
	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	/**
	 * @param port 邮箱服务器端口
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName 发送邮箱(邮箱配置信息)
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * @param password 邮箱密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName 发送者昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFrom() {
		return from;
	}

	/**
	 * @param from 发送邮箱(会覆盖userName)
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	public String getFromAlais() {
		return fromAlais;
	}

	/**
	 * @param fromAlais 发送邮箱别名(会覆盖nickName)
	 */
	public void setFromAlais(String fromAlais) {
		this.fromAlais = fromAlais;
	}

	public String[] getTo() {
		return to;
	}

	/**
	 * @param to 接收邮箱
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getCc() {
		return cc;
	}

	/**
	 * @param cc 抄送
	 */
	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject 发送主题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	/**
	 * @param content 发送内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	/**
	 * @param templateType 邮件模板类型
	 */
	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public Map<String, String> getTemplateParam() {
		return templateParam;
	}

	/**
	 * @param templateParam 模板中需要替换的参数列表，如#code# -- 123456
	 */
	public void setTemplateParam(Map<String, String> templateParam) {
		this.templateParam = templateParam;
	}
}
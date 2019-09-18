package com.cwh.entity;

public class User {
	private int id;
	private String username;
	private String email;
	private String sex;
	private String city;
	private String sign;
	private int experience;
	private String ip;
	private int logins;
	private String joinTime;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(int id, String username, String email, String sex, String city, String sign, int experience, String ip,
			int logins, String joinTime) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.sex = sex;
		this.city = city;
		this.sign = sign;
		this.experience = experience;
		this.ip = ip;
		this.logins = logins;
		this.joinTime = joinTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getLogins() {
		return logins;
	}
	public void setLogins(int logins) {
		this.logins = logins;
	}
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	
}

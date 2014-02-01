package com.dmurphy.dnd.csheet.character;

public class CharClass {
	
	private String name;
	private String picName;
	private Role role;
	private Source source;
	
	public enum Role {
		LEADER, DEFENDER, CONTROLLER, STRIKER
	}
	
	public enum Source {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

}

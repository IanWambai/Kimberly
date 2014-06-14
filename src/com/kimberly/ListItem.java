package com.kimberly;

public class ListItem {
	private String name;
	private String description;

	public ListItem(String i, String d) {
		this.description = d;
		this.name = i;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
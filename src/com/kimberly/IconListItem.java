package com.kimberly;

import android.graphics.drawable.Drawable;

public class IconListItem {
	private String name;
	private String description;
	private Drawable drawable;

	public IconListItem(String i, String d, Drawable res) {
		this.description = d;
		this.name = i;
		this.setDrawable(res);
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

	public Drawable getDrawable() {
		return drawable;
	}

	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}
}
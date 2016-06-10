package com.github.funthomas424242.feature.toggle.example.domain;

public class Text {

	private Integer id;

	private final String name;

	public void setId(final int id) {
		this.id = new Integer(id);
	}

	public Text(final String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}

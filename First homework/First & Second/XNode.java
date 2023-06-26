package com.cisco;

import java.util.*;

public class XNode implements GNode {
	private String name;
	private List<GNode> children;

	public XNode(String name) {
		this.name = name;
		this.children = new ArrayList<>();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<GNode> getChildren() {
		return this.children;
	}
}

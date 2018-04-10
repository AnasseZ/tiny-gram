package com.anasse.entity;

import java.util.ArrayList;

public class MessageIndex {
	
	private String id;
	
	private ArrayList<String> followersId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<String> getFollowersId() {
		return followersId;
	}

	public void setFollowersId(ArrayList<String> followersId) {
		this.followersId = followersId;
	}

}

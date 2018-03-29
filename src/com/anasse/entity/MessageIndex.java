package com.anasse.entity;

import java.util.ArrayList;

public class MessageIndex {
	
	private int id;
	
	private ArrayList<Integer> followersId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Integer> getFollowersId() {
		return followersId;
	}

	public void setFollowersId(ArrayList<Integer> followersId) {
		this.followersId = followersId;
	}

}

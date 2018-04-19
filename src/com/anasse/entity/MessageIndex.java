package com.anasse.entity;

import java.util.ArrayList;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class MessageIndex {
	
	@PrimaryKey
	@Persistent
	private Key id;
	
	@Persistent
	private ArrayList<String> followers;

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public ArrayList<String> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<String> followers) {
		this.followers = followers;
	}

}

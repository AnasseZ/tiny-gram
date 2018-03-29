package com.anasse.entity;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class User {

	@PrimaryKey
	@Persistent
	private int id;
	
	@Persistent
	private String userName;
	
	@Persistent
	private String firstName;
	
	@Persistent
	private String lastName;
	
	@Persistent
	private ArrayList<Integer> followings;
	
	@Persistent
	private ArrayList<Integer> followers;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Integer> getFollowings() {
		return followings;
	}

	public void setFollowings(ArrayList<Integer> followings) {
		this.followings = followings;
	}

	public ArrayList<Integer> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<Integer> followers) {
		this.followers = followers;
	}
}

package com.anasse.entity;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Message {

	@PrimaryKey
	@Persistent
	private Key postId;
	
	@Persistent
	private String content;
	
	@Persistent
	private ArrayList<String> hashtags; 
	
	@Persistent
	private String userId;
	
	@Persistent
	private String imageUrl;
	
	@Persistent
	private Date publicationDate;
	
	private IncompleteUser user;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(ArrayList<String> hashtags) {
		this.hashtags = hashtags;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Key getPostId() {
		return postId;
	}

	public void setPostId(Key postId) {
		this.postId = postId;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public IncompleteUser getUser() {
		return user;
	}

	public void setUser(IncompleteUser user) {
		this.user = user;
	}
}

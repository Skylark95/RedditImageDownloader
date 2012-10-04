package com.skylark95.redditdownloader.beans;

import java.io.Serializable;
import java.util.List;

public class RedditUser implements Serializable {

	private static final long serialVersionUID = -7140887513885492450L;
	private String username;
	private List<String> comments;
	private List<String> submissions;
	
	public RedditUser() {
	}
	
	public RedditUser(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public List<String> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<String> submissions) {
		this.submissions = submissions;
	}

}

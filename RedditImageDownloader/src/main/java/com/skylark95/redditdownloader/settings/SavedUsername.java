package com.skylark95.redditdownloader.settings;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class SavedUsername implements Comparable<SavedUsername> {

	@XStreamAlias("name")
	@XStreamAsAttribute
	private String username;

	@XStreamAlias("time")
	@XStreamAsAttribute
	private Date timestamp;

	public SavedUsername() {
	}

	public SavedUsername(String username, Date timestamp) {
		this.username = username;
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int compareTo(SavedUsername o) {
		return getTimestamp().compareTo(o.getTimestamp());
	}

}

package com.skylark95.redditdownloader.settings;

import java.io.Serializable;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class SavedDirectory implements Serializable, Comparable<SavedDirectory> {

	private static final long serialVersionUID = -7027746386021627262L;

	@XStreamAlias("path")
	@XStreamAsAttribute
	private String directoryPath;

	@XStreamAlias("time")
	@XStreamAsAttribute
	private Date timestamp;

	public SavedDirectory() {
	}

	public SavedDirectory(String directoryPath, Date timestamp) {
		this.directoryPath = directoryPath;
		this.timestamp = timestamp;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int compareTo(SavedDirectory o) {
		return getTimestamp().compareTo(o.getTimestamp());
	}

}

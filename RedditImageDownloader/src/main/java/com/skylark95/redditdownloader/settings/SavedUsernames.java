package com.skylark95.redditdownloader.settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class SavedUsernames implements Serializable {
	
	private static final long serialVersionUID = 1490219291142002610L;

	@XStreamOmitField
	private String currentUsername;
	
	@XStreamImplicit(itemFieldName = "username")
	private List<SavedUsername> usernames;
	
	@XStreamAlias("keepHistory")
	@XStreamAsAttribute
	private boolean saveUsernames;
	
	@XStreamAlias("max")
	@XStreamAsAttribute
	private int usernameCount;
	
	public SavedUsernames() {
		usernames = new ArrayList<SavedUsername>();
	}

	public List<SavedUsername> getUsernames() {
		return usernames;
	}

	public void setUsernames(List<SavedUsername> usernames) {
		this.usernames = usernames;
	}

	public boolean isSaveUsernames() {
		return saveUsernames;
	}

	public void setSaveUsernames(boolean saveUsernames) {
		this.saveUsernames = saveUsernames;
	}

	public int getUsernameCount() {
		return usernameCount;
	}

	public void setUsernameCount(int usernameCount) {
		this.usernameCount = usernameCount;
	}

	public String getCurrentUsername() {
		return currentUsername;
	}

	public void setCurrentUsername(String currentUsername) {
		this.currentUsername = currentUsername;
	}
	
	

}

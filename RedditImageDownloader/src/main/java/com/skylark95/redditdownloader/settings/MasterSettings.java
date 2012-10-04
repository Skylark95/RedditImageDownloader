package com.skylark95.redditdownloader.settings;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("RedditImageDownloader")
public class MasterSettings implements Serializable, Settings {

	private static final long serialVersionUID = -6667007555343566353L;

	@XStreamAlias("version")
	@XStreamAsAttribute
	private String programVersion;

	@XStreamAlias("settings")
	private UserConfig userConfig;

	@XStreamAlias("usernames")
	private SavedUsernames savedUsernames;
	
	@XStreamAlias("directories")
	private SavedDirectories savedDirectories;
	
	public MasterSettings() {
		userConfig = new UserConfig();
		savedUsernames = new SavedUsernames();
		savedDirectories = new SavedDirectories();
	}
	
	public MasterSettings(String version) {
		this();
		programVersion = version;
	}

	public String getProgramVersion() {
		return programVersion;
	}

	public void setProgramVersion(String programVersion) {
		this.programVersion = programVersion;
	}

	public UserConfig getUserConfig() {
		return userConfig;
	}

	public void setUserConfig(UserConfig userConfig) {
		this.userConfig = userConfig;
	}

	public SavedUsernames getSavedUsernames() {
		return savedUsernames;
	}

	public void setSavedUsernames(SavedUsernames savedUsernames) {
		this.savedUsernames = savedUsernames;
	}

	public SavedDirectories getSavedDirectories() {
		return savedDirectories;
	}

	public void setSavedDirectories(SavedDirectories savedDirectories) {
		this.savedDirectories = savedDirectories;
	}
	
	

}

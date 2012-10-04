package com.skylark95.redditdownloader.settings;


public interface Settings {
	
	public String getProgramVersion();
	
	public void setProgramVersion(String programVersion);
	
	public UserConfig getUserConfig();
	
	public void setUserConfig(UserConfig userSettings);
	
	public SavedUsernames getSavedUsernames();
	
	public void setSavedUsernames(SavedUsernames savedUsernames);
	
	public SavedDirectories getSavedDirectories();
	
	public void setSavedDirectories(SavedDirectories savedDirectories);

}

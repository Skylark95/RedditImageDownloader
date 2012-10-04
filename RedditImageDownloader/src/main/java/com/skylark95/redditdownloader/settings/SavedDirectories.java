package com.skylark95.redditdownloader.settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class SavedDirectories implements Serializable {
	
	private static final long serialVersionUID = 166601157311998082L;

	@XStreamOmitField
	private String currentDirectory;
	
	@XStreamImplicit(itemFieldName = "directory")
	private List<SavedDirectory> directories;
	
	@XStreamAlias("keepHistory")
	@XStreamAsAttribute
	private boolean saveDirectories;
	
	@XStreamAlias("max")
	@XStreamAsAttribute
	private int directoryCount;
	
	public SavedDirectories() {
		directories = new ArrayList<SavedDirectory>();
	}

	public List<SavedDirectory> getDirectories() {
		return directories;
	}

	public void setDirectories(List<SavedDirectory> directories) {
		this.directories = directories;
	}

	public boolean isSaveDirectories() {
		return saveDirectories;
	}

	public void setSaveDirectories(boolean saveDirectories) {
		this.saveDirectories = saveDirectories;
	}

	public int getDirectoryCount() {
		return directoryCount;
	}

	public void setDirectoryCount(int directoryCount) {
		this.directoryCount = directoryCount;
	}

	public String getCurrentDirectory() {
		return currentDirectory;
	}

	public void setCurrentDirectory(String currentDirectory) {
		this.currentDirectory = currentDirectory;
	}
	
	

}

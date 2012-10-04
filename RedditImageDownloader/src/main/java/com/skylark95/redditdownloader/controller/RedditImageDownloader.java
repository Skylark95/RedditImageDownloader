package com.skylark95.redditdownloader.controller;

import java.util.Map;

import com.skylark95.redditdownloader.beans.ProgramProperties;

public interface RedditImageDownloader {
	
	public void download(Map<String, String> downloadSettings) throws DownloaderExpection;
	
	public ProgramProperties getProgramProperties();
	
}

package com.skylark95.redditdownloader.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.beans.ProgramProperties;
import com.skylark95.redditdownloader.beans.RedditUser;
import com.skylark95.redditdownloader.core.ProgramPropertiesFactory;
import com.skylark95.redditdownloader.core.RedditUserExpection;
import com.skylark95.redditdownloader.core.RedditUserFactory;
import com.skylark95.redditdownloader.core.SettingsExecption;
import com.skylark95.redditdownloader.core.SettingsFactory;
import com.skylark95.redditdownloader.io.FileDownloader;
import com.skylark95.redditdownloader.io.PropertyException;
import com.skylark95.redditdownloader.settings.Settings;
import com.skylark95.redditdownloader.util.ImageExtractor;

public class RedditImageDownloaderImpl implements RedditImageDownloader {

	private static Logger LOG = Logger.getLogger(RedditImageDownloaderImpl.class);
	
	private ProgramProperties programProperties;

	@Override
	public void download(Map<String, String> downloadSettings) throws DownloaderExpection {
		LOG.info("*** Welcome! Now Starting Reddit Image Downloader by Skylark95 ***");
		Settings settings = getSettings(downloadSettings);
		RedditUser redditUser = getRedditUser(settings);
		List<String> content = getContent(redditUser);
		List<String> images = getImages(content);
		downloadImages(images, settings.getSavedDirectories().getCurrentDirectory());
		LOG.info("*** DOWNLOAD COMPLETE.  Have a nice day :-) ***");
	}
	
	@Override
	public ProgramProperties getProgramProperties() {
		if (programProperties == null) {
			programProperties = ProgramPropertiesFactory.build();
		}
		
		return programProperties;
	}

	private void downloadImages(List<String> images, String directory) {
		FileDownloader fileDownloader = new FileDownloader();
		
		LOG.info("*** DOWNLOADING IMAGES ***");
		fileDownloader.download(images, directory);
	}

	private List<String> getImages(List<String> content) throws DownloaderExpection {
		List<String> images = new ArrayList<String>();
		ImageExtractor imageExtractor = null;
		
		try {
			imageExtractor = new ImageExtractor();
		} catch (PropertyException e) {
			String message = e.getMessage();
			LOG.error(message);
			throw new DownloaderExpection(message, e);
		}

		LOG.info("*** SEARCHING FOR IMAGES ***");
		images.addAll(imageExtractor.extractImages(content));
		return images;
	}

	private List<String> getContent(RedditUser redditUser) {
		List<String> content = new ArrayList<String>();
		content.addAll(redditUser.getComments());
		content.addAll(redditUser.getSubmissions());
		return content;
	}

	private RedditUser getRedditUser(Settings settings) throws DownloaderExpection {
		RedditUser redditUser = null;
		try {
			redditUser = RedditUserFactory.build(settings);
		} catch (RedditUserExpection e) {
			String message = e.getMessage();
			LOG.error(message, e);
			throw new DownloaderExpection(message, e);
		}
		return redditUser;
	}

	private Settings getSettings(Map<String, String> downloadSettings) throws DownloaderExpection {
		Settings settings = null;
		try {
			settings = SettingsFactory.build(downloadSettings);
		} catch (SettingsExecption e) {
			String message = e.getMessage();
			LOG.error(message, e);
			throw new DownloaderExpection(message, e);
		}
		return settings;
	}

	
}

package com.skylark95.redditdownloader.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.io.PropertyException;
import com.skylark95.redditdownloader.io.RedditFileReader;
import com.skylark95.redditdownloader.io.RedditFileWriter;
import com.skylark95.redditdownloader.settings.MasterSettings;
import com.skylark95.redditdownloader.settings.SavedDirectory;
import com.skylark95.redditdownloader.settings.SavedUsername;
import com.skylark95.redditdownloader.settings.Settings;

public class SettingsFactory {

	private static Settings settings;
	private static Logger LOG = Logger.getLogger(SettingsFactory.class);
	
	private SettingsFactory() {
		
	}

	public static Settings build(Map<String, String> settingsMap) throws SettingsExecption {
		loadSettings();
		updateSettings(settingsMap);
		return settings;
	}

	private static void loadSettings() throws SettingsExecption {
		LOG.debug("Looking for exsiting settings file");
		RedditFileReader fileReader = new RedditFileReader();
		settings = fileReader.readMasterSettings();
		if (settings == null) {
			LOG.debug("No Existing Settings found.");
			settings = new MasterSettings();
		}
	}

	private static void updateSettings(Map<String, String> settingsMap) throws SettingsExecption {
		LOG.debug("Parsing Map and updating settings");
		String username = settingsMap.get("username");
		String keepUsernames = settingsMap.get("keepUsernames");
		String maxUsernames = settingsMap.get("maxUsernames");
		String saveDirectory = settingsMap.get("saveDirectory");
		String keepDirectories = settingsMap.get("keepDirectories");
		String maxDirectories = settingsMap.get("maxDirectories");
		String imageLimit = settingsMap.get("imageLimit");
		String searchComments = settingsMap.get("searchComments");
		String commentsLimit = settingsMap.get("commentsLimit");
		String searchUrlSubmissions = settingsMap.get("searchSubmissions");
		String submissionsLimit = settingsMap.get("submissionsLimit");
		String searchSelfPosts = settingsMap.get("searchSelfPosts");

		saveProgramVersion();
		LOG.info("*** CURRENT SETTINGS ***");
		saveUserName(username, keepUsernames, maxUsernames);
		saveDirectory(saveDirectory, keepDirectories, maxDirectories);
		saveDownloadOptions(imageLimit);
		saveComments(searchComments, commentsLimit);
		saveSubmissions(searchUrlSubmissions, submissionsLimit);
		saveSelfPosts(searchSelfPosts);		
		saveSettings();
	}

	private static void saveProgramVersion() throws SettingsExecption {
		LOG.debug("Adding program version to settings");
		RedditFileReader fileReader = new RedditFileReader();
		String version = null;
		try {
			version = fileReader.readProperty("Global.programVersion");
			LOG.info("Current Program Version: " + version);
		} catch (PropertyException e) {
			String message = "Error reading program version";
			LOG.error(message);
			throw new SettingsExecption(message, e);
		}
		settings.setProgramVersion(version);
	}
	
	private static void logBoolean(String message, boolean val) {
		if (val) {
			LOG.info(message + ": YES");
		} else {
			LOG.info(message + ": NO");
		}
	}

	private static void saveSelfPosts(String searchSelfPostsStr) throws SettingsExecption {
		boolean searchSelfPosts = Boolean.parseBoolean(searchSelfPostsStr);
		logBoolean("Search Self Posts" , searchSelfPosts);
		settings.getUserConfig().getSearchOptions().setSearchSelfPosts(searchSelfPosts);
	}

	private static void saveSubmissions(String searchUrlSubmissionsStr, String submissionsLimitStr)
			throws SettingsExecption {
		int submissionsLimit = convertStringtoInt("Submissions Limit", submissionsLimitStr);
		boolean searchUrlSubmissions = Boolean.parseBoolean(searchUrlSubmissionsStr);
		logBoolean("Search Submissions", searchUrlSubmissions);
		LOG.info("Max pages to read for URL Submissions: " + submissionsLimit);
		settings.getUserConfig().getSearchOptions().setSearchUrlSubmissions(searchUrlSubmissions);
		settings.getUserConfig().getPageLimits().setUrlSubmissionsPageLimit(submissionsLimit);
	}

	private static void saveComments(String searchCommentsStr, String commentsLimitStr) throws SettingsExecption {
		int commentsLimit = convertStringtoInt("Comments Limit", commentsLimitStr);
		boolean searchComments = Boolean.parseBoolean(searchCommentsStr);
		logBoolean("Search Comments", searchComments);
		LOG.info("Max pages to read for Comments: " + commentsLimit);
		settings.getUserConfig().getSearchOptions().setSearchComments(searchComments);
		settings.getUserConfig().getPageLimits().setCommentsPageLimit(commentsLimit);
	}

	private static void saveDirectory(String saveDirectory, String keepDirectoriesStr, String maxDirectoriesStr)
			throws SettingsExecption {		
		validateSaveDirectory(saveDirectory);

		LOG.info("Entered Save Location: " + saveDirectory);
		
		boolean keepDirectories = Boolean.parseBoolean(keepDirectoriesStr);
		int maxDirectories = convertStringtoInt("Max Directories", maxDirectoriesStr);
		List<SavedDirectory> directories = new ArrayList<SavedDirectory>();

		if (keepDirectories) {
			directories = organizeSavedDirectories(maxDirectories);
			directories.add(new SavedDirectory(saveDirectory, new Date()));
			Collections.sort(directories);
		}

		settings.getSavedDirectories().setCurrentDirectory(saveDirectory);
		settings.getSavedDirectories().setSaveDirectories(keepDirectories);
		settings.getSavedDirectories().setDirectoryCount(maxDirectories);
		settings.getSavedDirectories().setDirectories(directories);
	}

	private static void validateSaveDirectory(String saveDirectory) throws SettingsExecption {
		if (saveDirectory == null || saveDirectory.trim().length() == 0) {
			String message = "Save Directory cannot be blank";
			LOG.error(message);
			throw new SettingsExecption(message);
		}
		
		File file = new File(saveDirectory);
		if (!file.exists()) {
			if (!file.mkdirs()) {
				String message = "Invalid Save Directory";
				LOG.error(message);
				throw new SettingsExecption(message);
			}
		}		
	}

	private static List<SavedDirectory> organizeSavedDirectories(int maxDirectories) {
		List<SavedDirectory> directories = settings.getSavedDirectories().getDirectories();

		if (directories == null) {
			directories = new ArrayList<SavedDirectory>();
		}

		Collections.sort(directories);
		
		while (directories.size() > 0 && directories.size() >= maxDirectories) {
			directories.remove(0);
		}

		return directories;
	}

	private static void saveUserName(String username, String keepUsernamesStr, String maxUsernamesStr)
			throws SettingsExecption {
		validateUsername(username);
		
		LOG.info("Entered Reddit User: " + username);

		boolean keepUsernames = Boolean.parseBoolean(keepUsernamesStr);
		int maxUsernames = convertStringtoInt("Max Usernames", maxUsernamesStr);
		List<SavedUsername> usernames = new ArrayList<SavedUsername>();

		if (keepUsernames) {
			usernames = organizeSavedUsernames(maxUsernames);
			usernames.add(new SavedUsername(username, new Date()));
			Collections.sort(usernames);
		}

		settings.getSavedUsernames().setCurrentUsername(username);
		settings.getSavedUsernames().setSaveUsernames(keepUsernames);
		settings.getSavedUsernames().setUsernameCount(maxUsernames);
		settings.getSavedUsernames().setUsernames(usernames);
	}

	private static void validateUsername(String username) throws SettingsExecption {
		if (username == null || username.trim().length() == 0) {
			String message = "Username cannot be blank";
			LOG.error(message);
			throw new SettingsExecption(message);
		}
	}

	private static List<SavedUsername> organizeSavedUsernames(int maxUsernames) {
		List<SavedUsername> usernames = settings.getSavedUsernames().getUsernames();

		if (usernames == null) {
			usernames = new ArrayList<SavedUsername>();
		}

		Collections.sort(usernames);

		while (usernames.size() > 0 && usernames.size() >= maxUsernames) {
			usernames.remove(0);
		}

		return usernames;
	}

	private static void saveDownloadOptions(String imageLimitStr) throws SettingsExecption {
		int imageLimit = convertStringtoInt("Image Limit", imageLimitStr);
		settings.getUserConfig().getDownloadOptions().setImageLimit(imageLimit);
	}

	private static void saveSettings() throws SettingsExecption {
		LOG.debug("Savings settings to file.");
		RedditFileWriter fileWriter = new RedditFileWriter();
		try {
			fileWriter.writeSettings(settings);
		} catch (IOException e) {
			String message = "An error occured while saving settings.";
			String detail = "ERROR DETAILS: " + e.getMessage();
			LOG.error(message);
			LOG.error(detail, e);
			throw new SettingsExecption(message, e);
		}
	}

	private static int convertStringtoInt(String name, String value) throws SettingsExecption {
		int returnVal = 0;
		try {
			returnVal = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			String message = name + " must be numeric";
			LOG.error(message);
			throw new SettingsExecption(message, e);
		}
		return returnVal;
	}

}

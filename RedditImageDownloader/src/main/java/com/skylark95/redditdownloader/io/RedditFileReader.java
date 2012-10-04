package com.skylark95.redditdownloader.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.settings.MasterSettings;
import com.skylark95.redditdownloader.settings.SavedDirectories;
import com.skylark95.redditdownloader.settings.SavedDirectory;
import com.skylark95.redditdownloader.settings.SavedUsername;
import com.skylark95.redditdownloader.settings.SavedUsernames;
import com.skylark95.redditdownloader.settings.Settings;
import com.skylark95.redditdownloader.settings.UserConfig;
import com.thoughtworks.xstream.XStream;

public class RedditFileReader {

	private static final String PROPERTIES_PATH = "config.properties";
	private static Logger LOG = Logger.getLogger(RedditFileReader.class);


	public String readProperty(String property) throws PropertyException {
		LOG.debug("Fetching property: " + property);
		Properties prop = new Properties();
		InputStream in = ClassLoader.getSystemResourceAsStream(PROPERTIES_PATH);
		if (in == null) {
			String message = "Resource " + PROPERTIES_PATH + " cannot be found.";
			LOG.error(message);
			throw new PropertyException(message);
		}
		try {
			prop.load(in);
		} catch (IOException e) {
			String message = "Could not read resource " + PROPERTIES_PATH;
			LOG.error(message);
			throw new PropertyException(message, e);
		}
		String value = prop.getProperty(property);
		LOG.debug("PROPERTY: " + property + "=" + value);
		return value;
	}


	public Settings readMasterSettings() {
		Settings settings = null;
		String filename = "settings.xml";
		File file = new File(filename);
		LOG.debug("Reading settings from file: " + filename);
		XStream xStream = new XStream();
		xStream.processAnnotations(new Class[] { MasterSettings.class, UserConfig.class, SavedDirectories.class,
				SavedUsernames.class, SavedDirectory.class, SavedUsername.class });
		if (file.exists()) {
			settings = (Settings) xStream.fromXML(file);
			LOG.debug("Settings read from file: " + filename);
		} else {
			LOG.debug("Settings file does not exist: " + filename);
		}		
		return settings;
	}
}

package com.skylark95.redditdownloader.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.settings.MasterSettings;
import com.skylark95.redditdownloader.settings.SavedDirectories;
import com.skylark95.redditdownloader.settings.SavedDirectory;
import com.skylark95.redditdownloader.settings.SavedUsername;
import com.skylark95.redditdownloader.settings.SavedUsernames;
import com.skylark95.redditdownloader.settings.Settings;
import com.skylark95.redditdownloader.settings.UserConfig;
import com.thoughtworks.xstream.XStream;

public class RedditFileWriter {
	
	private static Logger LOG = Logger.getLogger(RedditFileWriter.class);

	public void writeSettings(Settings settings) throws IOException {
		String filename = "settings.xml";
		LOG.debug("Writing settings to file: " + filename);
		XStream xStream = new XStream();
		xStream.processAnnotations(new Class[] { MasterSettings.class, UserConfig.class, SavedDirectories.class,
				SavedUsernames.class, SavedDirectory.class, SavedUsername.class });
		Writer out = new FileWriter(filename);
		xStream.toXML(settings, out);
		LOG.debug("Settings written to file: " + filename);
	}

}

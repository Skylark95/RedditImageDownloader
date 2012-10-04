package com.skylark95.redditdownloader.core;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.beans.ProgramProperties;
import com.skylark95.redditdownloader.io.PropertyException;
import com.skylark95.redditdownloader.io.RedditFileReader;

public class ProgramPropertiesFactory {
	
	private static Logger LOG = Logger.getLogger(ProgramPropertiesFactory.class);
	
	private ProgramPropertiesFactory() {
		
	}

	public static ProgramProperties build() {
		LOG.debug("Building ProgramProperties");
		ProgramProperties programProperties = new ProgramProperties();
		RedditFileReader fileReader = new RedditFileReader();
		
		programProperties.setProgramName(getProgramName(fileReader));
		programProperties.setProgramVersion(getProgramVersion(fileReader));
		
		return programProperties;
	}

	private static String getProgramVersion(RedditFileReader fileReader) {
		String programVersion = null;
		
		try {
			programVersion = fileReader.readProperty("Global.programVersion");
		} catch (PropertyException e) {
			LOG.error("Error reading program version from properties", e);
		}
		
		return programVersion;
	}

	private static String getProgramName(RedditFileReader fileReader) {
		String programName = null;
		
		try {
			programName = fileReader.readProperty("Global.programName");
		} catch (PropertyException e) {
			LOG.error("Error reading program name from properties", e);
		}
		
		return programName;
	}

}

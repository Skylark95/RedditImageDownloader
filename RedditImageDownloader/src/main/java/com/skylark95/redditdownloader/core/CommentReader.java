package com.skylark95.redditdownloader.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.io.PropertyException;

public class CommentReader extends RedditReader {

	private static Logger LOG = Logger.getLogger(CommentReader.class);
	
	public CommentReader() throws PropertyException {
		super();
	}

	@Override
	public List<String> read(String username, int pageLimit) throws IOException, PropertyException {
		LOG.info("Reading Comments");
		String url = "http://www.reddit.com/user/" + username + "/comments/.json";
		List<String> keys = new ArrayList<String>();
		keys.add("body");
		return readRedditProfile(url, keys, pageLimit);
	}

}

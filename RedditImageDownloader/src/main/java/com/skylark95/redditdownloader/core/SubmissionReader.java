package com.skylark95.redditdownloader.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.io.PropertyException;

public class SubmissionReader extends RedditReader {

	private static Logger LOG = Logger.getLogger(RedditReader.class);
	private boolean readUrlSubmission = true;
	private boolean readSelfPost = true;

	public SubmissionReader() throws PropertyException {
		super();
	}

	@Override
	public List<String> read(String username, int pageLimit) throws IOException, PropertyException {
		LOG.info("Reading Submissions");
		String url = "http://www.reddit.com/user/" + username + "/submitted/.json";
		List<String> keys = new ArrayList<String>();
		if (isReadUrlSubmission()) {
			keys.add("url");
		}
		if (isReadSelfPost()) {
			keys.add("selftext");
		}
		List<String> values = new ArrayList<String>();
		if (isReadUrlSubmission() || isReadSelfPost()) {
			values = readRedditProfile(url, keys, pageLimit);
		}
		return values;
	}

	public boolean isReadUrlSubmission() {
		return readUrlSubmission;
	}

	public void setReadUrlSubmission(boolean readUrlSubmission) {
		this.readUrlSubmission = readUrlSubmission;
	}

	public boolean isReadSelfPost() {
		return readSelfPost;
	}

	public void setReadSelfPost(boolean readSelfPost) {
		this.readSelfPost = readSelfPost;
	}

}

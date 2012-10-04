package com.skylark95.redditdownloader.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.beans.RedditUser;
import com.skylark95.redditdownloader.io.PropertyException;
import com.skylark95.redditdownloader.settings.Settings;
import com.skylark95.redditdownloader.settings.UserConfig;
import com.skylark95.redditdownloader.settings.UserConfig.SearchOptions;

public class RedditUserFactory {

	private static RedditUser user;
	private static Logger LOG = Logger.getLogger(RedditUserFactory.class);
	
	private RedditUserFactory() {
		
	}

	public static RedditUser build(Settings settings) throws RedditUserExpection {
		String username = settings.getSavedUsernames().getCurrentUsername();
		UserConfig userConfig = settings.getUserConfig();
		user = new RedditUser(username);
		List<String> comments = new ArrayList<String>();
		List<String> submissions = new ArrayList<String>();

		LOG.info("*** BEGINNING PAGE READ ***");
		try {
			comments.addAll(getComments(username, userConfig));
			submissions.addAll(getSubmissions(username, userConfig));
		} catch (PropertyException e) {
			String message = e.getMessage();
			LOG.error(message, e);
			throw new RedditUserExpection(message, e);
		} catch (IOException e) {
			String message = e.getMessage();
			LOG.error(message, e);
			throw new RedditUserExpection(message, e);
		}
		
		user.setComments(comments);
		user.setSubmissions(submissions);
		
		return user;
	}

	private static List<String> getSubmissions(String username, UserConfig userConfig) throws PropertyException,
			IOException {
		List<String> submissions = new ArrayList<String>();
		SearchOptions searchOptions = userConfig.getSearchOptions();
		int pageLimit = userConfig.getPageLimits().getSubmissionsPageLimit();

		if (searchOptions.isSearchSelfPosts() || searchOptions.isSearchUrlSubmissions()) {
			SubmissionReader reader = new SubmissionReader();
			if (!searchOptions.isSearchSelfPosts()) {
				reader.setReadSelfPost(false);
				LOG.debug("Will NOT read Self Posts");
				submissions = reader.read(username, pageLimit);
			} else if (!searchOptions.isSearchUrlSubmissions()) {
				reader.setReadUrlSubmission(false);
				LOG.debug("Will NOT read URL Submissions");
				submissions = reader.read(username, pageLimit);
			} else {
				LOG.debug("Will read Self Posts and URL Submissions");
				submissions = reader.read(username, pageLimit);
			}
		} else {
			LOG.debug("Will NOT read any Submissions");
		}

		return submissions;
	}

	private static List<String> getComments(String username, UserConfig userConfig) throws PropertyException,
			IOException {
		List<String> comments = new ArrayList<String>();
		SearchOptions searchOptions = userConfig.getSearchOptions();
		int pageLimit = userConfig.getPageLimits().getCommentsPageLimit();

		if (searchOptions.isSearchComments()) {
			LOG.debug("Will read Comments");
			CommentReader reader = new CommentReader();
			comments = reader.read(username, pageLimit);
		} else {
			LOG.debug("Will NOT read Comments");
		}
		return comments;
	}

}

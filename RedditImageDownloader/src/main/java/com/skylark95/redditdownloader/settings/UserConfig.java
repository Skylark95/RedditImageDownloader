package com.skylark95.redditdownloader.settings;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.basic.BooleanConverter;

@XStreamAlias("setting")
public class UserConfig implements Serializable {

	private static final long serialVersionUID = -4182338287591969340L;

	private DownloadOptions downloadOptions;

	private SearchOptions searchOptions;
	
	private PageLimits pageLimits;

	public UserConfig() {
		downloadOptions = new DownloadOptions();
		searchOptions = new SearchOptions();
		pageLimits = new PageLimits();
	}

	public DownloadOptions getDownloadOptions() {
		return downloadOptions;
	}

	public SearchOptions getSearchOptions() {
		return searchOptions;
	}
	
	public PageLimits getPageLimits() {
		return pageLimits;
	}

	public static class DownloadOptions {
		@XStreamAlias("maxImages")
		private int imageLimit;

		public int getImageLimit() {
			return imageLimit;
		}

		public void setImageLimit(int imageLimit) {
			this.imageLimit = imageLimit;
		}

	}

	public static class PageLimits {

		@XStreamAlias("comments")
		private int commentsPageLimit;

		@XStreamAlias("submissions")
		private int urlSubmissionsPageLimit;

		public int getCommentsPageLimit() {
			return commentsPageLimit;
		}

		public void setCommentsPageLimit(int commentsPageLimit) {
			this.commentsPageLimit = commentsPageLimit;
		}

		public int getSubmissionsPageLimit() {
			return urlSubmissionsPageLimit;
		}

		public void setUrlSubmissionsPageLimit(int urlSubmissionsPageLimit) {
			this.urlSubmissionsPageLimit = urlSubmissionsPageLimit;
		}

	}

	public static class SearchOptions {
		@XStreamAlias("comments")
		@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = { "yes", "no" })
		private boolean searchComments;

		@XStreamAlias("submissions")
		@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = { "yes", "no" })
		private boolean searchUrlSubmissions;

		@XStreamAlias("selfposts")
		@XStreamConverter(value = BooleanConverter.class, booleans = { false }, strings = { "yes", "no" })
		private boolean searchSelfPosts;

		public boolean isSearchComments() {
			return searchComments;
		}

		public void setSearchComments(boolean searchComments) {
			this.searchComments = searchComments;
		}

		public boolean isSearchUrlSubmissions() {
			return searchUrlSubmissions;
		}

		public void setSearchUrlSubmissions(boolean searchUrlSubmissions) {
			this.searchUrlSubmissions = searchUrlSubmissions;
		}

		public boolean isSearchSelfPosts() {
			return searchSelfPosts;
		}

		public void setSearchSelfPosts(boolean searchSelfPosts) {
			this.searchSelfPosts = searchSelfPosts;
		}
	}

}

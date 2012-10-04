package com.skylark95.redditdownloader.core;

public class RedditUserExpection extends Exception {

	private static final long serialVersionUID = 2136184482961103987L;

	public RedditUserExpection() {
		super();
	}

	public RedditUserExpection(String message) {
		super(message);
	}

	public RedditUserExpection(Throwable cause) {
		super(cause);
	}

	public RedditUserExpection(String message, Throwable cause) {
		super(message, cause);
	}

}

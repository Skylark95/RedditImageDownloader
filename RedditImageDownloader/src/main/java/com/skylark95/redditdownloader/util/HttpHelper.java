package com.skylark95.redditdownloader.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.io.PropertyException;
import com.skylark95.redditdownloader.io.RedditFileReader;

public class HttpHelper {

	private static Logger LOG = Logger.getLogger(HttpHelper.class);
	private RedditFileReader reader = new RedditFileReader();

	public InputStream createConnection(String url) throws PropertyException, IOException {
		LOG.debug("Creating connection to URL: " + url);
		String userAgent = fetchUserAgent();
		int connectTimeout = fetchConnectTimeout();
		int readTimeout = fetchReadTimeout();
		URL theUrl = null;
		try {
			theUrl = new URL(url);
		} catch (MalformedURLException e) {
			String message = "Bad URL: " + url;
			LOG.error(message);
			throw new IOException(message, e);
		}
		URLConnection conn = theUrl.openConnection();
		conn.setRequestProperty("User-Agent", userAgent);
		conn.setConnectTimeout(connectTimeout);
		conn.setReadTimeout(readTimeout);
		return conn.getInputStream();
	}

	private String fetchUserAgent() throws PropertyException {
		final String property = "HttpHelper.userAgent";
		String userAgent = reader.readProperty(property);
		if (userAgent == null) {
			String message = "User Agent property is missing";
			LOG.error(message);
			throw new PropertyException(message);
		}
		return userAgent;
	}
	
	private int fetchConnectTimeout() throws PropertyException {
		final String property = "HttpHelper.connectTimeout";
		String timeoutStr = reader.readProperty(property);
		int timeout = 0;
		try {
			timeout = Integer.parseInt(timeoutStr);
		} catch (NumberFormatException e) {
			String message = "Connect Timeout property is either missing or not numeric";
			LOG.error(message);
			throw new PropertyException(message);
		}
		return timeout;
	}
	
	private int fetchReadTimeout() throws PropertyException {
		final String property = "HttpHelper.readTimeout";
		String timeoutStr = reader.readProperty(property);
		int timeout = 0;
		try {
			timeout = Integer.parseInt(timeoutStr);
		} catch (NumberFormatException e) {
			String message = "Read Timeout property is either missing or not numeric";
			LOG.error(message);
			throw new PropertyException(message);
		}
		return timeout;
	}

}

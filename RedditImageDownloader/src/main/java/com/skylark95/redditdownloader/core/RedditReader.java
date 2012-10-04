package com.skylark95.redditdownloader.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.skylark95.redditdownloader.io.PropertyException;
import com.skylark95.redditdownloader.io.RedditFileReader;
import com.skylark95.redditdownloader.util.HttpHelper;

public abstract class RedditReader {

	private static Logger LOG = Logger.getLogger(RedditReader.class);
	private int counter = 0;
	private int pageCounter = 1;
	private String afterValue = null;
	private boolean doneReading;
	private final int entriesPerPage;

	public RedditReader() throws PropertyException {
		entriesPerPage = getEntriesPerPage();
	}

	private int getEntriesPerPage() throws PropertyException {
		final String property = "Reader.entriesPerPage";
		RedditFileReader reader = new RedditFileReader();
		String value = reader.readProperty(property);
		int numEntriesPerPage = -1;
		try {
			numEntriesPerPage = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			String message = "Invalid value for " + property + ": " + value;
			LOG.error(message, e);
			throw new PropertyException(message, e);
		}
		return numEntriesPerPage;
	}

	public abstract List<String> read(String username, int pageLimit) throws IOException, PropertyException;

	List<String> readRedditProfile(String url, List<String> keys, int pageLimit) throws IOException, PropertyException {
		doneReading = false;
		List<String> values = new ArrayList<String>();
		while (!doneReading) {
			values.addAll(readJsonDocument(url, keys, pageLimit));
			LOG.info("Pages Read: " + pageCounter++);
			url = url.split("\\?")[0] + "?after=" + afterValue;
		}
		reset();
		return values;
	}

	private void reset() {
		counter = 0;
		pageCounter = 1;
		doneReading = false;
		afterValue = null;
	}

	private List<String> readJsonDocument(String url, List<String> keys, int pageLimit) throws IOException,
			PropertyException {
		LOG.debug("Reading URL: " + url);
		HttpHelper httpHelper = new HttpHelper();
		InputStream is = httpHelper.createConnection(url);
		Reader in = new InputStreamReader(is);
		JsonReader reader = new JsonReader(in);
		List<String> values = loopThroughJson(reader, keys);
		counter += values.size();
		LOG.debug("Number of values: " + counter);
		if (afterValue == null || counter >= pageLimit * entriesPerPage) {
			doneReading = true;
		}
		return values;
	}

	private List<String> loopThroughJson(JsonReader reader, List<String> keys) throws IOException {
		LOG.debug("NOW PARSING JSON DOCUMENT");
		List<String> values = new ArrayList<String>();
		String name = "";
		while (true) {
			JsonToken token = reader.peek();
			switch (token) {
			case BEGIN_ARRAY:
				reader.beginArray();
				break;
			case END_ARRAY:
				reader.endArray();
				break;
			case BEGIN_OBJECT:
				reader.beginObject();
				break;
			case END_OBJECT:
				reader.endObject();
				break;
			case NAME:
				name = reader.nextName();
				break;
			case STRING:
				String string = reader.nextString();
				Collection<String> extracted = extractValues(keys, name, string);
				values.addAll(extracted);
				if ("after".equals(name)) {
					afterValue = string;
				}
				break;
			case NUMBER:
				reader.nextString();
				break;
			case BOOLEAN:
				reader.nextBoolean();
				break;
			case NULL:
				reader.nextNull();
				if ("after".equals(name)) {
					afterValue = null;
				}
				break;
			case END_DOCUMENT:
				LOG.debug("AFTER = " + afterValue);
				return values;
			}
		}
	}

	private List<String> extractValues(List<String> keys, String name, String string) {
		List<String> values = new ArrayList<String>();
		for (String key : keys) {
			if (key.equals(name)) {
				values.add(string);
				LOG.debug("ADDED VALUE: " + string);
			}
		}
		return values;
	}

}

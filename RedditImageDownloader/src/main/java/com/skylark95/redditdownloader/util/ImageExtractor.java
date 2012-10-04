package com.skylark95.redditdownloader.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.io.PropertyException;
import com.skylark95.redditdownloader.io.RedditFileReader;

public class ImageExtractor {

	private static Logger LOG = Logger.getLogger(ImageExtractor.class);

	private Pattern imagePattern;

	public ImageExtractor() throws PropertyException {
		this(getRegex(), Pattern.CASE_INSENSITIVE);
	}

	public ImageExtractor(String imageRegex, int regexFlags) {
		LOG.debug("Initializing ImageExtractor using regex: " + imageRegex);
		imagePattern = Pattern.compile(imageRegex, regexFlags);
	}

	private static String getRegex() throws PropertyException {
		RedditFileReader fileReader = new RedditFileReader();
		String patternStr = fileReader.readProperty("ImageExtractor.imageRegex");
		return patternStr;
	}

	public List<String> extractImages(Collection<String> s) {
		List<String> images = new ArrayList<String>();
		int size = 0;
		for (String str : s) {
			images.addAll(extractImages(str));
			if (size != images.size()) {
				size = images.size();
				LOG.info("Images Found: " + size);
			}
		}
		return images;
	}

	public List<String> extractImages(String s) {
		Matcher matcher = imagePattern.matcher(s);
		List<String> images = new ArrayList<String>();
		while (matcher.find()) {
			String image = matcher.group();
			images.add(image);
			LOG.debug("ADDED IMAGE: " + image);
		}
		return images;
	}
}

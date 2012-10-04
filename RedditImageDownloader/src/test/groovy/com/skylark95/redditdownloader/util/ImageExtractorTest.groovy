package com.skylark95.redditdownloader.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ImageExtractorTest {

	def imageExtractor = new ImageExtractor()

	@Test
	public void canMatchJPG() {
		def expectedImages = ["http://i.imgur.com/FVUjeh.jpg"]
		def actualImages = imageExtractor.extractImages(expectedImages)
		assert expectedImages == actualImages
	}
	
	@Test
	public void canMatchGIF() {
		def expectedImages = ["http://i.imgur.com/zglMA.gif"]
		def actualImages = imageExtractor.extractImages(expectedImages)
		assert expectedImages == actualImages
	}
	
	@Test
	public void canMatchPNG() {
		def expectedImages = ["http://i.imgur.com/UmLf8.png"]
		def actualImages = imageExtractor.extractImages(expectedImages)
		assert expectedImages == actualImages
	}
	
	@Test
	public void canMatchHttpsImage() {
		def expectedImages = ["https://raw.github.com/Skylark95/RedditImageDownloader/master/Icon/RedditImageDownloaderIcon-64.png"]
		def actualImages = imageExtractor.extractImages(expectedImages)
		assert expectedImages == actualImages
	}
	
	@Test
	public void doesNotMatchWebPage() {
		def inputs = ["http://www.skylark95.com/index.htm"]
		def actualImages = imageExtractor.extractImages(inputs)
		assert actualImages.size() == 0
	}
	
	@Test
	public void canMatchImageAtStartOfInput() {
		def expectedImages = ["http://i.imgur.com/FVUjeh.jpg"]
		def actualImages = imageExtractor.extractImages(expectedImages[0] + "XXX")
		assert expectedImages == actualImages
	}
	
	@Test
	public void canMatchImageAtEndOfInput() {
		def expectedImages = ["http://i.imgur.com/FVUjeh.jpg"]
		def actualImages = imageExtractor.extractImages("XXX" + expectedImages[0])
		assert expectedImages == actualImages
	}
	
	@Test
	public void canMatchImageAtMiddleOfInput() {
		def expectedImages = ["http://i.imgur.com/FVUjeh.jpg"]
		def actualImages = imageExtractor.extractImages("XXX" + expectedImages[0] + "XXX")
		assert expectedImages == actualImages
	}
	
	@Test
	public void canMatchImageAtMiddleOfInputWithSpaces() {
		def expectedImages = ["http://i.imgur.com/FVUjeh.jpg"]
		def actualImages = imageExtractor.extractImages(" XXX " + expectedImages[0] + " XXX ")
		assert expectedImages == actualImages
	}
	
	@Test
	public void canMatchImageWithUppercaseUrl() {
		def expectedImages = ["http://i.imgur.com/FVUjeh.jpg".toUpperCase()]
		def actualImages = imageExtractor.extractImages(expectedImages)
		assert expectedImages == actualImages
	}
	
	@Test
	public void canMatchImageWithLowercaseUrl() {
		def expectedImages = ["http://i.imgur.com/FVUjeh.jpg".toLowerCase()]
		def actualImages = imageExtractor.extractImages(expectedImages)
		assert expectedImages == actualImages
	}
	
	@Test
	public void canMatchMultipleSubmissionsWithImages() {
		def expectedImages = ["http://i.imgur.com/FVUjeh.jpg", "http://i.imgur.com/zglMA.gif", "http://i.imgur.com/UmLf8.png"]
		def notImages = ["https://www.google.com/", "http://www.skylark95.com/", "http://reddit.com/", "http://www.example.com/index.html", "http://www.example.com/index.php"]
		def submissions = []
		submissions.addAll(expectedImages)
		submissions.addAll(notImages)
		Collections.shuffle(submissions)
		def actualImages = imageExtractor.extractImages(submissions)
		for (def image : expectedImages) {
			assert actualImages.contains(image)
		}
		for (def notImage : notImages) {
			assert !actualImages.contains(notImage)
		}
	}
	
	@Test
	public void canMatchMultipleCommentsWithImages() {
		def expectedImages = ["http://i.imgur.com/FVUjeh.jpg", "http://i.imgur.com/zglMA.gif", "http://i.imgur.com/UmLf8.png"]
		def notImages = ["https://www.google.com/", "http://www.skylark95.com/", "http://reddit.com/", "http://www.example.com/index.html", "http://www.example.com/index.php"]
		def links = []
		def comments = []
		links.addAll(expectedImages)
		links.addAll(notImages)
		Collections.shuffle(links)
		for (def link : links) {
			comments.add("XXX" + link + "XXX")
		}
		def actualImages = imageExtractor.extractImages(comments)
		for (def image : expectedImages) {
			assert actualImages.contains(image)
		}
		for (def notImage : notImages) {
			assert !actualImages.contains(notImage)
		}
	}
	
	

}

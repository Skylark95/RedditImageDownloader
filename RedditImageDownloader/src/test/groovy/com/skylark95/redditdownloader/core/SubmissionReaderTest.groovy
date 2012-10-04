package com.skylark95.redditdownloader.core;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class SubmissionReaderTest {

	def reader = new SubmissionReader();
	
	@Test
	public void canReadSelfPosts() {
		reader.setReadUrlSubmission(false)
		def selfpost = reader.read("ImgurAlbumDownloader", 2).toString()
		assert selfpost.contains("This is a test Self Post")
		assert selfpost.contains("[Test Album Link](http://imgur.com/a/nT2T9#0)")
		assert selfpost.contains("[Test Image Link](http://i.imgur.com/FVUje.jpg)")
		assert selfpost.contains("[Test Page Link](http://imgur.com/FVUje)")
	}

	@Test
	public void readingSelfPostsDoesNotReturnUrlSubmissions() {
		reader.setReadUrlSubmission false
		def selfpost = reader.read("ImgurAlbumDownloader", 2)
		def selfpostStr = selfpost.toString()
		assert !selfpostStr.contains("http://www.skylark95.com/")
	}

	@Test
	public void canReadUrlSubmissions() {
		reader.setReadSelfPost(false)
		def urlSubmissions = reader.read("ImgurAlbumDownloader", 2)
		assert "http://www.skylark95.com/" in urlSubmissions
		assert "http://imgur.com/FVUje" in urlSubmissions
		assert "http://i.imgur.com/FVUje.jpg" in urlSubmissions
		assert "http://imgur.com/a/nT2T9#0" in urlSubmissions
	}

	@Test
	public void readingUrlSubmissionsDoesNotReturnSelfPosts() {
		reader.setReadSelfPost(false)
		def urlSubmissions = reader.read("ImgurAlbumDownloader", 2)
		def urlSubmissionsStr = urlSubmissions.toString()
		assert !urlSubmissionsStr.contains("This is a test Self Post")
		assert !urlSubmissionsStr.contains("[Test Album Link](http://imgur.com/a/nT2T9#0)")
		assert !urlSubmissionsStr.contains("[Test Image Link](http://i.imgur.com/FVUje.jpg)")
		assert !urlSubmissionsStr.contains("[Test Page Link](http://imgur.com/FVUje)")
	}

	@Test
	public void canReadBothURLSubmissionsAndSelfPostsByDefault() {
		def submissions = reader.read("ImgurAlbumDownloader", 2)
		def submissionsStr = submissions.toString()
		assert submissionsStr.contains("This is a test Self Post")
		assert submissionsStr.contains("[Test Album Link](http://imgur.com/a/nT2T9#0)")
		assert submissionsStr.contains("[Test Image Link](http://i.imgur.com/FVUje.jpg)")
		assert submissionsStr.contains("[Test Page Link](http://imgur.com/FVUje)")
		assert "http://www.skylark95.com/" in submissions
		assert "http://imgur.com/FVUje" in submissions
		assert "http://i.imgur.com/FVUje.jpg" in submissions
		assert "http://imgur.com/a/nT2T9#0" in submissions
	}

	@Ignore
	@Test
	public void lookupUserAndOutputSubmissionsToConsole() {
		def submissions = reader.read("oracle2b", 2)
		for (String comment : submissions) {
			println submissions
			println "---"
		}
		println "Size: " + submissions.size()
		assertTrue("Comments output to console", true)
	}
	
}

package com.skylark95.redditdownloader.core;

import static org.junit.Assert.*

import org.junit.*

public class CommentReaderTest {

	def reader = new CommentReader()

	@Test
	public void canReadCommentsForUser() {
		def comments = reader.read("ImgurAlbumDownloader", 2)
		assert "Test Comment" in comments
		assert "[Test Album Comment](http://imgur.com/a/nT2T9#0)" in comments
		assert "[Test Image Comment](http://i.imgur.com/FVUje.jpg)" in comments
		assert "[Test Page Comment](http://imgur.com/FVUje)" in comments
	}

	@Test
	public void canGrabUpToPageLimit() {
		def comments = reader.read("oracle2b", 5);
		assert comments.size() == 125
	}

	@Ignore
	@Test
	public void lookupUserAndOutputCommentsToConsole() {
		def comments = reader.read("oracle2b", 5)
		for (String comment : comments) {
			println comments
			println "---"
		}
		println "Size: " + comments.size()
		assertTrue("Comments output to console", true)
	}
}

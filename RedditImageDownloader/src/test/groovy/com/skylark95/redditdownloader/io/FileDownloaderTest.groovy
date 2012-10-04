package com.skylark95.redditdownloader.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FileDownloaderTest {

	def fileDownloader = new FileDownloader();

	@Test
	public void canDownloadImages() {
		def images = ["http://i.imgur.com/FVUjeh.jpg", "http://i.imgur.com/zglMA.gif", "http://i.imgur.com/UmLf8.png"]
		def path = "E:\\Users\\Derek\\Dropbox\\Java\\Eclipse\\DesktopApps\\download\\"
		def fileJpg = new File(path + "FVUjeh.jpg")
		def fileGif = new File(path + "zglMA.gif")
		def filePng = new File(path + "UmLf8.png")
		fileJpg.delete()
		fileGif.delete()
		filePng.delete()
		fileDownloader.download(images, path)		
		assert fileJpg.exists()
		assert fileGif.exists()
		assert filePng.exists()
	}

}

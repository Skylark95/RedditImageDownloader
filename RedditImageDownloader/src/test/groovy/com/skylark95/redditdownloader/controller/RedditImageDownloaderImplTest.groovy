

package com.skylark95.redditdownloader.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RedditImageDownloaderImplTest {

	def redditImageDownloader = new RedditImageDownloaderImpl();

	@Test
	public void test() {
		def inputMap = [
			username:"skylark95",
			keepUsernames:"true",
			maxUsernames:"5",
			saveDirectory:"E:\\Users\\Derek\\Dropbox\\Java\\Eclipse\\DesktopApps\\download\\",
			keepDirectories:"true",
			maxDirectories:"5",
			imageLimit:"250",
			searchComments:"true",
			commentsLimit:"10",
			searchSubmissions:"true",
			submissionsLimit:"10",
			searchSelfPosts:"true",
		];
		redditImageDownloader.download(inputMap);
	}
}

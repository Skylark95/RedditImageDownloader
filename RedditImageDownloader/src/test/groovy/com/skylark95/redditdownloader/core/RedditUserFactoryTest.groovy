package com.skylark95.redditdownloader.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.skylark95.redditdownloader.settings.SavedUsernames;
import com.skylark95.redditdownloader.settings.Settings;
import com.skylark95.redditdownloader.settings.UserConfig;
import com.skylark95.redditdownloader.settings.UserConfig.PageLimits;
import com.skylark95.redditdownloader.settings.UserConfig.SearchOptions;

public class RedditUserFactoryTest {
	
	def username = "imguralbumdownloader"
	def limits = 1
	def mockSettings = mock(Settings.class)
	def mockUserConfig = mock(UserConfig.class)
	def mockSavedUsernames = mock(SavedUsernames.class)
	def mockSearchOptions = mock(SearchOptions.class)
	def mockPageLimits = mock(PageLimits.class)

	@Before
	public void setUp() {
		when(mockSettings.getUserConfig()).thenReturn(mockUserConfig)
		when(mockSettings.getSavedUsernames()).thenReturn(mockSavedUsernames)
		when(mockUserConfig.getSearchOptions()).thenReturn(mockSearchOptions)
		when(mockUserConfig.getPageLimits()).thenReturn(mockPageLimits)
		when(mockPageLimits.getCommentsPageLimit()).thenReturn(limits)
		when(mockPageLimits.getSubmissionsPageLimit()).thenReturn(limits)
		when(mockSavedUsernames.getCurrentUsername()).thenReturn(username)
	}

	@Test
	public void canCreateRedditUserWithCommentsAndSubmissions() {
		when(mockSearchOptions.isSearchComments()).thenReturn(true)
		when(mockSearchOptions.isSearchUrlSubmissions()).thenReturn(true)
		when(mockSearchOptions.isSearchSelfPosts()).thenReturn(true)
		def redditUser = RedditUserFactory.build(mockSettings)
		assert redditUser.getUsername() == username
		assert redditUser.getComments().size() > 0;
		assert redditUser.getSubmissions().size() > 0;
	}
	
	@Test
	public void canCreateRedditUserWithSubmissionsAndSelfPostsOnly() {
		when(mockSearchOptions.isSearchComments()).thenReturn(false)
		when(mockSearchOptions.isSearchUrlSubmissions()).thenReturn(true)
		when(mockSearchOptions.isSearchSelfPosts()).thenReturn(true)
		def redditUser = RedditUserFactory.build(mockSettings)
		assert redditUser.getUsername() == username
		assert redditUser.getComments().size() == 0;
		assert redditUser.getSubmissions().size() > 0;
	}
	
	@Test
	public void canCreateRedditUserWithSubmissionsOnly() {
		when(mockSearchOptions.isSearchComments()).thenReturn(false)
		when(mockSearchOptions.isSearchUrlSubmissions()).thenReturn(true)
		when(mockSearchOptions.isSearchSelfPosts()).thenReturn(false)
		def redditUser = RedditUserFactory.build(mockSettings)
		assert redditUser.getUsername() == username
		assert redditUser.getComments().size() == 0;
		assert redditUser.getSubmissions().size() > 0;
	}
	
	@Test
	public void canCreateRedditUserWithSelfPostsOnly() {
		when(mockSearchOptions.isSearchComments()).thenReturn(false)
		when(mockSearchOptions.isSearchUrlSubmissions()).thenReturn(false)
		when(mockSearchOptions.isSearchSelfPosts()).thenReturn(true)
		def redditUser = RedditUserFactory.build(mockSettings)
		assert redditUser.getUsername() == username
		assert redditUser.getComments().size() == 0;
		assert redditUser.getSubmissions().size() > 0;
	}
	
	@Test
	public void canCreateRedditUserWithCommentsOnly() {
		when(mockSearchOptions.isSearchComments()).thenReturn(true)
		when(mockSearchOptions.isSearchUrlSubmissions()).thenReturn(false)
		when(mockSearchOptions.isSearchSelfPosts()).thenReturn(false)
		def redditUser = RedditUserFactory.build(mockSettings)
		assert redditUser.getUsername() == username
		assert redditUser.getComments().size() > 0;
		assert redditUser.getSubmissions().size() == 0;
	}


}

package com.skylark95.redditdownloader.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test
import org.junit.rules.ExpectedException

import com.skylark95.redditdownloader.io.RedditFileReader;
import com.skylark95.redditdownloader.io.RedditFileWriter;

public class SettingsFactoryTest {

	def inputMap
	def file = new File("settings.xml")

	@Before
	public void setUp() {
		inputMap = [
					username:"Skylark95",
					keepUsernames:"true",
					maxUsernames:"5",
					saveDirectory:"C:\\Users\\Derek",
					keepDirectories:"true",
					maxDirectories:"5",
					imageLimit:"250",
					searchComments:"true",
					commentsLimit:"5",
					searchSubmissions:"true",
					submissionsLimit:"5",
					searchSelfPosts:"true",
				];
			resetFile()
	}
	
	private void resetFile() {
		inputMap.keepUsernames = "false"
		inputMap.keepDirectories = "false"
		
		SettingsFactory.build(inputMap)
		
		inputMap.keepUsernames = "true"
		inputMap.keepDirectories = "true"
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void canCreateCurrentSettingsWithValidInputMap() {
		def settings = SettingsFactory.build(inputMap)
		assert inputMap.username == settings.getSavedUsernames().getCurrentUsername()
		assert inputMap.keepUsernames as boolean == settings.getSavedUsernames().isSaveUsernames()
		assert inputMap.maxUsernames as int == settings.getSavedUsernames().getUsernameCount()
		assert inputMap.saveDirectory == settings.getSavedDirectories().getCurrentDirectory()
		assert inputMap.keepDirectories as boolean == settings.getSavedDirectories().isSaveDirectories()
		assert inputMap.maxDirectories as int == settings.getSavedDirectories().getDirectoryCount()
		assert inputMap.imageLimit as int == settings.getUserConfig().getDownloadOptions().getImageLimit()
		assert inputMap.searchComments as boolean == settings.getUserConfig().getSearchOptions().isSearchComments()
		assert inputMap.commentsLimit as int == settings.getUserConfig().getPageLimits().getCommentsPageLimit()
		assert inputMap.searchSubmissions as boolean == settings.getUserConfig().getSearchOptions().isSearchUrlSubmissions()
		assert inputMap.submissionsLimit as int == settings.getUserConfig().getPageLimits().getSubmissionsPageLimit()
		assert inputMap.searchSelfPosts as boolean == settings.getUserConfig().getSearchOptions().isSearchSelfPosts()
	}

	@Test
	public void nullImageLimitThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Image Limit must be numeric")
		inputMap.imageLimit = null
		SettingsFactory.build(inputMap)
	}

	@Test
	public void emptyImageLimitThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Image Limit must be numeric")
		inputMap.imageLimit = ""
		SettingsFactory.build(inputMap)
	}

	@Test
	public void notNumbericImageLimitThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Image Limit must be numeric")
		inputMap.imageLimit = "a"
		SettingsFactory.build(inputMap)
	}

	@Test
	public void nullMaxUsernamesThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Max Usernames must be numeric")
		inputMap.maxUsernames = null
		SettingsFactory.build(inputMap)
	}

	@Test
	public void emptyMaxUsernamesThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Max Usernames must be numeric")
		inputMap.maxUsernames = ""
		SettingsFactory.build(inputMap)
	}

	@Test
	public void notNumbericMaxUsernamesThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Max Usernames must be numeric")
		inputMap.maxUsernames = "a"
		SettingsFactory.build(inputMap)
	}

	@Test
	public void nullMaxDirectoriesThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Max Directories must be numeric")
		inputMap.maxDirectories = null
		SettingsFactory.build(inputMap)
	}

	@Test
	public void emptyMaxDirectoriesThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Max Directories must be numeric")
		inputMap.maxDirectories = ""
		SettingsFactory.build(inputMap)
	}

	@Test
	public void notNumbericMaxDirectoriesThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Max Directories must be numeric")
		inputMap.maxDirectories = "a"
		SettingsFactory.build(inputMap)
	}

	@Test
	public void nullCommentsLimitThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Comments Limit must be numeric")
		inputMap.commentsLimit = null
		SettingsFactory.build(inputMap)
	}

	@Test
	public void emptyCommentsLimitThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Comments Limit must be numeric")
		inputMap.commentsLimit = ""
		SettingsFactory.build(inputMap)
	}

	@Test
	public void notNumbericCommentsLimitThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Comments Limit must be numeric")
		inputMap.commentsLimit = "a"
		SettingsFactory.build(inputMap)
	}

	@Test
	public void nullSubmissionsLimitThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Submissions Limit must be numeric")
		inputMap.submissionsLimit = null
		SettingsFactory.build(inputMap)
	}

	@Test
	public void emptySubmissionsLimitThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Submissions Limit must be numeric")
		inputMap.submissionsLimit = ""
		SettingsFactory.build(inputMap)
	}

	@Test
	public void notNumbericSubmissionsLimitThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Submissions Limit must be numeric")
		inputMap.submissionsLimit = "a"
		SettingsFactory.build(inputMap)
	}

	@Test
	public void emptyUsernameThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Username cannot be blank")
		inputMap.username = ""
		SettingsFactory.build(inputMap)
	}

	@Test
	public void nullUsernameThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Username cannot be blank")
		inputMap.username = null
		SettingsFactory.build(inputMap)
	}

	@Test
	public void emptySaveDirectoryThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Save Directory cannot be blank")
		inputMap.saveDirectory = ""
		SettingsFactory.build(inputMap)
	}

	@Test
	public void nullSaveDirectoryThrowsExpection() {
		thrown.expect(SettingsExecption.class)
		thrown.expectMessage("Save Directory cannot be blank")
		inputMap.saveDirectory = null
		SettingsFactory.build(inputMap)
	}

	@Test
	public void canWriteSettings() {
		def settings = SettingsFactory.build(inputMap);
		def file = new File("settings.xml")
		assert file.exists();
	}
	
	@Test
	public void canLoadSettings() {
		def usernames = ["Skylark95", "SavedUsername"]
		def saveLocations = ["C:\\Users\\Derek", "C:\\Users\\Public"]
		inputMap.username = usernames[1]
		inputMap.saveDirectory = saveLocations[1]
		SettingsFactory.build(inputMap);
		inputMap.username = usernames[0]
		inputMap.saveDirectory = saveLocations[0]
		def settings = SettingsFactory.build(inputMap);
		assert settings.getSavedUsernames().getUsernames()[1].getUsername() == usernames[0] 
		assert settings.getSavedUsernames().getUsernames()[0].getUsername() == usernames[1]
		assert settings.getSavedDirectories().getDirectories()[1].getDirectoryPath() == saveLocations[0]
		assert settings.getSavedDirectories().getDirectories()[0].getDirectoryPath() == saveLocations[1]
	}
	
	@Test
	public void canRemoveExtraUsernames() {
		inputMap.maxUsernames = "2"
		def settings = SettingsFactory.build(inputMap)
		assert settings.getSavedUsernames().getUsernames().size() == 1
		settings = SettingsFactory.build(inputMap)
		assert settings.getSavedUsernames().getUsernames().size() == 2
		settings = SettingsFactory.build(inputMap)
		assert settings.getSavedUsernames().getUsernames().size() == 2
	}
	
	@Test
	public void canRemoveExtraSaveDirectories() {
		inputMap.maxDirectories = "2"
		def settings = SettingsFactory.build(inputMap)
		assert settings.getSavedDirectories().getDirectories().size() == 1
		settings = SettingsFactory.build(inputMap)
		assert settings.getSavedDirectories().getDirectories().size() == 2
		settings = SettingsFactory.build(inputMap)
		assert settings.getSavedDirectories().getDirectories().size() == 2
	}
}

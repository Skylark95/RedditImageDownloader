package com.skylark95.redditdownloader.ui;

import com.skylark95.redditdownloader.ui.windows.RedditMainWindow;

public class Launcher {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RedditMainWindow window = new RedditMainWindow();
			window.open();
		} catch (Exception e) {
			//TODO remove when completing app.
			e.printStackTrace();
		}
	}

}

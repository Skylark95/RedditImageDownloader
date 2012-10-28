package com.skylark95.redditdownloader.ui.events;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Dialog;

import com.skylark95.redditdownloader.ui.windows.RedditWindow;

public class OpenWindowListener implements SelectionListener {

	private final RedditWindow redditWindow;

	public OpenWindowListener(RedditWindow window) {
		redditWindow = window;
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		widgetDefaultSelected(event);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		redditWindow.open();
	}

}

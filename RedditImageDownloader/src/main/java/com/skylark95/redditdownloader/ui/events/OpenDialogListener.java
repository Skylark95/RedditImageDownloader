package com.skylark95.redditdownloader.ui.events;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.skylark95.redditdownloader.ui.windows.RedditDialog;

public class OpenDialogListener implements SelectionListener {

	private final RedditDialog redditDialog;

	public OpenDialogListener(RedditDialog dialog) {
		redditDialog = dialog;
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		widgetDefaultSelected(event);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		redditDialog.open();
	}

}

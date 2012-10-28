package com.skylark95.redditdownloader.ui.events;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.program.Program;

public class LaunchUrlListener implements SelectionListener {

	private final String url;
	
	public LaunchUrlListener(String url) {
		this.url = url;
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		Program.launch(url);
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		widgetDefaultSelected(event);
	}

}

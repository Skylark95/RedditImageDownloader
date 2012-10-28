package com.skylark95.redditdownloader.ui.events;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class ExitListener implements SelectionListener {

	@Override
	public void widgetSelected(SelectionEvent event) {
		widgetDefaultSelected(event);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		System.exit(0);		
	}

}

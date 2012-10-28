package com.skylark95.redditdownloader.ui.events;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;

public class CloseDialogListener implements SelectionListener {

	private final Shell shell;
	
	public CloseDialogListener(Shell shell) {
		this.shell = shell;
	}
	
	@Override
	public void widgetSelected(SelectionEvent event) {
		widgetDefaultSelected(event);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		shell.dispose();
	}

}

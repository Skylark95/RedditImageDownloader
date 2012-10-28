package com.skylark95.redditdownloader.ui.windows;

import org.eclipse.swt.SWT;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.CLabel;

import com.skylark95.redditdownloader.ui.bindings.Buttons;
import com.skylark95.redditdownloader.ui.bindings.Fonts;
import com.skylark95.redditdownloader.ui.bindings.Groups;
import com.skylark95.redditdownloader.ui.bindings.Labels;
import com.skylark95.redditdownloader.ui.bindings.Menus;
import com.skylark95.redditdownloader.ui.events.ExitListener;
import com.skylark95.redditdownloader.ui.events.LaunchUrlListener;
import com.skylark95.redditdownloader.ui.events.OpenDialogListener;

import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RedditMainWindow implements RedditWindow {

	protected Shell shlRedditImageDownloader;
	private Text text;
	private Text text_1;

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlRedditImageDownloader.open();
		shlRedditImageDownloader.layout();
		while (!shlRedditImageDownloader.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		createWindow();
		createMenus();
		createBody();		
	}

	private void createBody() {
		createSettingsSection();
		createLogSection();
	}

	private void createMenus() {
		Menu menu = new Menu(shlRedditImageDownloader, SWT.BAR);
		shlRedditImageDownloader.setMenuBar(menu);

		createFileMenu(menu);
		createHelpMenu(menu);
	}

	private void createHelpMenu(Menu menu) {
		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText(Menus.HELP_MENU);

		Menu menu_2 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_2);

		MenuItem mntmAbout = new MenuItem(menu_2, SWT.NONE);
		mntmAbout.setText(Menus.ABOUT_ITEM);

		MenuItem mntmDonate = new MenuItem(menu_2, SWT.NONE);
		mntmDonate.setText(Menus.DONATE_ITEM);

		MenuItem mntmWebsite = new MenuItem(menu_2, SWT.NONE);
		mntmWebsite.addSelectionListener(new LaunchUrlListener(Menus.WEBSITE_URL));
		mntmWebsite.setText(Menus.WEBSITE_ITEM);

		new MenuItem(menu_2, SWT.SEPARATOR);

		MenuItem mntmSubmitAnIssue = new MenuItem(menu_2, SWT.NONE);
		mntmSubmitAnIssue.addSelectionListener(new LaunchUrlListener(Menus.SUBMIT_ISSUE_URL));
		mntmSubmitAnIssue.setText(Menus.SUBMIT_ISSUE_ITEM);

		MenuItem mntmCheckForUpdates = new MenuItem(menu_2, SWT.NONE);
		mntmCheckForUpdates.addSelectionListener(new OpenDialogListener(new UpdatesDialog(shlRedditImageDownloader, SWT.DIALOG_TRIM)));
		mntmCheckForUpdates.setText(Menus.UPDATE_CHECK_ITEM);
	}

	private void createFileMenu(Menu menu) {
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText(Menus.FILE_MENU);

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		MenuItem mntmSettings = new MenuItem(menu_1, SWT.NONE);
		mntmSettings.setText(Menus.SETTINGS_ITEM);

		MenuItem mntmQuit = new MenuItem(menu_1, SWT.NONE);
		mntmQuit.addSelectionListener(new ExitListener());
		mntmQuit.setText(Menus.EXIT_ITEM);
	}

	private void createSettingsSection() {

		Group grpDownlad = new Group(shlRedditImageDownloader, SWT.NONE);
		grpDownlad.setText(Groups.SETTINGS_GROUP_TXT);
		grpDownlad.setBounds(10, 10, 564, 115);
		Label lblRedditUsername = new Label(grpDownlad, SWT.NONE);
		lblRedditUsername.setToolTipText(Labels.USERNAME_TOOL_TIP);
		lblRedditUsername.setBounds(10, 20, 123, 21);
		lblRedditUsername.setFont(SWTResourceManager.getFont(Fonts.FONT_SEGOE_UI, 12, SWT.NORMAL));
		lblRedditUsername.setText(Labels.USERNAME_TXT);

		text = new Text(grpDownlad, SWT.BORDER);
		text.setToolTipText(Labels.USERNAME_TOOL_TIP);
		text.setBounds(139, 22, 326, 21);
		

		Label lblSaveTo = new Label(grpDownlad, SWT.NONE);
		lblSaveTo.setToolTipText(Labels.SAVE_TOOL_TIP);
		lblSaveTo.setBounds(10, 55, 57, 21);
		lblSaveTo.setFont(SWTResourceManager.getFont(Fonts.FONT_SEGOE_UI, 12, SWT.NORMAL));
		lblSaveTo.setText(Labels.SAVE_TXT);
		
		Combo combo = new Combo(grpDownlad, SWT.NONE);
		combo.setToolTipText(Labels.SAVE_TOOL_TIP);
		combo.setBounds(73, 57, 392, 23);

		Button btnBrowse = new Button(grpDownlad, SWT.NONE);
		btnBrowse.setBounds(471, 55, 76, 25);
		btnBrowse.setText(Buttons.BROWSE_TXT);
		Button btnDownload = new Button(grpDownlad, SWT.NONE);
		btnDownload.setBounds(472, 20, 75, 25);
		btnDownload.setText(Buttons.DOWNLOAD_TXT);
		
		CLabel lblNewLabel = new CLabel(grpDownlad, SWT.BORDER);
		lblNewLabel.setFont(SWTResourceManager.getFont(Fonts.FONT_SEGOE_UI, 9, SWT.BOLD));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 86, 537, 21);
		lblNewLabel.setText(Labels.HOW_TO_SETTINGS_TXT);
	}

	private void createLogSection() {
		Group grpLog = new Group(shlRedditImageDownloader, SWT.NONE);
		grpLog.setText(Groups.LOG_GROUP_TXT);
		grpLog.setBounds(10, 142, 564, 390);

		text_1 = new Text(grpLog, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		text_1.setBounds(10, 21, 544, 359);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	}
	
	private void createWindow() {
		shlRedditImageDownloader = new Shell();
		shlRedditImageDownloader.setSize(600, 600);
		shlRedditImageDownloader.setText("Reddit Image Downloader");
	}
}

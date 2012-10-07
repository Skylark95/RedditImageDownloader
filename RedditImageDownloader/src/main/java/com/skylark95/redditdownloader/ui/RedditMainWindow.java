package com.skylark95.redditdownloader.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class RedditMainWindow {

	protected Shell shlRedditImageDownloader;
	private Text text;

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
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
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
		createStatusFooter();
	}

	private void createBody() {
		createRedditUsername();
		createSaveTo();
		createButtons();
	}

	private void createMenus() {
		Menu menu = new Menu(shlRedditImageDownloader, SWT.BAR);
		shlRedditImageDownloader.setMenuBar(menu);

		createFileMenu(menu);
		createHelpMenu(menu);
	}

	private void createHelpMenu(Menu menu) {
		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");

		Menu menu_2 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_2);

		MenuItem mntmAbout = new MenuItem(menu_2, SWT.NONE);
		mntmAbout.setText("About");

		MenuItem mntmDonate = new MenuItem(menu_2, SWT.NONE);
		mntmDonate.setText("Donate");

		MenuItem mntmWebsite = new MenuItem(menu_2, SWT.NONE);
		mntmWebsite.setText("Website");

		new MenuItem(menu_2, SWT.SEPARATOR);

		MenuItem mntmSubmitAnIssue = new MenuItem(menu_2, SWT.NONE);
		mntmSubmitAnIssue.setText("Submit an Issue");

		MenuItem mntmCheckForUpdates = new MenuItem(menu_2, SWT.NONE);
		mntmCheckForUpdates.setText("Check for Updates");
	}

	private void createFileMenu(Menu menu) {
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		MenuItem mntmSettings = new MenuItem(menu_1, SWT.NONE);
		mntmSettings.setText("Settings");

		MenuItem mntmQuit = new MenuItem(menu_1, SWT.NONE);
		mntmQuit.setText("Exit");
	}

	private void createStatusFooter() {
		Label lblNewLabel = new Label(shlRedditImageDownloader, SWT.BORDER | SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNewLabel.setBounds(10, 162, 564, 21);
		lblNewLabel.setText("Welcome to Reddit Image Downloader");
	}

	private void createButtons() {
		Button btnDownload = new Button(shlRedditImageDownloader, SWT.NONE);
		btnDownload.setBounds(183, 120, 75, 25);
		btnDownload.setText("Download");

		Button btnMoreSettings = new Button(shlRedditImageDownloader, SWT.NONE);
		btnMoreSettings.setBounds(297, 120, 96, 25);
		btnMoreSettings.setText("More Settings");
	}

	private void createSaveTo() {
		Combo combo = new Combo(shlRedditImageDownloader, SWT.NONE);
		combo.setBounds(73, 72, 420, 23);

		Label lblSaveTo = new Label(shlRedditImageDownloader, SWT.NONE);
		lblSaveTo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblSaveTo.setBounds(10, 70, 57, 21);
		lblSaveTo.setText("Save To:");

		Button btnBrowse = new Button(shlRedditImageDownloader, SWT.NONE);
		btnBrowse.setBounds(499, 70, 75, 25);
		btnBrowse.setText("Browse");
	}

	private void createRedditUsername() {
		Label lblRedditUsername = new Label(shlRedditImageDownloader, SWT.NONE);
		lblRedditUsername.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblRedditUsername.setBounds(10, 31, 123, 21);
		lblRedditUsername.setText("Reddit Username:");

		text = new Text(shlRedditImageDownloader, SWT.BORDER);
		text.setBounds(139, 33, 354, 21);
	}

	private void createWindow() {
		shlRedditImageDownloader = new Shell();
		shlRedditImageDownloader.setSize(600, 250);
		shlRedditImageDownloader.setText("Reddit Image Downloader");
	}
}

package com.skylark95.redditdownloader.io;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.skylark95.redditdownloader.util.HttpHelper;

public class FileDownloader {

	private static Logger LOG = Logger.getLogger(FileDownloader.class);

	public void download(List<String> files, String location) {
		for (int i = 0; i < files.size(); i++) {
			LOG.info("Downloading file " + (i + 1) + " of " + files.size() + ": " + files.get(i));
			download(files.get(i), location);
		}
	}

	public void download(String file, String location) {
		HttpHelper httpHelper = new HttpHelper();
		try {
			InputStream in = httpHelper.createConnection(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1 != (n = in.read(buf))) {
				out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();
			String filename = getFilename(file);
			saveFile(location, filename, response);
		} catch (PropertyException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}
	
	private String getFilename(String file) {
		String[] vals = file.split("/");
		return vals[vals.length - 1];
	}

	private void saveFile(String location, String filename, byte[] data) throws IOException {
		FileOutputStream fos = new FileOutputStream(location + filename);
		fos.write(data);
		fos.close();
	}

}

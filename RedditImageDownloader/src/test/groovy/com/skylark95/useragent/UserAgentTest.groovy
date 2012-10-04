package com.skylark95.useragent;

import static org.junit.Assert.*

import org.junit.*

import com.skylark95.redditdownloader.io.RedditFileReader

public class UserAgentTest {


	@Test
	@Ignore
	public void userAgentToHTML() {
		def fileReader = new RedditFileReader();
		def userAgent = fileReader.readProperty("Global.userAgent")

		def file = new FileOutputStream("userAgent.html")
		def out = new BufferedOutputStream(file)

		def url = new URL("http://whatsmyuseragent.com/")
		def conn = url.openConnection()
		conn.setRequestProperty("User-Agent", userAgent)
		out << conn.getInputStream()
		out.close()
		assertTrue("userAgent.html output", true)
	}

	//	@Test
	//	public void userAgentToHTML() throws IOException {
	//		URL oracle = new URL("http://whatsmyuseragent.com/");
	//		URLConnection yc = oracle.openConnection();
	//		yc.setRequestProperty("User-Agent", "testing 2");
	//		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	//		StringBuilder html = new StringBuilder();
	//		String inputLine;
	//		while ((inputLine = in.readLine()) != null) {
	//			html.append(inputLine);
	//		}
	//		in.close();
	//		FileWriter fw = new FileWriter("useragent.html");
	//		BufferedWriter out = new BufferedWriter(fw);
	//		out.write(html.toString());
	//		out.close();
	//	}

}

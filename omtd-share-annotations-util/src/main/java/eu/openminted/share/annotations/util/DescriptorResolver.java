package eu.openminted.share.annotations.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * Methods to retrieve OpenMinTeD-SHARE descriptors.
 */
public class DescriptorResolver {
	/**
	 * scan the classpath for all OpenMinTeD Share descriptors
	 * 
	 * @return the URLs of all the OMTD-SHARE descriptors located via the
	 *         classpath
	 * @throws IOException
	 *             if any problems arise while locating or resolving the
	 *             descriptors
	 */
	public static URL[] scanDescriptors() throws IOException {
		List<URL> patterns = new ArrayList<URL>();

		Enumeration<URL> enumeration = DescriptorResolver.class.getClassLoader()
				.getResources("META-INF/eu.openminted.share/descriptors.txt");

		while (enumeration.hasMoreElements()) {
			URL mfUrl = enumeration.nextElement();
			try (InputStream is = mfUrl.openStream()) {
				for (String line : IOUtils.readLines(is)) {
					patterns.add(new URL(mfUrl, line));
				}
			}
		}

		return patterns.toArray(new URL[patterns.size()]);
	}

	/**
	 * Processes a single JAR (or ZIP) file to locate the OMTD-SHARE descriptors
	 * 
	 * @param jarFile
	 *            the URL of the JAR file to process
	 * @return an array of URLs pointing to the OMTD-SHARE descriptor files
	 * @throws IOException
	 *             if an error occurs accessing the JAR in any way
	 */
	public static URL[] scanDescriptors(URL jarFile) throws IOException {
		List<URL> descriptors = new ArrayList<URL>();

		URL descriptorsURL = new URL("jar:" + jarFile + "!/META-INF/eu.openminted.share/descriptors.txt");

		try (InputStream in = descriptorsURL.openStream()) {
			for (String line : IOUtils.readLines(in)) {
				descriptors.add(new URL(descriptorsURL, line));
			}
		}

		return descriptors.toArray(new URL[descriptors.size()]);
	}
}

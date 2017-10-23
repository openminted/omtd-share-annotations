package eu.openminted.share.annotations.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.maven.model.Profile;
import org.apache.maven.model.Repository;
import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.apache.maven.settings.Settings;
import org.apache.maven.settings.building.DefaultSettingsBuilder;
import org.apache.maven.settings.building.DefaultSettingsBuilderFactory;
import org.apache.maven.settings.building.DefaultSettingsBuildingRequest;
import org.apache.maven.settings.building.SettingsBuildingException;
import org.apache.maven.settings.building.SettingsBuildingRequest;
import org.apache.maven.settings.building.SettingsBuildingResult;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory;
import org.eclipse.aether.impl.DefaultServiceLocator;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactRequest;
import org.eclipse.aether.resolution.ArtifactResolutionException;
import org.eclipse.aether.resolution.ArtifactResult;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.aether.spi.connector.transport.TransporterFactory;
import org.eclipse.aether.transport.file.FileTransporterFactory;
import org.eclipse.aether.transport.http.HttpTransporterFactory;

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

	public static URL[] scanDescriptors(String groupID, String artifactID, String version) throws IOException {
		Artifact artifactObj = new DefaultArtifact(groupID, artifactID, "jar", version);

		try {
			List<RemoteRepository> repos = getRepositoryList();
			RepositorySystem repoSystem = getRepositorySystem();
			RepositorySystemSession repoSession = getRepositorySession(repoSystem);

			ArtifactRequest artifactRequest = new ArtifactRequest(artifactObj, repos, null);

			ArtifactResult artifactResult = repoSystem.resolveArtifact(repoSession, artifactRequest);

			return scanDescriptors(artifactResult.getArtifact().getFile().toURI().toURL());
		} catch (ArtifactResolutionException | SettingsBuildingException e) {
			throw new IOException("unable to retrieve component from maven", e);
		}

	}

	public static final String userHome = System.getProperty("user.home");

	public static final File userMavenConfigurationHome = new File(userHome, ".m2");

	public static final String envM2Home = System.getenv("M2_HOME");

	public static final File DEFAULT_USER_SETTINGS_FILE = new File(userMavenConfigurationHome, "settings.xml");

	public static final String settingsXml = System.getProperty("M2_SETTINGS_XML",
			DEFAULT_USER_SETTINGS_FILE.getPath());

	public static final File DEFAULT_GLOBAL_SETTINGS_FILE = new File(
			System.getProperty("maven.home", envM2Home != null ? envM2Home : ""), "conf/settings.xml");

	public static Settings loadMavenSettings() throws SettingsBuildingException {
		// http://stackoverflow.com/questions/27818659/loading-mavens-settings-xml-for-jcabi-aether-to-use
		SettingsBuildingRequest settingsBuildingRequest = new DefaultSettingsBuildingRequest();
		settingsBuildingRequest.setSystemProperties(System.getProperties());
		settingsBuildingRequest.setUserSettingsFile(new File(settingsXml));
		settingsBuildingRequest.setGlobalSettingsFile(DEFAULT_GLOBAL_SETTINGS_FILE);

		SettingsBuildingResult settingsBuildingResult;
		DefaultSettingsBuilderFactory mvnSettingBuilderFactory = new DefaultSettingsBuilderFactory();
		DefaultSettingsBuilder settingsBuilder = mvnSettingBuilderFactory.newInstance();
		settingsBuildingResult = settingsBuilder.build(settingsBuildingRequest);

		Settings effectiveSettings = settingsBuildingResult.getEffectiveSettings();
		return effectiveSettings;
	}

	public static List<RemoteRepository> getRepositoryList() throws SettingsBuildingException {

		List<RemoteRepository> repos = new ArrayList<RemoteRepository>();

		RemoteRepository central = new RemoteRepository.Builder("central", "default", "http://repo1.maven.org/maven2/")
				.build();

		// Without this we wouldn't be able to find SNAPSHOT builds of plugins
		// we
		// haven't built and installed locally ourselves
		RemoteRepository gateRepo = new RemoteRepository.Builder("gate", "default",
				"http://repo.gate.ac.uk/content/groups/public/").build();

		repos.add(central);
		repos.add(gateRepo);

		// Add all repos from settings.xml
		// http://stackoverflow.com/questions/27818659/loading-mavens-settings-xml-for-jcabi-aether-to-use
		Settings effectiveSettings = loadMavenSettings();
		Map<String, Profile> profilesMap = effectiveSettings.getProfilesAsMap();
		for (String profileName : effectiveSettings.getActiveProfiles()) {
			Profile profile = profilesMap.get(profileName);
			List<Repository> repositories = profile.getRepositories();
			for (Repository repo : repositories) {
				RemoteRepository remoteRepo = new RemoteRepository.Builder(repo.getId(), "default", repo.getUrl())
						.build();
				repos.add(remoteRepo);
			}
		}

		return repos;
	}

	private static RepositorySystem getRepositorySystem() {

		DefaultServiceLocator locator = MavenRepositorySystemUtils.newServiceLocator();
		locator.addService(RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class);
		locator.addService(TransporterFactory.class, FileTransporterFactory.class);
		locator.addService(TransporterFactory.class, HttpTransporterFactory.class);

		return locator.getService(RepositorySystem.class);
	}

	private static RepositorySystemSession getRepositorySession(RepositorySystem repoSystem) {

		DefaultRepositorySystemSession repoSystemSession = MavenRepositorySystemUtils.newSession();

		String repoLocation = System.getProperty("user.home") + File.separator + ".m2" + File.separator + "repository/";
		try {
			Settings effectiveSettings = loadMavenSettings();
			if (effectiveSettings.getLocalRepository() != null) {
				repoLocation = effectiveSettings.getLocalRepository();
			}
		} catch (Exception e) {
			// log.warn("Unable to load Maven settings, using default repository
			// location", e);
		}

		LocalRepository localRepo = new LocalRepository(repoLocation);
		// log.debug("Using local repository at: " + repoLocation);
		repoSystemSession.setLocalRepositoryManager(repoSystem.newLocalRepositoryManager(repoSystemSession, localRepo));

		return repoSystemSession;
	}

}

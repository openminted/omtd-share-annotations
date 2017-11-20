package eu.openminted.share.annotations.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.io.IOUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.apache.maven.settings.Profile;
import org.apache.maven.settings.Repository;
import org.apache.maven.settings.Settings;
import org.apache.maven.settings.building.DefaultSettingsBuilder;
import org.apache.maven.settings.building.DefaultSettingsBuilderFactory;
import org.apache.maven.settings.building.DefaultSettingsBuildingRequest;
import org.apache.maven.settings.building.SettingsBuildingException;
import org.apache.maven.settings.building.SettingsBuildingRequest;
import org.apache.maven.settings.building.SettingsBuildingResult;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
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

import eu.openminted.registry.domain.ComponentDistributionFormEnum;
import eu.openminted.registry.domain.ComponentDistributionInfo;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ComponentLoc;
import eu.openminted.registry.domain.IdentificationInfo;
import eu.openminted.registry.domain.ResourceIdentifier;
import eu.openminted.registry.domain.ResourceIdentifierSchemeNameEnum;
import eu.openminted.share.annotations.util.analyzer.AnalyzerException;
import eu.openminted.share.annotations.util.analyzer.MavenProjectAnalyzer;
import eu.openminted.share.annotations.util.scanner.DescriptorSet;
import eu.openminted.share.annotations.util.scanner.GateComponentScanner;
import eu.openminted.share.annotations.util.scanner.UimaComponentScanner;

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
	
	public static String[] generateDescriptors(String groupID, String artifactID, String version) throws IOException {
		List<String> descriptors = new ArrayList<String>();

		File artifactJar = null;

		try {
			Artifact artifactObj = new DefaultArtifact(groupID, artifactID, "jar", version);
			List<RemoteRepository> repos = getRepositoryList();
			RepositorySystem repoSystem = getRepositorySystem();
			RepositorySystemSession repoSession = getRepositorySession(repoSystem);

			ArtifactRequest artifactRequest = new ArtifactRequest(artifactObj, repos, null);

			ArtifactResult artifactResult = repoSystem.resolveArtifact(repoSession, artifactRequest);

			artifactJar = artifactResult.getArtifact().getFile();
		} catch (ArtifactResolutionException | SettingsBuildingException e) {
			throw new IOException("unable to retrieve component from maven", e);
		}
		
		// Use scanners to find native component descriptors and convert them to OMTD-SHARE
        // Scan for UIMA
		List<DescriptorSet> descriptorSets = new ArrayList<DescriptorSet>();
		 try (FileSystem zipFs =
		          FileSystems.newFileSystem(new URI("jar:file:"+artifactJar.toString()), new HashMap<>());) {
            UimaComponentScanner uimaComponentScanner = new UimaComponentScanner();
            Finder finder = new Finder("*.xml");
            Files.walkFileTree(zipFs.getPath("/"), finder);
            List<Path> matched = finder.getMatchedPaths();
            String[] xmlFiles  = new String[matched.size()];
            for (int i = 0 ; i < xmlFiles.length ; ++i) {
            	xmlFiles[i] = matched.get(i).toUri().toURL().toExternalForm();
            }
            
            uimaComponentScanner.scan(xmlFiles);
            descriptorSets.addAll(uimaComponentScanner.getComponents());
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

        // Scan for GATE
        try {
            GateComponentScanner gateComponentScanner = new GateComponentScanner();
            URL creoleXmlFile = 
                    new URL("jar:"
                            + artifactJar.toURI().toURL()
                            + "!/META-INF/gate/creole.xml");
            try {
                creoleXmlFile.openStream();
                gateComponentScanner.scan(creoleXmlFile.toString());
                descriptorSets.addAll(gateComponentScanner.getComponents());
              } catch(IOException ioe) {
            	  ioe.printStackTrace();
              }                
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        if (descriptorSets.isEmpty()) return descriptors.toArray(new String[descriptors.size()]);
        
        MavenProjectAnalyzer pomAnalyzer = new MavenProjectAnalyzer();
        MavenProject mavenProject = null;
		try {
			Artifact artifactObj = new DefaultArtifact(groupID, artifactID, "pom", version);
			
			List<RemoteRepository> repos = getRepositoryList();
			RepositorySystem repoSystem = getRepositorySystem();
			RepositorySystemSession repoSession = getRepositorySession(repoSystem);

			ArtifactRequest artifactRequest = new ArtifactRequest(artifactObj, repos, null);

			ArtifactResult artifactResult = repoSystem.resolveArtifact(repoSession, artifactRequest);

			Model model = null;
			FileReader reader = null;
			MavenXpp3Reader mavenreader = new MavenXpp3Reader();

			reader = new FileReader(artifactResult.getArtifact().getFile());
			model = mavenreader.read(reader);
			model.setPomFile(artifactResult.getArtifact().getFile());

			mavenProject = new MavenProject(model);
		} catch (ArtifactResolutionException | SettingsBuildingException | XmlPullParserException e) {
			throw new IOException("unable to retrieve pom from Maven", e);
		}

        for (DescriptorSet ds : descriptorSets) {
        	
        	try {
				pomAnalyzer.analyze(ds.getOmtdShareDescriptor(), mavenProject);
			} catch (AnalyzerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
                    	
        	eu.openminted.registry.domain.Component omtdShareDescriptor = ds.getOmtdShareDescriptor();
            ComponentInfo componentInfo = omtdShareDescriptor.getComponentInfo();
            if (componentInfo != null) {
                List<ComponentDistributionInfo> distInfos = componentInfo.getDistributionInfos();
                if (distInfos != null) {
                    // If there already is a distribution info, then update it instead of
                    // creating a new one.
                    if (!componentInfo.getDistributionInfos().isEmpty()) {
                        ComponentDistributionInfo distributionInfo = componentInfo
                                .getDistributionInfos().get(0);
                        
                        ComponentLoc componentLoc = new ComponentLoc();
                        
                        // Set the componentDistributionForm
                        componentLoc.setComponentDistributionForm(ComponentDistributionFormEnum.EXECUTABLE_CODE);
                        
                        // If there is a MAVEN resource identifier, then we use its URI as the
                        // distribution URL
                        IdentificationInfo identificationInfo = componentInfo.getIdentificationInfo();
                        if (identificationInfo != null) {
                            for (ResourceIdentifier resourceIdentifier : identificationInfo
                                    .getResourceIdentifiers()) {
                                if (ResourceIdentifierSchemeNameEnum.MAVEN.equals(
                                        resourceIdentifier.getResourceIdentifierSchemeName())) {
                                    componentLoc.setDistributionURL(resourceIdentifier.getValue());
                                    break;
                                }
                            }
                        }
                        
                        distributionInfo.setComponentLoc(componentLoc);
                    }
                }
            }
        	
        	try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
        		XmlUtil.write(ds.getOmtdShareDescriptor(), out);
        		out.flush();
        		String xml = out.toString("UTF-8");
        		System.out.println(xml);
        		descriptors.add(xml);
        	} catch (JAXBException | XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		/*
		*/

		return descriptors.toArray(new String[descriptors.size()]);
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
		RemoteRepository omtdReleaseRepo = new RemoteRepository.Builder("omtd-releases", "default",
				"https://repo.openminted.eu/content/repositories/releases/").build();
		
		RemoteRepository omtdSnapshotRepo = new RemoteRepository.Builder("omtd-snapshots", "default",
				"https://repo.openminted.eu/content/repositories/snapshots/").build();

		repos.add(central);
		repos.add(omtdReleaseRepo);
		repos.add(omtdSnapshotRepo);

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
	
	public static class Finder
    extends SimpleFileVisitor<Path> {

    private final PathMatcher matcher;
    private List<Path> matched = new ArrayList<Path>();;

    Finder(String pattern) {
        matcher = FileSystems.getDefault()
                .getPathMatcher("glob:" + pattern);
    }

    // Compares the glob pattern against
    // the file or directory name.
    void find(Path file) {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            matched.add(file);
            //System.out.println(file);
        }
    }

    // Prints the total number of
    // matches to standard out.
    void done() {
        System.out.println("Matched: "
            + matched.size());
    }
    
    public List<Path> getMatchedPaths() {
    	return matched;
    }

    // Invoke the pattern matching
    // method on each file.
    @Override
    public FileVisitResult visitFile(Path file,
            BasicFileAttributes attrs) {
        find(file);
        return FileVisitResult.CONTINUE;
    }

    // Invoke the pattern matching
    // method on each directory.
    @Override
    public FileVisitResult preVisitDirectory(Path dir,
            BasicFileAttributes attrs) {
        find(dir);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file,
            IOException exc) {
        System.err.println(exc);
        return FileVisitResult.CONTINUE;
    }
}

}

package eu.openminted.share.annotations.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Methods to retrieve OpenMinTeD-SHARE descriptors.
 */
public class DescriptorResolver
{
    private static final Object SCAN_LOCK = new Object();

    private static String[] descriptorLocations;
    
    /**
     * Scan patterns from manifest files and from the specified system property.
     * 
     * @return array or all patterns found.
     * @throws IOException
     *             if there was a problem resolving the metadata locations from the patterns
     */
    private static String[] scanImportsAndManifests()
        throws IOException
    {
        ArrayList<String> patterns = new ArrayList<String>();

        // Scan manifest
        for (String mfUrl : resolve("classpath*:META-INF/eu.openminted.share/descriptors.txt")) {
            try (InputStream is = new URL(mfUrl).openStream()) {
                patterns.addAll(IOUtils.readLines(is));
            }
        }

        return patterns.toArray(new String[patterns.size()]);
    }

    /**
     * Resolve a list of patterns to a set of URLs.
     * 
     * @param patterns
     *            the patterns to resolve
     * @return an array of locations.
     * @throws IOException
     *             if the locations could not be resolved.
     */
    private static String[] resolve(String... patterns)
        throws IOException
    {
        Set<String> locations = new HashSet<String>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        
        // Scan auto-import locations. Using a set to avoid scanning a pattern twice.
        for (String pattern : new TreeSet<String>(Arrays.asList(patterns))) {
            String p = pattern.trim();
            if (p.length() == 0) {
                continue;
            }
            for (Resource r : resolver.getResources(pattern)) {
                locations.add(r.getURL().toString());
            }
        }
        
        return locations.toArray(new String[locations.size()]);
    }

    /**
     * Get all currently accessible OpenMinTeD-SHARE descriptors.
     * 
     * @return an array of locations.
     * @throws IOException
     *             if the locations could not be resolved.
     */
    public static String[] scanDescriptors()
        throws IOException
    {
        synchronized (SCAN_LOCK) {
            if (descriptorLocations == null) {
                descriptorLocations = resolve(scanImportsAndManifests());
            }
            return descriptorLocations;
        }
    }
}

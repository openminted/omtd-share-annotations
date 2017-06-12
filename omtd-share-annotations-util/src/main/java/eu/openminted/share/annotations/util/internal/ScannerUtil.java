package eu.openminted.share.annotations.util.internal;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class ScannerUtil
{
    /**
     * Resolve a list of patterns to a set of URLs.
     * 
     * @param patterns
     *            the patterns to resolve
     * @return an array of locations.
     */
    public static String[] resolve(String... patterns)
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
}

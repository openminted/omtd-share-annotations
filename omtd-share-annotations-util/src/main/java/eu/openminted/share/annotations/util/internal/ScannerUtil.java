/*
 * Licensed to the OpenMinTeD Consortium under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The OpenMinTeD Consortium 
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.
 *  
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

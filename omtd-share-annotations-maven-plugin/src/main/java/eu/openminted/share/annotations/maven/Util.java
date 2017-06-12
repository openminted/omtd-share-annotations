/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package eu.openminted.share.annotations.maven;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public final class Util {

  private Util() {
    // No instances
  }

  /**
   * Get the class name for a class file.
   */
  public static String getClassName(MavenProject aProject, String aFile) {
    String base = aFile.substring(0, aFile.length() - 6);
    String clazzPath = base.substring(aProject.getBuild().getOutputDirectory().length() + 1);
    return clazzPath.replace(File.separator, ".");
  }

  /**
   * Create a class loader which covers the classes compiled in the current project and all
   * dependencies.
   */
  public static URLClassLoader getClassloader(MavenProject aProject, Log aLog)
          throws MojoExecutionException {
    List<URL> urls = new ArrayList<URL>();
    try {
      for (Object object : aProject.getCompileClasspathElements()) {
        String path = (String) object;
        aLog.debug("Classpath entry: " + object);
        urls.add(new File(path).toURI().toURL());
      }
    } catch (IOException e) {
      throw new MojoExecutionException("Unable to assemble classpath: "
              + ExceptionUtils.getRootCauseMessage(e), e);
    } catch (DependencyResolutionRequiredException e) {
      throw new MojoExecutionException("Unable to resolve dependencies: "
              + ExceptionUtils.getRootCauseMessage(e), e);
    }

    for (Artifact dep : (Set<Artifact>) aProject.getDependencyArtifacts()) {
      try {
        if (dep.getFile() == null) {
          // Unresolved file because it is in the wrong scope (e.g. test?)
          continue;
        }
        aLog.debug("Classpath entry: " + dep.getGroupId() + ":" + dep.getArtifactId() + ":"
                + dep.getVersion() + " -> " + dep.getFile());
        urls.add(dep.getFile().toURI().toURL());
      } catch (Exception e) {
        throw new MojoExecutionException("Unable get dependency artifact location for "
                + dep.getGroupId() + ":" + dep.getArtifactId() + ":" + dep.getVersion()
                + ExceptionUtils.getRootCauseMessage(e), e);
      }
    }
    return new URLClassLoader(urls.toArray(new URL[] {}), Util.class.getClassLoader());
  }
}

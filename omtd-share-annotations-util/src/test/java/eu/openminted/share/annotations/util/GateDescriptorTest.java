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
package eu.openminted.share.annotations.util;

import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import eu.openminted.share.annotations.util.scanner.GateComponentScanner;

public class GateDescriptorTest
{
    @Test
    public void testSingleGateDescriptior()
        throws IOException
    {
        GateComponentScanner scanner = new GateComponentScanner();

        scanner.scan("file:src/test/resources/META-INF/gate/creole.xml");

        assertEquals("Unexpected number of GATE resources found", 15,
                scanner.getComponents().size());
    }
    
    @Test
    public void testAllGateDescriptiors()
        throws IOException
    {
        GateComponentScanner scanner = new GateComponentScanner();

        scanner.scan(getClass().getClassLoader());

        assertEquals("Unexpected number of GATE resources found", 15,
                scanner.getComponents().size());
    }
}

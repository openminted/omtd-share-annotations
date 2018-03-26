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
package eu.openminted.share.annotations.util.analyzer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import eu.openminted.registry.domain.LicenceEnum;

public class LicenseMappingsTest
{
    @Test
    public void test()
    {
        assertEquals(LicenceEnum.GPL_3_0, LicenseMappings
                .getSpdxIdFromUrl("http://www.gnu.org/licenses/gpl-3.0-standalone.html"));
    }
}

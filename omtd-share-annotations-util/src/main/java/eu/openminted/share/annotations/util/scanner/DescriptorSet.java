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
package eu.openminted.share.annotations.util.scanner;

import eu.openminted.registry.domain.Component;

public class DescriptorSet<T>
{
    private Component omtdShareDescriptor;
    private String nativeDescriptorLocation;
    private T nativeDescriptor;
    private String implementationName;

    public Component getOmtdShareDescriptor()
    {
        return omtdShareDescriptor;
    }

    public void setOmtdShareDescriptor(Component aOmtdShareDescriptor)
    {
        omtdShareDescriptor = aOmtdShareDescriptor;
    }

    public String getNativeDescriptorLocation()
    {
        return nativeDescriptorLocation;
    }

    public void setNativeDescriptorLocation(String aNativeDescriptorLocation)
    {
        nativeDescriptorLocation = aNativeDescriptorLocation;
    }

    public T getNativeDescriptor()
    {
        return nativeDescriptor;
    }

    public void setNativeDescriptor(T aNativeDescriptor)
    {
        nativeDescriptor = aNativeDescriptor;
    }

    public String getImplementationName()
    {
        return implementationName;
    }

    public void setImplementationName(String aImplementationName)
    {
        implementationName = aImplementationName;
    }

}

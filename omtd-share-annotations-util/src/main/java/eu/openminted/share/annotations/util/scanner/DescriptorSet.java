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

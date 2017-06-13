package eu.openminted.share.annotations.util.scanner;

import java.io.IOException;
import java.util.List;

public interface ComponentScanner<T>
{
    void scan(String... aPatterns)
        throws IOException;

    List<DescriptorSet<T>> getComponents();
}

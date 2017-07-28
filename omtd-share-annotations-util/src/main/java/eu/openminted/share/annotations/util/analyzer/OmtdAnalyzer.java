package eu.openminted.share.annotations.util.analyzer;

import static eu.openminted.share.annotations.util.ComponentDescriptorFactory.createPersonInfo;
import static eu.openminted.share.annotations.util.internal.ReflectionUtil.getInheritableAnnotation;

import eu.openminted.registry.domain.Component;
import eu.openminted.registry.domain.ComponentInfo;
import eu.openminted.registry.domain.ContactInfo;
import eu.openminted.share.annotations.api.ContactPerson;

public class OmtdAnalyzer
    implements Analyzer<Class<?>>
{
    @Override
    public void analyze(Component aDescriptor, Class<?> aComponent)
    {
        eu.openminted.share.annotations.api.Component annoComponent = getInheritableAnnotation(
                eu.openminted.share.annotations.api.Component.class, aComponent);
        if (annoComponent != null) {
            analyzeComponent(aDescriptor, annoComponent);
        }
        
        ContactPerson annoContactPerson = getInheritableAnnotation(ContactPerson.class, aComponent);
        if (annoContactPerson != null) {
            analyzeContactPerson(aDescriptor, annoContactPerson);
        }
    }
    
    private void analyzeComponent(Component aDescriptor,
            eu.openminted.share.annotations.api.Component aComponentAnno)
    {
        if (aComponentAnno.value() != null) {
            ComponentInfo componentInfo = aDescriptor.getComponentInfo();
            if (componentInfo == null) {
                componentInfo = new ComponentInfo();
                aDescriptor.setComponentInfo(componentInfo);
            }
            
            componentInfo.setComponentType(aComponentAnno.value());
        }
    }
    
    private void analyzeContactPerson(Component aComponent, ContactPerson aComponentAnno)
    {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.getContactPersons()
                .add(createPersonInfo(aComponentAnno.name(), aComponentAnno.mail()));
    }
}

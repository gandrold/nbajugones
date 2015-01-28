package es.nbajugones.dbdao.base;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;
import org.springframework.util.ClassUtils;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to look for entities to be added in Spring.
 */
public class EntityCustomScanner implements PersistenceUnitPostProcessor
{

    /**
     * Pattern for the class.
     */
    private static final String RESOURCE_PATTERN = "**/*.class";
    /**
     * Packages to scan.
     */
    private String[] packagesToScan;
    /**
     * .
     */
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    /**
     * .
     */
    private TypeFilter[] entityTypeFilters = new TypeFilter[]{
	    new AnnotationTypeFilter(Entity.class, false), new AnnotationTypeFilter(Embeddable.class, false),
	    new AnnotationTypeFilter(MappedSuperclass.class, false) };
    /**
     * .
     * 
     * @param pui
     *            The
     */

    public final void postProcessPersistenceUnitInfo(final MutablePersistenceUnitInfo pui)
    {
	String[] entities = scanPackages();
	for (String entity : entities)
	{
	    pui.addManagedClassName(entity);
	}
    }

    /**
     * 
     * Set whether to use Spring-based scanning for entity classes in the classpath instead of listing annotated classes explicitly.
     * 
     * <p>
     * 
     * Default is none. Specify packages to search for autodetection of your entity classes in the classpath. This is analogous to
     * 
     * Spring's component-scan feature (org.springframework.context.annotation.ClassPathBeanDefinitionScanner}).
     * 
     * @param packages
     *            Packages to scan
     */
    public final void setPackagesToScan(final String[] packages)
    {
	this.packagesToScan = Arrays.copyOf(packages, packages.length);
    }

    /**
     * 
     * Perform Spring-based scanning for entity classes.
     * 
     * @see #setPackagesToScan
     * @return Packages to scan
     */

    protected final String[] scanPackages()
    {
	Set<String> entities = new HashSet<String>();
	if (this.packagesToScan != null)
	{
	    try
	    {
		for (String pkg : this.packagesToScan)
		{
		    String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
		    Resource[] resources = this.resourcePatternResolver.getResources(pattern);
		    MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);

		    for (Resource resource : resources)
		    {
			if (resource.isReadable())
			{
			    MetadataReader reader = readerFactory.getMetadataReader(resource);
			    String className = reader.getClassMetadata().getClassName();
			    if (matchesFilter(reader, readerFactory))
			    {
				entities.add(className);
			    }
			}
		    }
		}
	    }
	    catch (IOException ex)
	    {
	    	ex.printStackTrace();
	    }
	}
	return entities.toArray(new String[entities.size()]);
    }

    /**
     * 
     * Check whether any of the configured entity type filters matches the current class descriptor contained in the metadata.
     * 
     * @param reader
     *            Reader
     * @param readerFactory
     *            Reader factory
     * @return Boolean
     * @throws IOException
     *             Exception reader.
     */
    private boolean matchesFilter(final MetadataReader reader, final MetadataReaderFactory readerFactory) throws IOException
    {

	if (this.entityTypeFilters != null)
	{
	    for (TypeFilter filter : this.entityTypeFilters)
	    {
		if (filter.match(reader, readerFactory))
		{
		    return true;
		}
	    }
	}
	return false;
    }
}

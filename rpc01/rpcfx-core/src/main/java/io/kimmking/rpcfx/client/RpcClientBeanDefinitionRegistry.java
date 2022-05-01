package io.kimmking.rpcfx.client;

import com.fasterxml.jackson.databind.util.ClassUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class RpcClientBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware, ApplicationContextAware {
    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
    private ApplicationContext applicationContext;
    private ResourcePatternResolver resourcePatternResolver;
    private MetadataReaderFactory metadataReaderFactory;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Set<Class<?>> clazzSet = scanPackages("io.kimmking.rpcfx.demo.api");
        for(Class clazz: clazzSet){
            // 只处理service
            if(clazz.getSimpleName().endsWith("Service")){
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
                GenericBeanDefinition beanDefinition = (GenericBeanDefinition) builder.getRawBeanDefinition();
                beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(clazz);

                // setBeanClass 设置为factoryBean 返回factoryBean的getObject
                beanDefinition.setBeanClass(RpcClientProxyFactory.class);
                beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
                registry.registerBeanDefinition(clazz.getName(), beanDefinition);
            }
        }
    }

    private Set<Class<?>> scanPackages(String basePackage) {
        Set<Class<?>> set = new LinkedHashSet<>();
        String packagePath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resolveBasePackage(basePackage)
                + "/" + DEFAULT_RESOURCE_PATTERN;
        System.out.println(packagePath);
        try {
            Resource[] resources = this.resourcePatternResolver.getResources(packagePath);
            for (Resource resource : resources) {
                if(resource.isReadable()){
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    String className = metadataReader.getClassMetadata().getClassName();
                    System.out.println(className);
                    Class<?> clazz;
                    try {
                        clazz = Class.forName(className);
                        set.add(clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    private String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(this.getEnvironment().resolveRequiredPlaceholders(basePackage));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Environment getEnvironment() {
        return this.applicationContext.getEnvironment();
    }
}

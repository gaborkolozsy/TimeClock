/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock;

import hu.gaborkolozsy.timeclock.config.ApplicationConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

/**
 * The common development annotation for test classes.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles
@Transactional
@ContextConfiguration
public @interface Development {

    /**
     * Alias for {@link #profiles}.
     * 
     * <p>This attribute may <strong>not</strong> be used in conjunction with
     * {@link #profiles}, but it may be used <em>instead</em> of {@link #profiles}.
     * @return {@code String} array with active profile(s)
     */
    @AliasFor(annotation = ActiveProfiles.class, attribute = "value")
    String[] value() default {"development"};
    
    /**
     * The bean definition profiles to activate.
     * 
     * <p>This attribute may <strong>not</strong> be used in conjunction with
     * {@link #value}, but it may be used <i>instead</i> of {@link #value}.
     * @return {@code String} array with active profile(s)
     */
    @AliasFor(annotation = ActiveProfiles.class, attribute = "profiles")
    String[] profiles() default {"development"};
    
    /**
     * Alias for {@link #xmlLocations}.
     * 
     * <p>This attribute may <strong>not</strong> be used in conjunction with
     * {@link #xmlLocations}, but it may be used instead of {@link #xmlLocations}.
     * @return {@code String} array with xml file location(s)
     */
    @AliasFor(annotation = ContextConfiguration.class, attribute = "value")
    String[] xmlValue() default {};
    
    /**
     * The resource locations to use for loading an
     * {@link org.springframework.context.ApplicationContext}.
     * 
     * <p>This attribute may <strong>not</strong> be used in conjunction with
     * {@link #xmlValues}, but it may be used instead of {@link #xmlValues}.
     * @return {@code String} array with xml file location(s)
     */
    @AliasFor(annotation = ContextConfiguration.class, attribute = "locations")
    String[] xmlLocations() default {};
    
    /**
     * The <i>annotated classes</i> to use for loading an
     * {@link org.springframework.context.ApplicationContext}.
     * @return {@code Class} array with configuration class(es)
     */ 
    @AliasFor(annotation = ContextConfiguration.class, attribute = "classes")
    Class<?>[] classes() default {ApplicationConfig.class};
    
    /**
     * The type of {@link org.springframework.test.context.SmartContextLoader} 
     * (or {@link ContextLoader}) to use for loading an 
     * {@link org.springframework.context.ApplicationContext}.
     * 
     * <p>If not specified, the loader will be inherited from the first superclass
     * that is annotated with <i>{@code @ContextConfiguration}</i> and specifies an
     * explicit loader. If no class in the hierarchy specifies an explicit
     * loader, a default loader will be used instead.
     * @return {@code Class} array with configuration loader class
     */
    @AliasFor(annotation = ContextConfiguration.class, attribute = "loader")
    Class<? extends ContextLoader> loader() default AnnotationConfigContextLoader.class;
    
}

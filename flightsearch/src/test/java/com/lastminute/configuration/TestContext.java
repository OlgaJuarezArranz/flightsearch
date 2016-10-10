/**
 * flight search
 */
package com.lastminute.configuration;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.ui.ModelMap;

import com.lastminute.entities.Search;

/**
 *
 * @author ojuarez
 *
 */
@Configuration
public class TestContext {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // to validation messages

        return messageSource;
    }

    @Bean
    public Search searchInstTest() {
        return Mockito.mock(Search.class);
    }

    @Bean
    public ModelMap modelMapInstTest() {
        return Mockito.mock(ModelMap.class);
    }
}

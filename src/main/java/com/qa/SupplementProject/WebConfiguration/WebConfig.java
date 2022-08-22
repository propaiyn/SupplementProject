package com.qa.SupplementProject.WebConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configurePathMatch(PathMatchConfigurer config) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        pathMatcher.setCaseSensitive(false);
        config.setPathMatcher(pathMatcher);
    }
}
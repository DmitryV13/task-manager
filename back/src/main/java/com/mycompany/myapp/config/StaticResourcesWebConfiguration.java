package com.mycompany.myapp.config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.undertow.server.handlers.resource.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;
import tech.jhipster.config.JHipsterConstants;
import tech.jhipster.config.JHipsterProperties;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@Profile({ JHipsterConstants.SPRING_PROFILE_PRODUCTION })
public class StaticResourcesWebConfiguration implements WebMvcConfigurer {

    protected static final String[] RESOURCE_LOCATIONS = { "classpath:/static/", "classpath:/static/content/", "classpath:/static/i18n/" };
    protected static final String[] RESOURCE_PATHS = { "/*.js", "/*.css", "/*.svg", "/*.png", "*.ico", "/content/**", "/i18n/*" };
    private static final Logger log = LoggerFactory.getLogger(StaticResourcesWebConfiguration.class);

    private final JHipsterProperties jhipsterProperties;

    public StaticResourcesWebConfiguration(JHipsterProperties jHipsterProperties) {
        this.jhipsterProperties = jHipsterProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ws/info")
            .resourceChain(false)
            .addResolver(new WebSocketResourceResolver());
        ResourceHandlerRegistration resourceHandlerRegistration = appendResourceHandler(registry);
        initializeResourceHandler(resourceHandlerRegistration);
    }

    private static class WebSocketResourceResolver implements ResourceResolver {
        @Override
        public org.springframework.core.io.Resource resolveResource(HttpServletRequest request, String requestPath, List<? extends org.springframework.core.io.Resource> locations, ResourceResolverChain chain) {
            log.debug("Request to /ws/info intercepted, returning null");
            log.debug("Request details: {}", request.getRequestURI());
            return null;
        }

        @Override
        public String resolveUrlPath(String resourcePath, List<? extends org.springframework.core.io.Resource> locations, ResourceResolverChain chain) {
            return null;
        }
    }

    protected ResourceHandlerRegistration appendResourceHandler(ResourceHandlerRegistry registry) {
        return registry.addResourceHandler(RESOURCE_PATHS);
    }

    protected void initializeResourceHandler(ResourceHandlerRegistration resourceHandlerRegistration) {
        resourceHandlerRegistration.addResourceLocations(RESOURCE_LOCATIONS).setCacheControl(getCacheControl());
    }

    protected CacheControl getCacheControl() {
        return CacheControl.maxAge(getJHipsterHttpCacheProperty(), TimeUnit.DAYS).cachePublic();
    }

    private int getJHipsterHttpCacheProperty() {
        return jhipsterProperties.getHttp().getCache().getTimeToLiveInDays();
    }
}

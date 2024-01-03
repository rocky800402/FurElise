package com.furelise.resolver;

import org.springframework.lang.NonNull;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.AbstractResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class HtmlResourceResolver extends AbstractResourceResolver {
    private static final String HTML_SUFFIX = ".html";

    @Override
    protected Resource resolveResourceInternal(HttpServletRequest request,@NonNull String requestPath,
                                               @NonNull List<? extends Resource> locations, ResourceResolverChain chain) {
        Resource resolved = chain.resolveResource(request, requestPath, locations);
        return resolved == null ? chain.resolveResource(request, requestPath + HTML_SUFFIX, locations) : resolved;
    }

    @Override
    protected String resolveUrlPathInternal(@NonNull String resourceUrlPath,@NonNull List<? extends Resource> locations,
                                            ResourceResolverChain chain) {
        return chain.resolveUrlPath(resourceUrlPath, locations);
    }
}

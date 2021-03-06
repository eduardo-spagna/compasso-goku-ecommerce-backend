package com.compassouol.gokuecommerce.configurations;

import com.compassouol.gokuecommerce.configurations.cache.CacheKeyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    CacheKeyFilter cacheKeyFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cacheKeyFilter).addPathPatterns("/**").excludePathPatterns("/swagger-ui/**",
                "/webjars/springfox-swagger-ui/**");
    }
}

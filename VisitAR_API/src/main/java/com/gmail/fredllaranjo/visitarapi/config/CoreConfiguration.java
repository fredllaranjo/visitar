package com.gmail.fredllaranjo.visitarapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.fredllaranjo.visitarapi.api.service.AssetBundlesService;
import com.gmail.fredllaranjo.visitarapi.core.business.AssetBundlesBusiness;
import com.gmail.fredllaranjo.visitarapi.core.service.impl.AssetBundlesServiceImpl;
import com.gmail.fredllaranjo.visitarapi.core.validator.AssetBundlesValidator;

@Import({ RestApiConfiguration.class })
@Configuration
@RestController
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CoreConfiguration {

    @Bean
    public AssetBundlesBusiness assetBundlesBusiness() {
        return new AssetBundlesBusiness();
    }

    @Bean
    public AssetBundlesValidator assetBundlesValidator() {
        return new AssetBundlesValidator();
    }

    @Bean
    @Scope("session")
    public AssetBundlesService assetBundlesService(AssetBundlesValidator validator, AssetBundlesBusiness business) {
        return new AssetBundlesServiceImpl(validator, business);
    }

}

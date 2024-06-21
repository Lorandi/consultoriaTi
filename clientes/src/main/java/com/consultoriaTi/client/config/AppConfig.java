package com.consultoriaTi.client.config;

import com.consultoriaTi.client.helper.MessageHelper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AppConfig {
    @Bean
    MessageHelper messageHelper(MessageSource messageSource) {
        return new MessageHelper(messageSource);
    }

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("classpath:i18n/messages");
        source.setCacheSeconds(3600);
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}

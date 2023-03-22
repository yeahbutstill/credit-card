package com.yeahbutstill.creditcard.config;

import com.yeahbutstill.creditcard.interceptor.EncryptionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.Map;

@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer {

    EncryptionInterceptor interceptor;

    @Autowired
    public InterceptorRegistration(EncryptionInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", interceptor);
    }
}

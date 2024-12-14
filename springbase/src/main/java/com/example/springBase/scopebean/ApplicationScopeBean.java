package com.example.springBase.scopebean;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationScopeBean {
    @PostConstruct
    public void init() {
        System.out.println("🔵 ApplicationScopeBean 생성됨: " + this);
    }

    public String getScopeName() {
        return "Application Scope";
    }
}

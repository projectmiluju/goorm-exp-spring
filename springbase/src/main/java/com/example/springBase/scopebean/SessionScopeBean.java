package com.example.springBase.scopebean;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopeBean {
    @PostConstruct
    public void init() {
        System.out.println("🟡 SessionScopeBean 생성됨: " + this);
    }

    public String getScopeName() {
        return "Session Scope";
    }
}

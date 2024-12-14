package com.example.springBase.scopebean;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeBean {
    @PostConstruct
    public void init() {
        System.out.println("🟢 RequestScopeBean 생성됨: " + this);
    }

    public String getScopeName() {
        return "Request Scope";
    }
}
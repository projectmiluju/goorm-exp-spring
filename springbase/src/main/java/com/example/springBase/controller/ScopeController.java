package com.example.springBase.controller;

import com.example.springBase.scopebean.ApplicationScopeBean;
import com.example.springBase.scopebean.RequestScopeBean;
import com.example.springBase.scopebean.SessionScopeBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScopeController {

    private final RequestScopeBean requestBean;
    private final SessionScopeBean sessionBean;
    private final ApplicationScopeBean applicationBean;

    public ScopeController(RequestScopeBean requestBean,
                           SessionScopeBean sessionBean,
                           ApplicationScopeBean applicationBean) {
        this.requestBean = requestBean;
        this.sessionBean = sessionBean;
        this.applicationBean = applicationBean;
    }

    @GetMapping("/scope")
    public String scopeInfo(Model model) {
        model.addAttribute("request", requestBean.getScopeName() + " : " + requestBean);
        model.addAttribute("session", sessionBean.getScopeName() + " : " + sessionBean);
        model.addAttribute("application", applicationBean.getScopeName() + " : " + applicationBean);
        return "scope-view";
    }
}
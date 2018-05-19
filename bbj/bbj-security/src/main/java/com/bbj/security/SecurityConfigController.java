package com.bbj.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbj.base.constant.Constants;

@Controller
@RequestMapping(value={Constants.module_security})
public class SecurityConfigController {
	
	@RequestMapping("/login")
	public String login() {
        return Constants.module_security + "/login" ;
    }
}

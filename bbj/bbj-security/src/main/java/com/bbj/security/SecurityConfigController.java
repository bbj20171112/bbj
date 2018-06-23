package com.bbj.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbj.base.constant.Constants;

@Controller
@RequestMapping(value={Constants.module_security})
public class SecurityConfigController {
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.getSession().setAttribute(Constants.sessionAttr_currentSkin, Constants.sessionAttr_Skin_greenLight);
        return Constants.module_security + "/login" ;
    }
}

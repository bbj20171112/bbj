package com.bbj.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbj.base.utils.CreateImageCode;

@Controller
@RequestMapping(value={"/rl"})
public class RgstAndLoginController {

	@Autowired
	private com.bbj.base.service.RLService RLService;
	
	@RequestMapping(value={"/","/register"})
	public String register(@RequestParam("hobby") String[] hoppy, HttpServletRequest request){
		String gender = request.getParameter("gender");
		System.out.println(gender + hoppy[0]);
		return "/base/widgets/register";
	}
	
	@RequestMapping(value={"/login/vacount"})
	@ResponseBody
	public Object vAcount(HttpServletRequest request){
		String userName = "";
		String password = "";
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		
		//模拟数据校验部分
		if ("1".equals(password) && "1".equals(userName)) {
			return "success";
		}
		
		return "false";
	}
	
	@RequestMapping(value={"/vcode"})
	public void vCode(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		try {
			CreateImageCode.getImgCode(request, response, session);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value={"/vvcode"})
	@ResponseBody
	public Object vVcode(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String content = session.getAttribute("code").toString();
		String tempCot = request.getParameter("content");
		if(content.equalsIgnoreCase(tempCot)) {
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value={"/vname"})
	@ResponseBody
	public Object vName(HttpServletRequest request, HttpServletResponse response){
		String userName = "";
		userName = request.getParameter("userName");
		
		if(RLService.getUserByName(userName) != null) {
			return "true";
		}
		return "false";
	}
	
}

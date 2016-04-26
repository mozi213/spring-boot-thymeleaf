package cn.newtouch.ldap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.newtouch.ldap.service.LdapService;

@Controller
public class LdapController {

	@Autowired
	private LdapService ldapService;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(String message){
		ModelAndView mv = new ModelAndView("ldap");
		if(!StringUtils.isEmpty(message)){
			mv.addObject("message", message);
		}
		return mv;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(String uid,String oldPasswd,String newPasswd,RedirectAttributes attr){
		
		try {
			ldapService.modifyPassword(uid, oldPasswd, newPasswd);
			attr.addAttribute("message", "操作成功");
		} catch (IllegalArgumentException e) {
			attr.addAttribute("message",e.getMessage());
		}
		
		return "redirect:/index";  
	}
}

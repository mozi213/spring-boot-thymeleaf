package cn.newtouch.ldap.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.newtouch.ldap.service.DroolsService;

@RestController
@RequestMapping(value="/drools")
public class DroolsController {

	@Autowired
	private DroolsService droolsService;
	
	@RequestMapping(value="/fire",produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,String> fire(){
		
		Map<String,String> result = new HashMap<>();
		this.droolsService.fire();
		result.put("result", "success");
		return result;
	}
}

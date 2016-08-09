package cn.newtouch.ldap.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.newtouch.ldap.service.DroolsService;
import cn.newtouch.ldap.service.helloworld.HelloWorldExample.Message;

@RestController
@RequestMapping(value="/drools")
public class DroolsController {

	@Autowired
	private DroolsService droolsService;
	
	@RequestMapping(value="/fire",produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> fire(){
		
		Map<String,Object> result = new HashMap<>();
		Message message = this.droolsService.fire();
		result.put("result", "success");
		result.put("message", message.getMessage());
		result.put("status", message.getStatus());
		return result;
	}
}

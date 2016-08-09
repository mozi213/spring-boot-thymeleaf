package cn.newtouch.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.newtouch.ldap.Application;
import cn.newtouch.ldap.service.DroolsService;
import cn.newtouch.ldap.service.helloworld.HelloWorldExample.Message;
import cn.newtouch.ldap.service.internalmodel.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SpringdroolsApplicationTests {


	private static final Logger LOG = LoggerFactory.getLogger(SpringdroolsApplicationTests.class);
	
	@Autowired
	private KieBase kieBase;
	
	@Autowired
	private DroolsService droolsService;
	
	@Test
	public void contextLoads() {
		// Given
	    Address address = new Address();
	    address.setPostcode("99425");
	    address.setStreet("Haalstreet");
	    address.setState("ALBANIA");
	    
	    KieSession kieSession = kieBase.newKieSession();
	    // When
	    // Let´s give the Drools Knowledge-Base an Object, we can then apply rules on
	    kieSession.insert(address);
		int ruleFiredCount = kieSession.fireAllRules();
		

		
		// Then		
		assertEquals("there´s 1 rule, so there should be 1 fired", 1, ruleFiredCount);
		LOG.debug("Rules checked: {}" + ruleFiredCount);
		kieSession.dispose();
	}
	
	
	@Test
	public void helloWorldTest(){
		Message message = this.droolsService.fire();
		assertEquals(Message.GOODBYE,message.getStatus());
	}

}

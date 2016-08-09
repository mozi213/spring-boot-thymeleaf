package cn.newtouch.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.newtouch.ldap.Application;
import cn.newtouch.ldap.service.helloworld.HelloWorldExample.Message;
import cn.newtouch.ldap.service.internalmodel.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class SpringdroolsApplicationTests {


	private static final Logger LOG = LoggerFactory.getLogger(SpringdroolsApplicationTests.class);
	
	@Autowired
	private KieBase kieBase;
	
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
		KieSession kieSession = kieBase.newKieSession();
		  // Once the session is created, the application can interact with it
        // In this case it is setting a global as defined in the
        // org/drools/examples/helloworld/HelloWorld.drl file
        kieSession.setGlobal( "list",
                            new ArrayList<Object>() );

        // The application can also setup listeners
        kieSession.addEventListener( new DebugAgendaEventListener() );
        kieSession.addEventListener( new DebugRuleRuntimeEventListener() );

        // To setup a file based audit logger, uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newFileLogger( kieSession, "./helloworld" );

        // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( kieSession, "./helloworld", 1000 );

        // The application can insert facts into the session
        final Message message = new Message();
        message.setMessage( "Hello World" );
        message.setStatus( Message.HELLO );
        kieSession.insert( message );

        // and fire the rules
        kieSession.fireAllRules();

        // Remove comment if using logging
        // logger.close();

        // and then dispose the session
        System.out.println(message);
        kieSession.dispose();
	}

}

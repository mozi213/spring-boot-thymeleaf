package cn.newtouch.ldap.service;

import java.util.ArrayList;

import org.kie.api.KieBase;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtouch.ldap.service.helloworld.HelloWorldExample.Message;

@Service
public class DroolsService {

	@Autowired
	private KieBase kieBase;

	public Message fire() {
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
		
	  return message;
	}

	
}

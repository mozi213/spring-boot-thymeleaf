package cn.newtouch.ldap.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {


	public void fire() {
		
		   // KieServices is the factory for all KIE services 
        KieServices ks = KieServices.Factory.get();
        
        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();
		
		KieSession ksession = kc.newKieSession("HelloWorldKS");;
		ksession.setGlobal("list", new ArrayList<Object>());

		// The application can also setup listeners
		ksession.addEventListener(new DebugAgendaEventListener());
		ksession.addEventListener(new DebugRuleRuntimeEventListener());

		// To setup a file based audit logger, uncomment the next line
		// KieRuntimeLogger logger = ks.getLoggers().newFileLogger( ksession,
		// "./helloworld" );

		// To setup a ThreadedFileLogger, so that the audit view reflects events
		// whilst debugging,
		// uncomment the next line
		// KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger(
		// ksession, "./helloworld", 1000 );

		// The application can insert facts into the session
		final Message message = new Message();
		message.setMessage("Hello World");
		message.setStatus(Message.HELLO);
		ksession.insert(message);

		// and fire the rules
		ksession.fireAllRules();

		// Remove comment if using logging
		// logger.close();

		// and then dispose the session
		ksession.dispose();
	}

	public static class Message {
		public static final int HELLO = 0;
		public static final int GOODBYE = 1;

		private String message;

		private int status;

		public Message() {

		}

		public String getMessage() {
			return this.message;
		}

		public void setMessage(final String message) {
			this.message = message;
		}

		public int getStatus() {
			return this.status;
		}

		public void setStatus(final int status) {
			this.status = status;
		}

		public static Message doSomething(Message message) {
			return message;
		}

		public boolean isSomething(String msg, List<Object> list) {
			list.add(this);
			return this.message.equals(msg);
		}
	}
}

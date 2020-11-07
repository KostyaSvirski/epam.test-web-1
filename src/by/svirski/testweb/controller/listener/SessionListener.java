package by.svirski.testweb.controller.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import by.svirski.testweb.controller.RequestParameters;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionListener() {
        
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         HttpSession session = se.getSession();
         session.setAttribute(RequestParameters.LANGUAGE, RequestParameters.DEFAULT_LANG);
         
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         
    }
	
}

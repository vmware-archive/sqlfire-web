package vmware.au.se.sqlfireweb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vmware.au.se.sqlfireweb.dao.ISQLFireDAOFactory;
import vmware.au.se.sqlfireweb.dao.gatewayrecievers.GatewayReceiver;
import vmware.au.se.sqlfireweb.dao.gatewayrecievers.GatewayReceiverDAO;
import vmware.au.se.sqlfireweb.main.Result;
import vmware.au.se.sqlfireweb.main.UserPref;

@Controller
public class GatewayReceiverController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/gatewayreceivers", method = RequestMethod.GET)
    public String showGatewayReceivers
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
		int startAtIndex = 0, endAtIndex = 0;
		javax.servlet.jsp.jstl.sql.Result allGatewayRecieverInfoResult = null;
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to show gateway recievers");
    	
    	GatewayReceiverDAO grDAO = ISQLFireDAOFactory.getGatewayRecieverDAO();
    	Result result = new Result();
    	
    	String grAction = request.getParameter("grAction");
    	
    	if (grAction != null)
    	{
    		logger.debug("grAction = " + grAction);
    		
    		if (grAction.equals("ALLGATEWAYRECIEVERINFO"))
    		{
    			allGatewayRecieverInfoResult = 
    					grDAO.getGatewayRecieverInfo
    					  ((String)request.getParameter("id"),
    					   (String)session.getAttribute("user_key"));
    			
    			model.addAttribute("allGatewayRecieverInfoResult", allGatewayRecieverInfoResult);
    			model.addAttribute("gatewayreciever", (String)request.getParameter("id"));
    		}
    		else
    		{
	            result = null;
	            result =
	            	grDAO.simplegatewayReceiverCommand
	                ((String)request.getParameter("id"),
	                 grAction,
	                 (String)session.getAttribute("user_key"));
	            
	            model.addAttribute("result", result);
    		}
    	}
    	
    	List<GatewayReceiver> gatewayreceivers = grDAO.retrieveGatewayReceiverList
    			((String)session.getAttribute("schema"), 
    			 null, 
    			 (String)session.getAttribute("user_key"));
    	
    	model.addAttribute("records", gatewayreceivers.size());
    	model.addAttribute("estimatedrecords", gatewayreceivers.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (gatewayreceivers.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("gatewayreceivers", gatewayreceivers); 
        }
        else
        {
          if (request.getParameter("startAtIndex") != null)
          {
            startAtIndex = Integer.parseInt(request.getParameter("startAtIndex"));
          }
          
          if (request.getParameter("endAtIndex") != null)
          {
            endAtIndex = Integer.parseInt(request.getParameter("endAtIndex"));
            if (endAtIndex > gatewayreceivers.size())
            {
              endAtIndex = gatewayreceivers.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = gatewayreceivers.subList(startAtIndex, endAtIndex);
          model.addAttribute("gatewayreceivers", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        
    	// This will resolve to /WEB-INF/jsp/gatewayreceivers.jsp
    	return "gatewayreceivers";
    }
	
	@RequestMapping(value = "/gatewayreceivers", method = RequestMethod.POST)
    public String performGatewayReceiversAction
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	int startAtIndex = 0, endAtIndex = 0;
    	Result result = new Result();
    	List<GatewayReceiver> gatewayreceivers = null;
    	
    	logger.debug("Received request to perform an action on the gateway recievers");

    	GatewayReceiverDAO grDAO = ISQLFireDAOFactory.getGatewayRecieverDAO();
    	
    	if (request.getParameter("search") != null)
    	{
    		gatewayreceivers = grDAO.retrieveGatewayReceiverList
				((String)session.getAttribute("schema"), 
				 (String)request.getParameter("search"), 
				 (String)session.getAttribute("user_key"));
	    	
	    	model.addAttribute("search", (String)request.getParameter("search"));
    	}
    	else
    	{
	        String[] tableList  = request.getParameterValues("selected_gatewayreceivers[]");
	        String   commandStr = request.getParameter("submit_mult");
	        
	        logger.debug("tableList = " + Arrays.toString(tableList));
	        logger.debug("command = " + commandStr);
	        
	        // start actions now if tableList is not null
	        
	        if (tableList != null)
	        {
	        	List al = new ArrayList<Result>();
	        	for (String id: tableList)
	        	{
	                result = null;
	                result =
                    	grDAO.simplegatewayReceiverCommand
                        (id,
                         commandStr,
                         (String)session.getAttribute("user_key"));
	                al.add(result);
	        	}
	        	
	        	model.addAttribute("arrayresult", al);
	        }
	        
	    	gatewayreceivers = grDAO.retrieveGatewayReceiverList
	    			((String)session.getAttribute("schema"), 
	    			 null, 
	    			 (String)session.getAttribute("user_key"));
    	}

    	model.addAttribute("records", gatewayreceivers.size());
    	model.addAttribute("estimatedrecords", gatewayreceivers.size());
    	
        UserPref userPref = (UserPref) session.getAttribute("prefs");
        
        if (gatewayreceivers.size() <= userPref.getRecordsToDisplay())
        {
        	model.addAttribute("gatewayreceivers", gatewayreceivers); 
        }
        else
        {
          if (request.getParameter("startAtIndex") != null)
          {
            startAtIndex = Integer.parseInt(request.getParameter("startAtIndex"));
          }
          
          if (request.getParameter("endAtIndex") != null)
          {
            endAtIndex = Integer.parseInt(request.getParameter("endAtIndex"));
            if (endAtIndex > gatewayreceivers.size())
            {
              endAtIndex = gatewayreceivers.size();
            }
          }
          else
          {
            endAtIndex = userPref.getRecordsToDisplay();
          }
          
          List subList = gatewayreceivers.subList(startAtIndex, endAtIndex);
          model.addAttribute("gatewayreceivers", subList);
        }  
        
        model.addAttribute("startAtIndex", startAtIndex);
        model.addAttribute("endAtIndex", endAtIndex);
        
    	// This will resolve to /WEB-INF/jsp/gatewayreceivers.jsp
    	return "gatewayreceivers";
    
    }
}

package vmware.au.se.sqlfireweb.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vmware.au.se.sqlfireweb.dao.ISQLFireDAOFactory;
import vmware.au.se.sqlfireweb.dao.ISQLFireDAOUtil;
import vmware.au.se.sqlfireweb.dao.diskstores.DiskStore;
import vmware.au.se.sqlfireweb.dao.diskstores.DiskStoreDAO;
import vmware.au.se.sqlfireweb.dao.types.Type;
import vmware.au.se.sqlfireweb.dao.types.TypeDAO;
import vmware.au.se.sqlfireweb.main.Result;

@Controller
public class CreateTableController 
{
	protected static Logger logger = Logger.getLogger("controller");
	private static final String FILENAME = "%s.sql";
	private static final String SAVE_CONTENT_TYPE = "application/x-download";
	
	@RequestMapping(value = "/createtable", method = RequestMethod.GET)
    public String createTable
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {		
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to create a new table");
    	
    	session.setAttribute("numColumns", "0");
    	session.setAttribute("tabName", "newtable");
    			
    	model.addAttribute("numColumns", "0");
    	
    	// This will resolve to /WEB-INF/jsp/create-table.jsp
    	return "create-table";
    }
	
	@RequestMapping(value = "/createtable", method = RequestMethod.POST)
    public String createTableAction
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to action an event for create table");
    	
    	String tabName = request.getParameter("tabName");
    	
    	String[] columnNames  = request.getParameterValues("column_name[]");
    	String[] columnTypes  = request.getParameterValues("column_type[]");
    	String[] columnPrecision  = request.getParameterValues("column_precision[]");
    	String[] columnDefaultValues  = request.getParameterValues("column_default_value[]");
    	String[] columnSelectedNulls  = request.getParameterValues("column_selected_null[]");
    	String[] columnSelectedPrimaryKeys  = request.getParameterValues("column_selected_primary_key[]");
    	String[] columnSelectedAutoIncrements  = request.getParameterValues("column_selected_auto_increment[]");
    	
    	logger.debug("New Table Name = " + tabName);
    	logger.debug("columnNames = " + Arrays.toString(columnNames));
    	logger.debug("columnTypes = " + Arrays.toString(columnTypes));
    	logger.debug("columnPrecision = " + Arrays.toString(columnPrecision));
    	logger.debug("columnDefaultValues = " + Arrays.toString(columnDefaultValues));
    	logger.debug("columnSelectedNulls = " + Arrays.toString(columnSelectedNulls));
    	logger.debug("columnSelectedPrimaryKeys = " + Arrays.toString(columnSelectedPrimaryKeys));
    	logger.debug("columnSelectedAutoIncrements = " + Arrays.toString(columnSelectedAutoIncrements));
    	
    	// perform some action here with what we have
    	String submit = request.getParameter("pSubmit");
    	
    	if (submit != null)
    	{

    		if (submit.equalsIgnoreCase("Column(s)"))
    		{
    	    	TypeDAO typeDAO = ISQLFireDAOFactory.getTypeDAO();
    	    	List<Type> types = typeDAO.retrieveTypeList
    	    			((String)session.getAttribute("schema"), 
    	    			 null, 
    	    			 (String)session.getAttribute("user_key"));
    	    	
    	    	model.addAttribute("types", types); 
    	    	
    	    	int cols = Integer.parseInt(request.getParameter("numColumns"));
    	    	int numColumns = Integer.parseInt((String)session.getAttribute("numColumns"));
    	    	
    	    	numColumns = numColumns + cols;
    	    	
    	    	session.setAttribute("numColumns", "" + numColumns);
    	    	session.setAttribute("tabName", (String)request.getParameter("tabName"));
    	    	
    	    	model.addAttribute("numColumns", numColumns);
    	    	model.addAttribute("tabName", (String)request.getParameter("tabName")); 
    	    	
    	    	DiskStoreDAO dsDAO = ISQLFireDAOFactory.getDiskStoreDAO();
    	    	
    	    	List<DiskStore> dsks = dsDAO.retrieveDiskStoreForCreateList
    	    			((String)session.getAttribute("user_key"));
    	    	
    	    	model.addAttribute("diskstores", dsks);
    		}
    		else
    		{
    			// build create table SQL 
    			StringBuffer createTable = new StringBuffer();
    			
    			createTable.append("create table " + tabName + " \n");
    			createTable.append("(");
    			
    			int i = 0;
    			String val = null;
    			int size = columnNames.length;
    			
    			for (String columnName: columnNames)
    			{
    			  createTable.append(columnName + " ");
    			  createTable.append(columnTypes[i]);
    			  
    			  // doing precision / size
    			  val = checkIfEntryExists(columnPrecision, i);
    			  if (val != null)
    			  {
    				  createTable.append("(" + columnPrecision[i] + ")");
    			  }
    			  val = null;
    			  
    			  // doing auto increment check here
    			  val = checkIfEntryExists(columnSelectedAutoIncrements, i);
    			  if (val != null)
    			  {
    				  // should check for column type here
    				  if (val.equalsIgnoreCase("Y"))
    				  {
    					  createTable.append(" generated always as identity");
    				  }
    			  }
    			  val = null;
    			  
    			  // doing default value
    			  val = checkIfEntryExists(columnDefaultValues, i);
    			  if (val != null)
    			  {
    				  // should check for column type here
    				  createTable.append(" default " + columnDefaultValues[i]);
    			  }
    			  val = null;
    			  
    			  // doing not null check here
    			  val = checkIfEntryExists(columnSelectedNulls, i);
    			  if (val != null)
    			  {
    				  if (val.equalsIgnoreCase("Y"))
    				  {
    					  createTable.append(" NOT NULL");
    				  }
    			  }
    			  val = null;
    			  
    			  // doing primary key check here
    			  val = checkIfEntryExists(columnSelectedPrimaryKeys, i);
    			  if (val != null)
    			  {
    				  if (val.equalsIgnoreCase("Y"))
    				  {
    					  createTable.append(" CONSTRAINT " + tabName + "_PK Primary Key");
    				  }
    			  }
    			  val = null;
    			  
    			  int j = size - 1;
    			  if (i < j)
    			  {
    				  createTable.append(",\n");
    			  }
    			  
    			  i++;
    			}
    			
    			createTable.append(")\n");
    			
    			if (request.getParameter("dataPolicy").equalsIgnoreCase("REPLICATE"))
    			{
    			    createTable.append("REPLICATE\n");	
    			}
    			
    			if (!checkIfParameterEmpty(request, "serverGroups"))
    			{
    				createTable.append("SERVER GROUPS (" + request.getParameter("serverGroups") + ")\n");
    			}

    			if (!checkIfParameterEmpty(request, "persistant"))
    			{
    				if (request.getParameter("persistant").equalsIgnoreCase("Y"))
    				{
    					createTable.append("PERSISTENT ");
        				if (!checkIfParameterEmpty(request, "diskStore"))
        				{
        					createTable.append("'" + request.getParameter("diskStore") + "' ");
        					if (!checkIfParameterEmpty(request, "persistenceType"))
        					{
        						createTable.append(request.getParameter("persistenceType") + "\n");
        					}
        					else
        					{
        						createTable.append("\n");
        					}
        					
        				}
    				}
    			}

    			if (request.getParameter("dataPolicy").equalsIgnoreCase("PARTITION"))
    			{
        			if (!checkIfParameterEmpty(request, "partitionBy"))
        			{
        				createTable.append("PARTITION BY " + request.getParameter("partitionBy") + "\n");
        			}  		
        			
    				if (!checkIfParameterEmpty(request, "colocateWith"))
    				{
    					createTable.append("COLOCATE WITH (" + request.getParameter("colocateWith") + ")\n");
    				}

    				if (!checkIfParameterEmpty(request, "redundancy"))
    				{
    					createTable.append("REDUNDANCY " + request.getParameter("redundancy") + "\n");
    				}
    			}
    			
    			if (!checkIfParameterEmpty(request, "other"))
    			{
    				createTable.append(request.getParameter("other") + "\n");
    			}
    			
        		if (submit.equalsIgnoreCase("create"))
        		{
        			Result result = new Result();
        			
        			logger.debug("Creating table as -> " + createTable.toString());
        			
        			result = ISQLFireDAOUtil.runCommand
        					(createTable.toString(),
        					 (String)session.getAttribute("user_key"));
        			
        			model.addAttribute("result", result);
        			
        			session.removeAttribute("numColumns");
        			
        		}   
        		else if (submit.equalsIgnoreCase("Show SQL"))
        		{
        			logger.debug("Table SQL as follows as -> " + createTable.toString());
        			model.addAttribute("sql", createTable.toString());
        			session.removeAttribute("numColumns");
        		}
        		else if (submit.equalsIgnoreCase("Save to File"))
        		{
                    response.setContentType(SAVE_CONTENT_TYPE);
                    response.setHeader("Content-Disposition", "attachment; filename=" + 
                                       String.format(FILENAME, tabName));
                    
                    ServletOutputStream out = response.getOutputStream();
                    out.println(createTable.toString());
                    out.close();
                    return null;
        		}
    		}
    	}
    	
    	// This will resolve to /WEB-INF/jsp/create-table.jsp
    	return "create-table";
    }
	
	private String checkIfEntryExists (String[] attribute, int index)
	{
		if (attribute != null)
		{
			try
			{
				String val = attribute[index];
				if (val.trim().length() == 0)
				{
					return null;
				}
				else
				{
					return val;
				}
			}
			catch (Exception ex)
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	
	private boolean checkIfParameterEmpty (HttpServletRequest request, String param)
	{
		String val = request.getParameter(param);
		
		if (val != null)
		{
			if (val.trim().length() == 0)
			{
				return true;
			}
			else
			{
				// we have some data
				return false;
			}
		}
		else
		{
			// no data here
			return true;
		}
	}
}

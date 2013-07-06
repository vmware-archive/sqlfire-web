package vmware.au.se.sqlfireweb.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vmware.au.se.sqlfireweb.beans.IndexDefinition;
import vmware.au.se.sqlfireweb.dao.ISQLFireDAOFactory;
import vmware.au.se.sqlfireweb.dao.ISQLFireDAOUtil;
import vmware.au.se.sqlfireweb.dao.indexes.IndexColumn;
import vmware.au.se.sqlfireweb.dao.indexes.IndexDAO;
import vmware.au.se.sqlfireweb.main.Result;

@Controller
public class CreateIndexController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/createindex", method = RequestMethod.GET)
    public String createIndex
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {		
		
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to create a new index");
    	
    	String tabName = (String)request.getParameter("tabName");
    	String schema = (String)request.getParameter("schemaName");
    	
    	logger.debug("Table = " + schema + "." + tabName);
    	
    	IndexDAO indexDAO = ISQLFireDAOFactory.getIndexDAO();
    	
    	List<IndexColumn> columns = indexDAO.retrieveIndexColumns
    			(schema, tabName, (String)session.getAttribute("user_key"));
    	
    	model.addAttribute("tabName", tabName);
    	model.addAttribute("tableSchemaName", schema);
    	model.addAttribute("columns", columns);
    	model.addAttribute("schema", schema);
    	
    	// This will resolve to /WEB-INF/jsp/create-index.jsp
    	return "create-index";
    }
	
	@RequestMapping(value = "/createindex", method = RequestMethod.POST)
    public String createIndexAction
    (Model model, HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception 
    {
    	if (session.getAttribute("user_key") == null)
    	{
    		logger.debug("user_key is null new Login required");
    		response.sendRedirect(request.getContextPath() + "/sqlfireweb/login");	
    		return null;
    	}
    	
    	logger.debug("Received request to action an event for create index");
    	
    	String tabName = request.getParameter("tabName");
    	String schema = (String)request.getParameter("schemaName");
    	String unique = request.getParameter("unique");
    	String idxName = request.getParameter("idxName");
    	
    	String[] selectedColumns  = request.getParameterValues("selected_column[]");
    	String[] indexOrder  = request.getParameterValues("idxOrder[]");
    	String[] position  = request.getParameterValues("position[]");
    	
    	logger.debug("selectedColumns = " + Arrays.toString(selectedColumns));
    	logger.debug("indexOrder = " + Arrays.toString(indexOrder));
    	logger.debug("position = " + Arrays.toString(position));
    	
    	StringBuffer createIndex = new StringBuffer();
    	
    	if (unique.equalsIgnoreCase("Y"))
    	{
    		createIndex.append("create unique index " + idxName + " on " + schema + "." + tabName + " " );
    	}
    	else
    	{
    		createIndex.append("create index " + idxName + " on " + schema + "." + tabName + " ");
    	}
    	
    	createIndex.append("(");
    	
    	int i = 0;
    	Map<String, IndexDefinition> cols = new HashMap<String, IndexDefinition>();
    	
    	for (String column: selectedColumns)
    	{
    		String columnName = column.substring(0, column.indexOf("_"));
    		String index = column.substring(column.indexOf("_") + 1);
    		String pos = position[Integer.parseInt(index) - 1];
    		if (pos.trim().length() == 0)
    		{
    			pos = "" + i;
    		}
    		
    		
    		logger.debug("Column = " + columnName + ", indexOrder = " + 
    					 indexOrder[Integer.parseInt(index) - 1] + ", position = " + 
    				     pos);
    		
    		IndexDefinition idxDef = new IndexDefinition(columnName, indexOrder[Integer.parseInt(index) - 1]);
    		
    		cols.put("" + pos, idxDef);
    		i++;
    	}
    	
    	// sort map and create index now
    	SortedSet<String> keys = new TreeSet<String>(cols.keySet());
    	int length = keys.size();
    	i = 0;
    	for (String key : keys) 
    	{ 
    		IndexDefinition idxDefTemp = cols.get(key);
    		if (i == (length - 1))
    		{
    			createIndex.append(idxDefTemp.getColumnName() + " " + idxDefTemp.getOrderType() + ")");
    		}
    		else
    		{
    			createIndex.append(idxDefTemp.getColumnName() + " " + idxDefTemp.getOrderType() + ", ");
    		}
    		
    		i++;
    		
    	}
    	
    	logger.debug("Create index as follows -> " + createIndex.toString());
		
    	Result result = new Result();
    	
    	result = ISQLFireDAOUtil.runCommand
				(createIndex.toString(),
				 (String)session.getAttribute("user_key"));
		
		model.addAttribute("result", result);
		
    	// This will resolve to /WEB-INF/jsp/create-index.jsp
    	return "create-index";
    }
	
}

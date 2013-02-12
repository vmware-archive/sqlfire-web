import java.util.regex.Pattern;


public class RemoveMe 
{

	public RemoveMe()
	{
	}
	
	public void run()
	{
		String query =
				"-- test\n" +
				"select * from dept;\n" +
				"-- test 2\n" +
				"select * from dept where deptno = 20;";
		
        Pattern pattern = Pattern.compile(";\\s", Pattern.MULTILINE);
        String [] splitQueryStr = pattern.split(query);
        
        System.out.println("Number of statements = " + splitQueryStr.length);
        int i = 1;
        
    	for (String nextQuery: splitQueryStr)
    	{
    		String s = nextQuery.trim();
    		
    		String newQuery = checkForComments(s);

    		if (newQuery.trim().length() > 0)
    		{
				System.out.print("Query [" + i + "] -> ");
				System.out.print(newQuery + "\n");
				i++;
    		}
    	}
        
	}
	
	private String checkForComments(String s)
	{
		if (s.startsWith("--"))
		{
			int index = s.indexOf("\n");		
			if (index != -1)
			{
				String newQuery = s.substring(s.indexOf("\n"));
				if (newQuery.trim().startsWith("--"))
				{
					return checkForComments(newQuery.trim());
				}
				else
				{
					return newQuery.trim();
				}
			}
			else
			{
				return "";
			}
		}
		else
		{
			return s;
		}
			
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		RemoveMe test = new RemoveMe();
		test.run();
		
		
	}

}

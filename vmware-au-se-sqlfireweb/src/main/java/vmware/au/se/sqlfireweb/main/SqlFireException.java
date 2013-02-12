package vmware.au.se.sqlfireweb.main;

@SuppressWarnings("serial")
public class SqlFireException extends Exception
{
	public SqlFireException()
	{
	}

	public SqlFireException(final Throwable cause)
	{
		super(cause);   
	}
        
	public SqlFireException
	(final String msg,
	 final Throwable cause)
	{
		super(msg, cause);      
	}
        
    public SqlFireException(final String msg)
    {
    	super(msg);     
    }
}

package vmware.au.se.sqlfireweb.dao.diskstores;

public class DiskStore 
{
   private String name;
   private String directory;
   
   public DiskStore(String name, String directory) 
   {
		super();
		this.name = name;
		this.directory = directory;
   }

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDirectory() {
		return directory;
	}
	
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	@Override
	public String toString() {
		return "DiskStore [name=" + name + ", directory=" + directory + "]";
	}
   
	
   
}

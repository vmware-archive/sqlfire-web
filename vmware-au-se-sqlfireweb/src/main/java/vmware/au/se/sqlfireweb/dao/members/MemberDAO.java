package vmware.au.se.sqlfireweb.dao.members;

import java.util.List;

import vmware.au.se.sqlfireweb.main.SqlFireException;

public interface MemberDAO 
{
	public List<Member> retrieveMembers (String userKey) throws SqlFireException;
}

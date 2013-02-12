package vmware.au.se.sqlfireweb.dao;

import vmware.au.se.sqlfireweb.dao.asyncevent.AsynceventDAO;
import vmware.au.se.sqlfireweb.dao.asyncevent.AsynceventDAOImpl;
import vmware.au.se.sqlfireweb.dao.constraints.ConstraintDAO;
import vmware.au.se.sqlfireweb.dao.constraints.ConstraintDAOImpl;
import vmware.au.se.sqlfireweb.dao.diskstores.DiskStoreDAO;
import vmware.au.se.sqlfireweb.dao.diskstores.DiskStoreDAOImpl;
import vmware.au.se.sqlfireweb.dao.gatewayrecievers.GatewayReceiverDAO;
import vmware.au.se.sqlfireweb.dao.gatewayrecievers.GatewayReceiverDAOImpl;
import vmware.au.se.sqlfireweb.dao.gatewaysenders.GatewaySenderDAO;
import vmware.au.se.sqlfireweb.dao.gatewaysenders.GatewaySenderDAOImpl;
import vmware.au.se.sqlfireweb.dao.indexes.IndexDAO;
import vmware.au.se.sqlfireweb.dao.indexes.IndexDAOImpl;
import vmware.au.se.sqlfireweb.dao.stored.StoredProcDAO;
import vmware.au.se.sqlfireweb.dao.stored.StoredProcDAOImpl;
import vmware.au.se.sqlfireweb.dao.tables.TableDAO;
import vmware.au.se.sqlfireweb.dao.tables.TableDAOImpl;
import vmware.au.se.sqlfireweb.dao.triggers.TriggerDAO;
import vmware.au.se.sqlfireweb.dao.triggers.TriggerDAOImpl;
import vmware.au.se.sqlfireweb.dao.types.TypeDAO;
import vmware.au.se.sqlfireweb.dao.types.TypeDAOImpl;
import vmware.au.se.sqlfireweb.dao.views.ViewDAO;
import vmware.au.se.sqlfireweb.dao.views.ViewDAOImpl;

public class ISQLFireDAOFactory 
{
	 public static TableDAO getTableDAO()
	 {
		 return new TableDAOImpl();
	 }

	 public static ViewDAO getViewDAO()
	 {
		 return new ViewDAOImpl();
	 }

	 public static ConstraintDAO getConstraintDAO()
	 {
		 return new ConstraintDAOImpl();
	 }
	 
	 public static IndexDAO getIndexDAO()
	 {
		 return new IndexDAOImpl();
	 }

	 public static TriggerDAO getTriggerDAO()
	 {
		 return new TriggerDAOImpl();
	 }
	 
	 public static AsynceventDAO getAsynceventDAO()
	 {
		 return new AsynceventDAOImpl();
	 }
	 
	 public static StoredProcDAO getStoredProcDAO()
	 {
		 return new StoredProcDAOImpl();
	 }

	 public static GatewaySenderDAO getGatewaySenderDAO()
	 {
		 return new GatewaySenderDAOImpl();
	 }
	 
	 public static GatewayReceiverDAO getGatewayRecieverDAO()
	 {
		 return new GatewayReceiverDAOImpl();
	 }
	 
	 public static TypeDAO getTypeDAO()
	 {
		 return new TypeDAOImpl();
	 }

	 public static DiskStoreDAO getDiskStoreDAO()
	 {
		 return new DiskStoreDAOImpl();
	 }
}

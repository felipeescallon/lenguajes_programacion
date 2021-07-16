package holaComponentes;

import javax.ejb.*;

public interface HolaEJBHome extends javax.ejb.EJBHome {

public holaComponentes.HolaEJB create()
throws javax.ejb.CreateException, java.rmi.RemoteException;

}
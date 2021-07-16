package holaComponentes;

import javax.ejb.*;
import java.rmi.RemoteException;

public interface HolaEJB extends javax.ejb.EJBObject {
public String saludar() throws RemoteException;

}
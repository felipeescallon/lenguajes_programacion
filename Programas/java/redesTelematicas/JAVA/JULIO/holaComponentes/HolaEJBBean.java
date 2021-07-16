package holaComponentes;
javax.ejb.*;

public class HolaEJBBean implements javax.ejb.SessionBean {

	private javax.ejb.SessionContext context;
	public HolaEJBBean(){}
	public String saludar(){
	return(" Hola te saluda un Componente Java de tipo Session ");
}

public void setSessionContext(javax.ejb.SessionContext aContext) {
	context=aContext; }
	public void ejbActivate() {}
	public void ejbPassivate(){}
	public void ejbRemove() {}
	public void ejbCreate() {}
}
package jvn;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JvnProxy  implements  InvocationHandler {


	public JvnProxy(Object jo, JvnServerImpl js) {
	
		// TODO Auto-generated constructor stub
		try {
			obj = js.jvnCreateObject((Serializable)jo);
		} catch (JvnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private JvnObject obj;
	
	public static Object newInstance(Object obj,JvnServerImpl js) {
		return java.lang.reflect.Proxy.newProxyInstance(
				obj.getClass().getClassLoader(),
				obj.getClass().getInterfaces(),
				new JvnProxy(obj,js));
	}
	
	public JvnObject invoke(Object proxy, Method m, Object[] args)
	{
		// TODO Auto-generated method stub
		try {
			if(m.getName() == "read") {				
				//((JvnObject)obj).jvnLockRead();
			} else if (m.getName() == "write") {								
				//((JvnObject)obj).jvnLockWrite();
			}
			//JvnObject result = m.invoke(obj, args);
			
		//	((JvnObject)obj).jvnUnLock();
			
			System.out.println("Coucou");
			return null;
		//	return result;
		} catch (Exception e) {

		}
		return null;
		
	}

}

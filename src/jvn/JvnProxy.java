package jvn;

import irc.Read;
import irc.Write;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JvnProxy implements InvocationHandler {

	//JvnObject
	private JvnObject obj;

	public JvnProxy(JvnObject jo) {
		obj = jo;
	}

	public static Object newInstance(Serializable obj,JvnServerImpl js) {

		try {
			//
			JvnObject jo =  js.jvnLookupObject("IRC");
		   
			if (jo == null) {
				//jo = js.jvnCreateObject((Serializable) new Sentence());
				jo = js.jvnCreateObject(obj);
				//jo = (JvnObject) new JvnProxy(obj);
				// jo = (JvnProxy) JvnProxy.newInstance(new Sentence(),js);
				
				// after creation, I have a write lock on the object
				jo.jvnUnLock();
				
				//
				js.jvnRegisterObject("IRC", jo);				
			}		
			return java.lang.reflect.Proxy.newProxyInstance(
					obj.getClass().getClassLoader(),
					obj.getClass().getInterfaces(),
				new JvnProxy(jo));
		} catch (JvnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Object invoke(Object proxy, Method m, Object[] args)
	{
		try {
			//si la méthode est de type "Read": acquérir le verrou R
			if ( m.isAnnotationPresent(Read.class)) {
				System.out.println("read");
				obj.jvnLockRead();
			}
			//si la méthode est de type "Write": acquérir le verrou W
			if ( m.isAnnotationPresent(Write.class)) {
				System.out.println("write");
				obj.jvnLockWrite();
			}
			//appeler la méthode
			Object result = m.invoke(obj.jvnGetObject(), args);
			//Libérer le Lock
			obj.jvnUnLock();
			//
			System.out.println(m.getAnnotations());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}

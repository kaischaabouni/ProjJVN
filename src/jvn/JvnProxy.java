package jvn;

import irc.ItfSentence;
import irc.Read;
import irc.Write;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JvnProxy implements InvocationHandler {


	public JvnProxy(JvnObject jo) {
		obj = jo;
	}

	private JvnObject obj;
	
	public static Object newInstance(Serializable obj,JvnServerImpl js) {
		

		try {
			JvnObject jo =  js.jvnLookupObject("IRC");
		   
			if (jo == null) {
				//jo = js.jvnCreateObject((Serializable) new Sentence());
				jo = js.jvnCreateObject(obj);
				//jo = (JvnObject) new JvnProxy(obj);
				// jo = (JvnProxy) JvnProxy.newInstance(new Sentence(),js);
				// after creation, I have a write lock on the object
				//jo.jvnUnLock();
				jo.jvnUnLock();
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
		// TODO Auto-generated method stub
		try {
			if ( m.isAnnotationPresent(Read.class)) {
				System.out.println("read");
				obj.jvnLockRead();
			}
			if ( m.isAnnotationPresent(Write.class)) {
				System.out.println("write");
				obj.jvnLockWrite();
			}
			Object result = m.invoke(obj.jvnGetObject(), args);
			obj.jvnUnLock();
			
			System.out.println(m.getAnnotations());
			return result;
		//	return result;
		} catch (Exception e) {

		}
		return null;
		
	}

	

}

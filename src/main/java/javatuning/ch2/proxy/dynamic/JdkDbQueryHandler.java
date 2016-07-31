package javatuning.ch2.proxy.dynamic;

import javatuning.ch2.proxy.IDBQuery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkDbQueryHandler implements InvocationHandler {

    IDBQuery real = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (real == null)
            real = new DBQuery();
        //return real.request();
        if (proxy instanceof IDBQuery) {
            return method.invoke(real, args);
        } else {
            return null;
        }
    }

}

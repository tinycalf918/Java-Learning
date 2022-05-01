package io.kimmking.rpcfx.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class GenericService {
    private Object instance;

    public GenericService(Object instance) {
        this.instance = instance;
    }

    public Object $invoke(String methodName, Object[] params) throws InvocationTargetException, IllegalAccessException {
        Method method = Arrays.stream(instance.getClass().getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
        return method.invoke(instance, params);
    }
}

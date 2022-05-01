package io.kimmking.rpcfx.client;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class RpcClientProxyFactory<T> implements FactoryBean<T> {

    private Class<T> interfaceType;

    public RpcClientProxyFactory(Class<T> interfaceType){
        this.interfaceType = interfaceType;
    }

    @Override
    public T getObject() throws Exception {
        RpcClientProxy rpcClientProxy = new RpcClientProxy(interfaceType, "http://localhost:8080/");
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, rpcClientProxy);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

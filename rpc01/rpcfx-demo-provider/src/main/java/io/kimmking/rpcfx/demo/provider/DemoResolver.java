package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.server.GenericService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public GenericService resolve(String serviceClass) throws ClassNotFoundException {
        Class<?> interfaceType = Class.forName(serviceClass);
        Map<String, ?> beansOfType = applicationContext.getBeansOfType(interfaceType);
        System.out.println(beansOfType);
        Object defaultBean = beansOfType.values().stream().findFirst().get();
        return new GenericService(defaultBean);
    }
}

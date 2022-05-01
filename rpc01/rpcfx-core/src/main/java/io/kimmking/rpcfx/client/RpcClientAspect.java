package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Aspect
@Component
public class RpcClientAspect {

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");
    private final Filter[] filters = new Filter[]{};
    private final String url = "http://localhost:8080/";

    @Pointcut("execution(* io.kimmking.rpcfx.demo.consumer.TestService.*(..))")
    public void pointCut(){};

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        log.info("====hello");
        // 加filter地方之二
        // mock == true, new Student("hubao");

        RpcfxRequest request = new RpcfxRequest();
        String serviceName = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] params = proceedingJoinPoint.getArgs();
        request.setServiceClass(serviceName);
        request.setMethod(methodName);
        request.setParams(params);

        if (null!=filters) {
            for (Filter filter : filters) {
                if (!filter.filter(request)) {
                    return null;
                }
            }
        }

        RpcfxResponse response = null;
        try {
            response = post(request, url);
        } catch (IOException e) {
            e.printStackTrace();
            // 调用异常处理
        }

        // 加filter地方之三
        // Student.setTeacher("cuijing");

        // 这里判断response.status，处理异常
        // 考虑封装一个全局的RpcfxException

        return JSON.parse(response.getResult().toString());
    }

    private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson = client.newCall(request).execute().body().string();
        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }
}

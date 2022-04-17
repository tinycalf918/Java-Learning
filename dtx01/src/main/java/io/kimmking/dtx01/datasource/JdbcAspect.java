package io.kimmking.dtx01.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@Aspect
public class JdbcAspect {

    private static final Pattern readPattern =  Pattern.compile("^\\s*(select|get|query)");

    @Pointcut("execution(public * io.kimmking.dtx01.mapper.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint){
        Matcher matcher = readPattern.matcher(proceedingJoinPoint.getSignature().getName());
        if(matcher.find()){
            DatasourceHolder.setRead();
        }else{
            DatasourceHolder.setWrite();
        }
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        DatasourceHolder.clear();
    }
}

package ru.itis.javalab.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import ru.itis.javalab.aspect.servises.QueriesService;


@Aspect
@Component
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "ru.itis.javalab")
public class QueryOfUsersCounter {

    @Autowired
    QueriesService queriesService;

    @Around(value = "execution(* ru.itis.javalab.controllers.*.*(..))")
    public String addMethodToTable(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(joinPoint.getSignature());
        queriesService.IncreaseNumberOfUsing(joinPoint.getSignature().getName());
        String s = (String) joinPoint.proceed();
        return s ;
    }



}

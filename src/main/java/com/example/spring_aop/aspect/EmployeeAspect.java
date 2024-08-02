package com.example.spring_aop.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeAspect {

    @Before("execution(* com.example.spring_aop.service.EmployeeService.getEmployeeById(..))")  // point cup expression
    public void logBeforeMethod(JoinPoint joinPoint){

        List<Integer> args = Arrays.stream(joinPoint.getArgs()).map(n -> (int) n).toList();

        if(args.get(0) < 1 || args.get(0) > 100){
            throw new RuntimeException("Invalid id "+args.get(0));
        }

        System.out.println("Method : "+joinPoint.getSignature().getName() +" | attribute : "+args);
    }
}

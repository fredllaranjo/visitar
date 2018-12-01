
package com.gmail.fredllaranjo.visitarapi.core.aop.logging;

import java.util.Arrays;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Aspect for logging execution of service and repository Spring components.
 *
 * @author fredllaranjo
 */
@Component
@Aspect
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut that matches all modules but restapi.
     */
    @Pointcut("within(com.gmail.fredllaranjo.visitar.api..*) && !within(com.gmail.fredllaranjo.visitar.api.restapi..*)")
    public void loggingPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in
        // the advices.
    }

    /**
     * Advice that logs methods throwing exceptions.
     */
    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception {} in {}.{}() with message = \'{}\' and cause = \'{}\'", e.getClass().getName(),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getMessage(),
                e.getCause() != null ? e.getCause().toString() : "NULL");
        if (log.isTraceEnabled()) {
            log.error("Exception Stack Trace = {}", ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * Advice that logs when a method is entered and exited.
     */
    @Around("loggingPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }

        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Object result = joinPoint.proceed();
            String resultString = result == null ? "null" : result.toString();
            if (resultString.length() > 1500) {
                //avoid crashing the app due to very long result to log:
                resultString = resultString.substring(0, 999) + " ... [truncated]";
            }
            stopWatch.stop();

            if (log.isDebugEnabled()) {
                log.debug("Exit: {}.{}() with result = {} in {}ms", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), resultString, stopWatch.getTotalTimeMillis());
            }

            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

            throw e;
        }
    }

}

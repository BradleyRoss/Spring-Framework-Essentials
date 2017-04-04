package com.oreilly.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;
/**
 * Classes for AOP (Aspect Oriented Programming).
 * 
 * <p>For some reason, the following links when I enter the full URL, but not in a see annotation.  It looks like
 *    the work if I open in a new tab or a new window, but not if I attempt to open in the same window.  Problem was
 *    encountered with Safari on MacOS.  (If I turn off frames, everything works.)  I haven't seen a problem
 *    with other linked libraries, and it is possible that the problem is with the Javadoc plugin for Gradle.
 *    There were some problems with a previous JDK version.</p>
 * <ul>
 * <li><a href="http://www.eclipse.org/aspectj/doc/released/aspectj5rt-api/org/aspectj/lang/annotation/After.html"
 *     target="_blank">org.aspectj.lang/annotation/After.html</a></li>
 * <li><a href="http://www.eclipse.org/aspectj/doc/released/aspectj5rt-api/org/aspectj/lang/annotation/Around.html"
 *     target="_blank">org.aspectj.lang/annotation/Around.html</a></li>
 * <li><a href="http://www.eclipse.org/aspectj/doc/released/aspectj5rt-api/org/aspectj/lang/annotation/Aspect.html"
 *     target="_blank">org.aspectj.lang/annotation/Aspect.html</a></li>
 * <li><a href="http://www.eclipse.org/aspectj/doc/released/aspectj5rt-api/org/aspectj/lang/annotation/Before.html"
 *     target="_blank">org.aspectj.lang/annotation/Before.html</a></li>  
 * <li><a href="http://www.eclipse.org/aspectj/doc/released/aspectj5rt-api/org/aspectj/lang/annotation/Aspect.html?is-external=true"
 *     target="_blank">org.aspectj.lang/annotation/Aspect.html with <code>is-external=true</code>
 *     but opening a new tab.</a></li>    
 * <li><a href="http://www.eclipse.org/aspectj/doc/released/aspectj5rt-api/org/aspectj/lang/annotation/Aspect.html?is-external=true"
 *     >org.aspectj.lang/annotation/Aspect.html with <code>is-external=true</code> but same tab</a></li>
 * </ul>
 * 
 * 
 * @see Component
 * @see Aspect
 * @see Before
 * 
 * 
 */
@Aspect
@Component
public class LoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Before("execution(void com.oreilly..*.set*(*))")
    public void callSetters(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String arg = joinPoint.getArgs()[0].toString();
        logger.info("Called " + method + " with arg " + arg +
            " on " + joinPoint.getTarget());
    }
    /**
     * Allows running aspects before and after bean execution.
     * @param pjp Join Point for operation
     * @return Result of operation
     * @throws Throwable
     */
    @Around("execution(String playGame())")
    public Object checkForRain(ProceedingJoinPoint pjp) throws Throwable {
        boolean rain = Math.random() < 0.01;
        Object result = null;
        if (rain) {
            logger.info(pjp.getTarget() + " rained out");
        } else {
            result = pjp.proceed();
            logger.info(result.toString());
        }
        return result;
    }
}

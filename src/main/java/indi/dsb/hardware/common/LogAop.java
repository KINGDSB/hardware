package indi.dsb.hardware.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
public class LogAop {
    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Pointcut("@annotation(indi.dsb.hardware.common.Log)")
    public void controllerAspect() {

    }

    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {
        logger.error("请求失败：", e);
    }

    private Map<String, Object> getMethodDescription(JoinPoint joinPoint) {
        Map<String, Object> methodDesc = new HashMap<>();

        Class clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        methodDesc.put("className", clazz.getName());
        methodDesc.put("methodName", methodName);

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                Class[] parameterTypes = method.getParameterTypes();

                if (parameterTypes.length == args.length) {
                    Log log = method.getAnnotation(Log.class);
                    methodDesc.put("module", log.module());
                    methodDesc.put("method", log.method());
                }
            }
        }

        return methodDesc;
    }

    @Around("controllerAspect()")
    public Object aroundController(ProceedingJoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        // 请求的IP
        String ip = request.getRemoteAddr();

        long start = System.currentTimeMillis();
        logger.info("=====开始执行=====");
        logger.info("ip："+ip);
        Map<String, Object> methodDesc = getMethodDescription(joinPoint);
        logger.info("描述：" + methodDesc.get("method"));
        logger.info("请求:" + methodDesc.get("className") + "." + methodDesc.get("methodName"));

        Object result = null;
        try {
            result = joinPoint.proceed();
            logger.info("耗时：" + (System.currentTimeMillis() - start) + "ms");
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
        logger.info("=====执行结束=====");

        return result;
    }
}

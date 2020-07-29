/**
 * FileName: WebLogAcpect
 * Author:   jack.xue
 * Date:     2019/7/2 16:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * jack.xue           2019/7/2           1.0.0              描述
 */
package cn.bw.fitzboot.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Random;

/**
 * 〈TODO〉<br> 
 *
 * @author jack.xue
 * @create 2019/7/2
 * @since 1.0.0
 */
@Aspect
@Component
@Slf4j
public class WebLogAcpect {

    //@Pointcut("execution(public * cn.bw.fitzboot.controller..*.*(..))")
    //@Pointcut("execution(public * cn.bw.fitzboot.controller..*.queryMsg(..))")
    @Pointcut("execution(* cn.bw.fitzboot.controller..*.queryMsg(..))")
    public void webLog(){}

    /**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("doAfterReturning RESPONSE : " + ret);
    }

    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        System.out.println("方法环绕start.....");
        Boolean b = new Random().nextBoolean();
        try {
            if(b){
                Object o =  pjp.proceed();
                System.out.println("方法环绕proceed，结果是 :" + o);
                return o;
            }else{
                log.info("arround 阻断{} -> {}",pjp.getTarget().getClass().getName(),pjp.getSignature());
                return null;
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

}
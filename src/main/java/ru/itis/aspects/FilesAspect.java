package ru.itis.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import ru.itis.models.FileInfo;
import ru.itis.services.interfaces.EmailService;

@Component
@Aspect
@EnableAspectJAutoProxy
public class FilesAspect {

    @Autowired
    private EmailService emailService;

    @AfterReturning(pointcut = "pointcut()", returning = "fileInfo")
    public void sendEmail(JoinPoint joinPoint, FileInfo fileInfo){
        emailService.sendFileLinkMessage(fileInfo.getStorageFileName(), joinPoint.getArgs()[1].toString());
    }

    @Pointcut("execution(* ru.itis.services.FileInfoServiceImpl.save(..))")
    public void pointcut(){}
}

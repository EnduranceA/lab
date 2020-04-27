package ru.itis.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import ru.itis.models.Song;
import ru.itis.services.interfaces.EmailService;

@Component
@Aspect
@EnableAspectJAutoProxy
public class FilesAspect {

    @Autowired
    private EmailService emailService;

    @AfterReturning(pointcut = "pointcut()", returning = "song")
    public void sendEmail(Song song){
        emailService.sendFileLinkMessage(song.getStorageFileName(),song.getAuthor().getEmail());
    }

    @Pointcut("execution(* ru.itis.services.impl.mvc.SongServiceImpl.save(..))")
    public void pointcut(){}
}

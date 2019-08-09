package cn.blabla.community.advice;

import cn.blabla.community.exception.CustomizeErrorCode;
import cn.blabla.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handler(Throwable e, Model model) {

        if(e instanceof CustomizeException){
            model.addAttribute("message", e.getMessage());
        }else{
            model.addAttribute("message", CustomizeErrorCode.Question_NOT_FOUND);
        }
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }

}

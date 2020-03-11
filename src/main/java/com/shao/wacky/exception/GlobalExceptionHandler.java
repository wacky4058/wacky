package com.shao.wacky.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WackyException.class)
    @ResponseBody
    public Object handleException (HttpServletRequest request, WackyException e){
      Map<String,Object> map=new HashMap<>();
      map.put("url",request.getRequestURL().toString());
      map.put("msg",e.getMessage());
      return map;
  }

}

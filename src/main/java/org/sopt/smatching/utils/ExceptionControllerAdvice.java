package org.sopt.smatching.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

import static org.sopt.smatching.model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@ControllerAdvice // 모든 컨트롤러에 대응됨
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = Exception.class) // 모든 예외를 받음
    public ResponseEntity returnFailDefaultRes(HttpServletRequest req, Exception e) {
        log.error("--------------------------------------------\n\n-------MY EXCEPTION HANDER CATCHED!!!-------");
        log.error("- Method : " + req.getMethod());
        log.error("- URI : " + req.getRequestURI());
        log.error("- QueryString : " + req.getQueryString());

        Enumeration<String> stringEnumeration = req.getHeaderNames();
        StringBuffer headerStr = new StringBuffer();
        while(stringEnumeration.hasMoreElements()){
            String keyName = stringEnumeration.nextElement();
            headerStr.append(keyName);
            headerStr.append(" : ");
            headerStr.append(req.getHeader(keyName));
            headerStr.append("\n");
        }
        log.error("- Header (below)\n" + headerStr);

        log.error("- Exception Info (below)", e);

        log.error("--------------------------------------------\n\n");

        return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // 이미 컨트롤러에서 RequestBody 를 읽으며 InputStream을 썼기때문에 여기서 또 읽을 수 없음
    // RequestBody 확인하고 싶으면 위에 메소드 주석처리하고 이걸로 바꾸기.. 그러나 잘 안됨
//    @ModelAttribute
//    public void addAttributes(HttpServletRequest req, HttpServletResponse response, Model model, @RequestBody(required = false) String requestString, @RequestHeader(value = "User-Agent", required = false) String userAgent) {
//        // do whatever you want to do on the request body and header.
//        // with request object you can get the request method and request path etc.
//        System.out.println("requestString" + requestString);
//        System.out.println("userAgent" + userAgent);
//        model.addAttribute("attr1", "value1");
//        model.addAttribute("attr2", "value2");
//    }
}



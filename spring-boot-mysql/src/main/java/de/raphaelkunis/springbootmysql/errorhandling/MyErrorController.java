package de.raphaelkunis.springbootmysql.errorhandling;

import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController  {

    /* error page for browser in case of problems
    * see https://stackoverflow.com/questions/31134333/this-application-has-no-explicit-mapping-for-error
    */
    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }

    /* error page for browser in case of problems
    * see https://stackoverflow.com/questions/31134333/this-application-has-no-explicit-mapping-for-error
    */
    @RequestMapping("/demo/error")
    public String handleErrorDemo() {
        //do something like logging
        return "error demo";
    }    
}
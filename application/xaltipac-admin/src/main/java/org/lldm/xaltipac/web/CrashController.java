package org.lldm.xaltipac.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Juan Mateo.
 * Controller used to showcase what happens when an exception is thrown
 *
 *         Also see how the bean of type 'SimpleMappingExceptionResolver' has been declared inside
 *         /WEB-INF/mvc-core-config.xml
 */
@Controller
public class CrashController {

    @RequestMapping(value = "/oups", method = RequestMethod.GET)
    public String triggerException() {
        throw new RuntimeException("Expected: controller used to showcase what " +
                "happens when an exception is thrown");
    }
    
    
    @RequestMapping(value = "/notFound", method = RequestMethod.GET)
    public String notFound() {
    	return "notFound";
    }
    
    @RequestMapping(value = "/denegado", method = RequestMethod.GET)
    public String denegado() {
    	return "denegado";
    }
    

}

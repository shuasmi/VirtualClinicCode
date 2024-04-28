package com.mypack.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController{
	
	
	@RequestMapping("/error")
    public String handleError() {
        // Redirect to the custom 403 error page
        return "redirect:/saiclinic/home2";
		//return "error-403";
    }
	
  
	
	

}

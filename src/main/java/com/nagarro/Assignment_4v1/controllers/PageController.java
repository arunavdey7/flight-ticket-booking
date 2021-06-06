package com.nagarro.Assignment_4v1.controllers;

import java.util.Map;

import com.nagarro.Assignment_4v1.exceptions.ErrorInCSVFileException;
import com.nagarro.Assignment_4v1.daos.FlightSearchDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {


    @RequestMapping("/search")
    public String flightSearchPage(Map<String, Object> model)throws ErrorInCSVFileException
    {
        // model.put("message", this.message);
        FlightSearchDao fsd = new FlightSearchDao();
        fsd.getAllFiles();
        fsd.truncateTable();
        fsd.setEligibleFlights();
        return "FlightSearchOutput";
    }


    @RequestMapping("/homepage")
    public String homePage(Map<String, Object> model) {
        // model.put("message", this.message);
        return "HomePage";
    }
    @RequestMapping("/login")
    public String loginPage(Map<String, Object> model) {
        // model.put("message", this.message);
        return "LoginPage";
    }

    @RequestMapping("/signup")
    public String signupPage(Map<String, Object> model) {
        // model.put("message", this.message);
        return "SignupPage";
    }
    @RequestMapping("/frgtpswrd")
    public String forgotPassword(Map<String, Object> model) {
        // model.put("message", this.message);
        return "ForgotPassword";
    }
}
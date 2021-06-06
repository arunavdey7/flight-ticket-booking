package com.nagarro.Assignment_4v1.controllers;

import com.nagarro.Assignment_4v1.models.FlightData;
import com.nagarro.Assignment_4v1.daos.FlightSearchDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class FlightSearchController {

    List<FlightData> results;

    @PostMapping("/flight/output")
    public ModelAndView getResults(@RequestParam(name="dep_loc") String dep_loc,
                                       @RequestParam(name="arr_loc") String arr_loc,
                                       @RequestParam(name="flight_date") String flight_date,
                                       @RequestParam(name="flight_class") String flight_class,
                                       @RequestParam(name="output_preference") String output_preference,
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       ModelAndView modelAndView
                           ) throws IOException, ServletException {

//        System.out.println(dep_loc);
//        System.out.println(arr_loc);
//          System.out.println(flight_date);
//        System.out.println(flight_class);
//        System.out.println(output_preference);
        String temp[] = flight_date.split("-");
        flight_date = temp[2]+"-"+temp[1]+"-"+temp[0];
        //System.out.println(flight_date);
        FlightSearchDao fsd = new FlightSearchDao();

        results = fsd.readDataFromDB(dep_loc,arr_loc,flight_date,output_preference);
        //this.results = fsd.readDataFromDB("DEL","BGR","28-12-2013","fare only");
//        for(FlightData iter:results)
//        {
//            System.out.println(iter.getFlightNo());
//        }
        //System.out.println(this.results);

        //request.setAttribute("result_list",this.results);
        System.out.println("SENT "+results);
        modelAndView = new ModelAndView();
        modelAndView.addObject("result_list",results);
        modelAndView.addObject("flight_class",flight_class);
        modelAndView.setViewName("FlightSearchOutput");
        return modelAndView;
       //request.getRequestDispatcher("/search").include(request,response);




    }

    public List<FlightData> getList()
    {
        return this.results;
    }






}

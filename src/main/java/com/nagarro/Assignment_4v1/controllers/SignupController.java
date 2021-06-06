package com.nagarro.Assignment_4v1.controllers;


import com.nagarro.Assignment_4v1.models.User;
import com.nagarro.Assignment_4v1.daos.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SignupController {

    @PostMapping("/user/signup")
    @ResponseBody
    public void userSignup(@RequestParam("uname") String user_name ,
                          @RequestParam("pass") String pass_word,@RequestParam("cnf_pass") String cnf_password,
                          HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {

        try {
            UserDao user_dao = new UserDao();
            User user = new User();
            if(cnf_password.equals(pass_word)) {
                user.setUserName(user_name);
                user.setPassword(pass_word);
                user_dao.saveUser(user);

                response.setContentType("text/html");
                response.getWriter().print("<div>User Successfully Added</div>");
                request.getRequestDispatcher("/signup").include(request,response);

            }
            else
            {
                response.setContentType("text/html");
                response.getWriter().print("<div>Passwords do not match</div>");
                request.getRequestDispatcher("/signup").include(request,response);
            }


        }
        catch(Exception e )
        {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().print("<div>User Not Added</div>");
            request.getRequestDispatcher("/signup").include(request,response);

        }



    }
}
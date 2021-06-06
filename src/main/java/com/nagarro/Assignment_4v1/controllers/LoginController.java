package com.nagarro.Assignment_4v1.controllers;

import com.nagarro.Assignment_4v1.daos.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public void userLogin(@RequestParam("uname") String user_name ,
                          @RequestParam("pass") String pass_word,
                          HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {

        UserDao user = new UserDao();
        if(user.validate(user_name,pass_word))
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("login",user_name);
            response.sendRedirect("/search");
        }
        else
        {
            response.setContentType("text/html");
            response.getWriter().print("<div>User login failed</div>");
            request.getRequestDispatcher("/login").include(request,response);

        }


    }
}
package com.nagarro.Assignment_4v1.controllers;

import com.nagarro.Assignment_4v1.hibernateconfig.HibernateUtility;
import com.nagarro.Assignment_4v1.models.User;
import com.nagarro.Assignment_4v1.daos.UserDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ForgotPasswordController {

@PostMapping("/user/passwordchange")
@ResponseBody
public void forgotPassword(@RequestParam("uname") String user_name ,
                           @RequestParam("curr_pass") String pass_word,
                           @RequestParam("new_pass") String new_password,
                           @RequestParam("cnf_pass") String cnf_newpass,
                           HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException, ServletException {

        UserDao user_dao = new UserDao();
        User user = new User();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction tr = null;
        if(user_dao.validate(user_name,pass_word))
        {
                System.out.println("validation complete");
                tr = session.beginTransaction();
                user.setUserName(user_name);
                user.setPassword(new_password);

                try {

                        session.update(user);
                        tr.commit();
                        response.setContentType("text/html");
                        response.getWriter().print("<div>Password Changed Successfully</div>");
                        request.getRequestDispatcher("/frgtpswrd").include(request,response);

                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        tr.rollback();

                }

        }
        else
        {
                if(tr != null)
                {
                        tr.rollback();
                }
        response.setContentType("text/html");
        response.getWriter().print("<div>Failed to change the password</div>");
        request.getRequestDispatcher("/frgtpswrd").include(request,response);
        }


        }
}
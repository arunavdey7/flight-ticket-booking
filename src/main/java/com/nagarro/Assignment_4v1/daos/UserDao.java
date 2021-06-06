package com.nagarro.Assignment_4v1.daos;


import com.nagarro.Assignment_4v1.hibernateconfig.HibernateUtility;
import com.nagarro.Assignment_4v1.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDao {

    public boolean saveUser(User user) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtility.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the User object
            session.save(user);
            // commit transaction
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
    public boolean validate(String userName, String password) {

        Transaction transaction = null;
        User user = null;
        try  {
            Session session = HibernateUtility.getSessionFactory().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.user_name = :userName").setParameter("userName", userName)
                    .uniqueResult();
            //System.out.println(user);
            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

}
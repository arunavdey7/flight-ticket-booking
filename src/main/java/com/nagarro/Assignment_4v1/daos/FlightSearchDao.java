package com.nagarro.Assignment_4v1.daos;

import com.nagarro.Assignment_4v1.exceptions.ErrorInCSVFileException;
import com.nagarro.Assignment_4v1.hibernateconfig.HibernateUtility;
import com.nagarro.Assignment_4v1.models.FlightData;
import com.opencsv.CSVReader;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchDao {

    public static ArrayList<ArrayList<FlightData>> all_flight_data;

    public  ArrayList<String> getAllFiles()
    {
        System.out.println("Getting all the CSV files stored in the configured location");
        ArrayList<String> results = new ArrayList<String>();
        //location of the CSV files on my machine.
        File[] files = new File("C:/Users/arunavdey/Documents/Assignment Links/Assignment Links").listFiles();
        for (File file : files)
        {
            if (file.isFile())
            {
                results.add(file.getName());
            }
        }
        return results;
    }

    public void setEligibleFlights()throws ErrorInCSVFileException
    {
        System.out.println("Reading all the CSV files and inserting data into DB");
        try
        {

            ArrayList<String> all_file_names = getAllFiles();
            all_flight_data = new ArrayList<>();
            for(String iter:all_file_names) {
                ArrayList<FlightData> flight_record = new ArrayList<>();
                FileReader filereader = new FileReader("C:/Users/arunavdey/Documents/Assignment Links/Assignment Links/" + iter);
                CSVReader csvReader = new CSVReader(filereader);
                String[] nextRecord = csvReader.readNext();
                while ((nextRecord = csvReader.readNext()) != null) {
                    String[] temp = String.valueOf(nextRecord[0]).split("\\|");
                    //if any data is missing in CSV then raise an error
                    if (temp.length != 9) {
                        throw new ErrorInCSVFileException("Some data is missing in the entries of the CSV File name : " + iter);
                    }
                    //If Arrival Location or Departure Location is not present then raise an error
                    if (temp[1].equals(" ") || temp[2].equals(" ")) {
                        throw new ErrorInCSVFileException("Arrival Location/Departure Location is absent in the CSV File name : " + iter);
                    }

                    //Inserting the values into the table (flight_record)
                    insertDataIntoDB(temp[0], temp[1], temp[2], temp[3], temp[4], Double.parseDouble(temp[5]), Integer.parseInt(temp[6]), temp[7], temp[8]);
                }


            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Table Read,Write and Truncate Methods

    private static void insertDataIntoDB(String flight_no,String dep_loc,String arr_loc,String valid_till,
                                        String flight_time,Double flight_dur,int fare,String seat_availability,String passenger_class)
    {
        FlightData fd_object  = new FlightData();
        try {

            Session session = HibernateUtility.getSessionFactory().openSession();
            // start a transaction
            session.beginTransaction();
            // save the User object



            //session.beginTransaction();

            fd_object.setFlightNo(flight_no);
            fd_object.setDepLoc(dep_loc);
            fd_object.setArrLoc(arr_loc);
            fd_object.setValidTill(valid_till);
            fd_object.setFlightTime(flight_time);
            fd_object.setFlightDur(flight_dur);
            fd_object.setFare(fare);
            fd_object.setSeatAvailability(seat_availability);
            fd_object.setPassengerClass(passenger_class);

            session.save(fd_object);

            session.getTransaction().commit();

        }
        catch(Exception e)
        {

            e.printStackTrace();
        }
    }

    public List<FlightData> readDataFromDB(String dep_loc, String arr_loc, String valid_till, String output_preference)
    {
        //System.out.println("Read from DB Error");
        System.out.println("Reading Data from the DB");
        String hql;
        Session session = HibernateUtility.getSessionFactory().openSession();
        if(output_preference.equals("fare only"))
        {
            hql = "FROM FlightData WHERE dep_loc='"+dep_loc+"' AND arr_loc='"+arr_loc+"' AND valid_till='"+valid_till+"' ORDER BY fare ASC";
        }
        else
        {
            hql = "FROM FlightData WHERE dep_loc='"+dep_loc+"' AND arr_loc='"+arr_loc+"' AND valid_till='"+valid_till+"' ORDER BY fare,flight_dur ASC";
        }
        session.beginTransaction();
        Query query = session.createQuery(hql);
        List results = query.list();
        session.getTransaction().commit();
        return results;
    }

    public void truncateTable()
    {
        System.out.println("Removing all the entries from DB");
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            String hql = "TRUNCATE flight_record";
            session.createSQLQuery(hql).executeUpdate();
            session.getTransaction().commit();
            // session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}



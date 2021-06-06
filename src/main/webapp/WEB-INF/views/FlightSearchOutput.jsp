<%@ page import="com.nagarro.Assignment_4v1.controllers.FlightSearchController" %>
<%@ page import="com.nagarro.Assignment_4v1.models.FlightData" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Flight Search</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <style>
        .heading1
        {
            text-align: center;
            font-weight: bold;
            font-size: 50px;
            border-style: solid;
            border-width: 1px 1px 1px 1px;
            background-color: lightblue;
        }
        .file_browser
        {
            border-style: solid;
            border-width: 1px 1px 0px 1px;
            padding: 50px 50px 50px 50px;
        }
        td
        {
            padding: 10px 10px 10px 10px;
            font-weight: bold;
            font-size: large;
        }
        .table_headers
        {
            display: flex;
            flex-direction: row;
            /*justify-content: space-around;*/
            border-style: solid;
            border-width: 1px 1px 1px 1px;
            font-weight: bold;
        }
        .main_headers
        {
            border-style: solid;
            border-width: 1px 1px 1px 1px;
            padding: 2px 2px 2px 2px;
            text-align: center;
            width: 150px;
            background-color: lightblue;
        }
        .row_element
        {
            border-style: solid;
            border-width: 1px 1px 1px 1px;
            padding: 2px 2px 2px 2px;
            text-align: center;
            width: 150px;
        }
       
        .table_data
        {
            display: flex;
            flex-direction: row;
            /*justify-content: space-around;*/
            border-style: solid;
            border-width: 1px 1px 1px 1px;

        }
       
        .table_container
        {
            overflow:auto;
        }
        .heading2
        {
            font-weight: bold;
            font-size: 50px;
            position:relative;
            /* left: 20px; */
            background-color:lightgreen;
            text-align: center;
        }
        .file_browser
        {
            background-color: lightgoldenrodyellow;
        }
        .clr_btn
        {
            position:relative;
            left: -230px;
        }
        .er
        {
            padding: 10px 10px 10px 10px;
            background-color: red;
            font-size: large;
            color: white;
        }
        
    </style>
</head>
<body>
<div class="heading1">Flight Search Utility</div>
<form action="/flight/output" METHOD="POST" onsubmit="return checkEntries()">
    <div class="file_browser">
        <table>
            <tr>
                <td>
                    <label>Departure Location</label>
                </td>
                <td>
                    <input id="dl" name="dep_loc" type="text">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Arrival Location</label>
                </td>
                <td>
                    <input id="al" name="arr_loc" type="text">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Flight Date</label>
                </td>
                <td>
                    <input id="fd" name="flight_date" type="date">
                </td>
            </tr>
            <tr>
                <td>
                    <label>Flight Class</label>
                </td>
                <td>
                    <input type="radio" name="flight_class" id="business" value="B">
                    <label for="both">Business Class</label>
                    <input type="radio" name="flight_class" id="economy" value="E">
                    <label for="both">Economy Class</label><br>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Output Preference</label>
                    
                </td>
                <td>
                    <input type="radio" name="output_preference" id="fare" value="fare Only">
                    <label for="fare">Fare only</label>
                    <input type="radio" name="output_preference" id="both" value="both">
                    <label for="both">Both</label><br>
                </td>
                
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Search">
                </td>
                <td>
                    <input class="clr_btn" type="reset" value="Clear">
                </td>
            </tr>

        </table>
    </div>
    <%
        List<FlightData> ls = (List<FlightData>) request.getAttribute("result_list");
        System.out.println("RECEIVED "+ls);
        String flight_class = (String) request.getAttribute("flight_class");
        String output_price;
        String final_class;
        String final_dur;
        String seat_avl;
        String final_time;
    %>
</form>

<div class="heading2">Available flights</div>

<div class="table_container">
    <div class="table_headers">
        <div class="main_headers">Flight No.</div>
        <div class="main_headers">Departure Location</div>
        <div class="main_headers">Arrival Location</div>
        <div class="main_headers">Valid Till</div>
        <div class="main_headers">Flight Time</div>
        <div class="main_headers">Flight Duration</div>
        <div class="main_headers">Fare</div>
        <div class="main_headers">Seat Availability</div>
        <div class="main_headers">Flight Class</div>
    </div>

      <%if(ls != null && ls.size() != 0)
      {
          for(FlightData iter:ls)
          {
      %>
    <div class="table_row">
        <div class="table_data">
            <div class="row_element"><%=iter.getFlightNo()%></div>
            <div class="row_element"><%=iter.getDepLoc()%></div>
            <div class="row_element"><%=iter.getArrLoc()%></div>
            <div class="row_element"><%=iter.getValidTill()%></div>
            <%
                String ft= iter.getFlightTime();
                String temp1 = ft.substring(0,2)+":"+ft.substring(2);
                final_time = temp1;
            %>
            <div class="row_element"><%=final_time%></div>
            <%
                double fd =  iter.getFlightDur();
                int hrs = (int)fd;
                double min = fd-hrs;
                String temp = String.format("%.2f",min);
                String mins = temp.substring(temp.indexOf(".")+1);
                final_dur = hrs+" hrs "+mins+" min";
            %>
            <div class="row_element"><%=final_dur%></div>
            <%if(flight_class.equals("B"))
            {
                Double final_price = iter.getFare()*1.4;
                output_price ="Rs." + String.format("%.2f",final_price);
            }
            else
            {
                output_price ="Rs." + String.valueOf(iter.getFare());
            }
            %>
            <div class="row_element"><%=output_price%></div>
            <%
                if(iter.getSeatAvailability().equals("Y"))
                {
                    seat_avl = "Available";
                }
                else
                {
                    seat_avl = "Not Available";
                }
            %>
            <div class="row_element"><%=seat_avl%></div>
            <%
                if(iter.getPassengerClass().equals("B"))
                {
                    final_class = "Business";
                }
                else
                {
                    final_class = "Economy";
                }
            %>
            <div class="row_element"><%=final_class%></div>
        </div>
    </div>

    <%}
    }
    else if(ls != null)
    {
    %>
    <div class="er">No Flights available for the provided criteria</div>
    <%
    }
    %>

</div>

</body>
<script>
    //blank entries validation
    function checkEntries()
    {

        let dl = document.getElementById("dl").value;
        let al = document.getElementById("al").value;
        let fd = document.getElementById("fd").value;
        let eco = document.getElementsByName("flight_class")[1].checked;
        let bui = document.getElementsByName("flight_class")[0].checked;
        let fare = document.getElementsByName("output_preference")[0].checked;
        let both = document.getElementsByName("output_preference")[1].checked;
        if (dl == "" || al == "" || fd == "" || (eco == false && bui == false) || (fare == false && both == false)) {
            alert("Please fill all the entries !");
            return false;
            //window.location.replace("http://localhost:8888/search");
        }
        if((dl.length > 3) || (al.length > 3))
        {
            alert("Departure/Arrival location code cannot exceed 3 characters !");
            return false;
        }
        return true;

    }


    //console.log(fd);
</script>
</html>

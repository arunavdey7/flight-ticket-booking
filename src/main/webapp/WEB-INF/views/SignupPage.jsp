<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">
    <link rel="stylesheet"
          href="design.css">
    </link>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <style type="text/css">
        .error_message
        {
            padding:20px;

            background-color:red;
            color:white;
            font-size:32px;

        }
        .main_container
        {
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            /*background-color:lightcoral;*/

        }
        .header_and_footer
        {
            padding: 5px 5px 5px 5px;
            background-color: lightblue;
            font-weight: bold;
            font-size: 40px;
            font-family:'Franklin Gothic Medium', Arial, sans-serif
            /*margin: 5px 5px 5px 5px;*/
        }
        .content_section
        {
            padding: 40px 40px 40px 40px;
        }
        .footer
        {
            padding: 15px 15px 15px 15px;
            background-color: lightblue;

        }
        .login_button
        {
            position: relative;
            left: 1060px;
            bottom: 46px;
        }
        td
        {
            padding: 20px 20px 20px 20px;
        }
        .requireds
        {
            color: red;
        }

        .signup_button
        {
            position:relative;
            bottom: 2px;
            left:1150px;
        }
    </style>
</head>
<body>
<div class="main_container">
    <div class="header_and_footer">Sign Up</div>
    <form class="login_page" action="/user/signup" method="POST">
        <div class="content_section">

            <table>
                <tr>
                    <td>
                        <Label>Username</Label>
                    </td>
                    <td>
                        <label class="requireds">*</label>
                        <input name="uname" type="text">
                    </td>
                </tr>
                <tr>
                    <td>
                        <Label>Password</Label>
                    </td>
                    <td>
                        <label class="requireds">*</label>
                        <input name="pass" type="password">
                    </td>
                </tr>
                <tr>
                    <td>
                        <Label>Confirm Password</Label>
                    </td>
                    <td>
                        <label class="requireds">*</label>
                        <input name="cnf_pass" type="password">
                    </td>
                </tr>
            </table>

        </div>
        <div class="footer">

            <input class="signup_button" type="submit" value="Sign Up">




        </div>
    </form>
    <form action="/login">
    <input class="login_button" type="submit" value="<<Login">
    </form>>

</div>
</body>
</html>

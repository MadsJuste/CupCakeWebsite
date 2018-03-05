<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body background="cupcakes.jpg" position:absolute>
        <table width="100%" >
            <tr>
                <td></td>
                <td width="800px" style="padding: 15px; height: 100%; background-color:whitesmoke">                
                    <table class="pure-table">
                        <form class="pure-form pure-form-stacked" method="post" action="registration.jsp">
                            <fieldset>
                                <legend></legend>
                                <label for="Username">Username</label>
                                <input type="text" name="uname" class="pure-u-1-4">

                                <label for="Password">Password</label>
                                <input type="text" name="pass" class="pure-u-1-4">

                                <button type="submit" class="pure-button pure-button-primary">Submit</button>
                                <tr>
                                    <td colspan="2">Are you already registered? <a href="index.jsp">Click here</a> to login</td>
                                </tr>
                            </fieldset>
                        </form>
                    </table>
                </td>
                <td></td>
            </tr>
        </table>
    </body>
</html>

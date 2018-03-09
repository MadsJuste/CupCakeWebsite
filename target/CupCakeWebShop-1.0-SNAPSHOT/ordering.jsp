
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="ordering.css" rel="stylesheet" type="text/css"/>
        <title>Cupcake Choice</title>
    </head>
    <body>
        <form action="Control" method="post"> 
            <select name ="bottom" >
                <option value="1" selected>Chocolate 5.00kr</option>
                <option value="2">Vanilla 5.00kr</option>
                <option value="3">Nutmeg 5.00kr</option>
                <option value="4">Pistacio 6.00kr</option>
                <option value="5">Almond 7.00kr</option>
            </select>

            <br>

            <select name ="topping">
                <option value="1" selected>Chocolate 5.00kr</option>
                <option value="2">Blueberry 5.00kr</option>
                <option value="3">Rasberry 5.00kr</option>
                <option value="4">Crispy 6.00kr</option>
                <option value="5">Strawberry 6.00kr</option>
                <option value="6">Rum/Raisin 7.00kr</option>
                <option value="7">Orange 8.00kr</option>
                <option value="8">Lemon 8.00kr</option>
                <option value="9">Blue Cheese 9.00kr</option>
            </select>
            <input type="number" name="amount" placeholder="Enter amount..."/>
            <br>
            <input type="hidden" name="origin" value="placeOrder" />
            <input type="submit" value="Submit Cupcake" />

        </form> 

        <form action="Control" method="post">
            <input type="hidden" name="origin" value="seeOrders" />
            <input type="submit" value="Look at orders" />
        </form> 
    </body>
</html>

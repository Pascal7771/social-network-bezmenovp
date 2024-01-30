<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        form {
            width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        label {
            display: inline-block;
            width: 30%;
            margin-bottom: 10px;
        }

        input[type="text"],
        input[type="date"],
        input[type="file"] {
            width: 60%;
            padding: 5px;
            margin-bottom: 10px;
            border-radius: 3px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            background-color: #4285F4;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #3367D6;
        }
    </style>
</head>

<body>
<form action="create-account" method="post" enctype="multipart/form-data">
    <b>Fill in the fields for registration</b>
    <br>
    <br>
    <label for="accountSurname">Surname:</label>
    <input type="text" required name="accountSurname">
    <br>
    <label for="accountName">Name:</label>
    <input type="text" required name="accountName">
    <br>
    <label for="accountPatronymic">Patronymic:</label>
    <input type="text" required name="accountPatronymic">
    <br>
    <label for="accountBirthday">Birth Day:</label>
    <input type="date" required name="accountBirthday">
    <br>
    <label for="accountPhone">Phone:</label>
    <input type="text" name="accountPhone">
    <br>
    <label for="accountWorkPhone">Work Phone:</label>
    <input type="text" name="accountWorkPhone">
    <br>
    <label for="accountHomeAddress">Address:</label>
    <input type="text" name="accountHomeAddress">
    <br>
    <label for="accountBusinessAddress">Work Address:</label>
    <input type="text" name="accountBusinessAddress">
    <br>
    <label for="accountEmail">Email:</label>
    <input type="text" required name="accountEmail">
    <br>
    <label for="accountICQ">ICQ:</label>
    <input type="text" name="accountICQ">
    <br>
    <label for="accountSkype">Skype:</label>
    <input type="text" name="accountSkype">
    <br>
    <label for="accountAdditionalInformation">Additionally:</label>
    <input type="text" name="accountAdditionalInformation">
    <br>
    <label for="accountImage">Photo:</label>
    <input type="file" name="accountImage">
    <br>
    <label for="accountPassword">Password:</label>
    <input type="text" required name="accountPassword">
    <br>
    <br>
    <input type="submit" value="Create">
</form>
</body>
</html>

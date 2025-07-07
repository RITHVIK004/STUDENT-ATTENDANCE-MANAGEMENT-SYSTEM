<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Login - Attendance System</title>
  <link rel="stylesheet" href="<c:url value='/style.css' />" />
  <style>
    body {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      background: linear-gradient(to right, #2c3e50, #3498db);
      margin: 0;
    }
    .login-box {
      background: white;
      padding: 40px;
      border-radius: 12px;
      box-shadow: 0 6px 20px rgba(0, 0, 0, 0.2);
      text-align: center;
      width: 350px;
    }
    .login-box h2 {
      margin-bottom: 20px;
      color: #2c3e50;
    }
    .login-box input[type="text"],
    .login-box input[type="password"] {
      width: 100%;
      padding: 12px;
      margin: 10px 0;
      border-radius: 6px;
      border: 1px solid #ccc;
    }
    .login-box button {
      padding: 12px;
      width: 107%;
      border: none;
      background: #3498db;
      color: white;
      font-weight: bold;
      border-radius: 6px;
      cursor: pointer;
      margin-top: 10px;
    }
    .login-box button:hover {
      background: #2980b9;
    }
    .error {
      color: red;
      margin-top: 10px;
      font-size: 0.9em;
    }
  </style>
</head>
<body>
  <div class="login-box">
    <h2>Login</h2>
    <input type="text" id="username" placeholder="Enter username" />
    <input type="password" id="password" placeholder="Enter password" />
    <button onclick="login()">Login</button>
    <div id="error" class="error"></div>
  </div>

  <script>
    function login() {
      const username = document.getElementById("username").value;
      const password = document.getElementById("password").value;
      const errorDiv = document.getElementById("error");

      // ✅ Hardcoded credentials
      const validUsername = "admin";
      const validPassword = "admin123";

      if (username === validUsername && password === validPassword) {
    	  window.location.href = "/index";
      } else {
        errorDiv.textContent = "Invalid username or password!";
      }
    }
  </script>
</body>
</html>

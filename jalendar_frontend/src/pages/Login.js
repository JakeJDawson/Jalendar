// Import statements.
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";

function Login({onLogin}) {
    // Set up the navigate ability to get to other pages.
    const navigate = useNavigate();
    // Set up variables to hold and functions to set email and password.
    const[email, setEmail] = useState("");
    const[password, setPassword] = useState("");

    // The case which actually handles the login.
    const handleLogin = (e) => {
        e.preventDefault();

        fetch("http://localhost:8080/api/users/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email,
                password
            })
        })
            // Look for an error to display.
            .then(async(res) => {
                if(!res.ok) {
                    const text = await res.text();
                    throw new Error(text);
                }
                // Debugging line
                //console.log("Response received:", res);
                return res.json();
            })
            // Go to the tasks page on successful login.
            .then(data => {onLogin(data.id); navigate("/tasks");})
            // Display an error if there is one.
            .catch(err => {console.error("Login failed:", err.message);});
    };

    // The HTML display of the login page.
    return(
        <div>
            <h1>Login</h1>

            <form onSubmit={handleLogin}>
                <input
                    type = "text"
                    placeholder = "Email"
                    value = {email}
                    onChange = {(e) => setEmail(e.target.value)}
                />

                <input
                    type = "password"
                    placeholder = "Password"
                    value = {password}
                    onChange = {(e) => setPassword(e.target.value)}
                />

                <button type="submit" onClick={handleLogin}>Login</button>
            </form>
        </div>
    );
}

export default Login;
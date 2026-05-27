// Import statements.
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";

function Register() {
    // Set up the navigate ability to get to other pages.
    const navigate = useNavigate();
    // Set up variables to hold and functions to set email and password.
    const[email, setEmail] = useState("");
    const[password, setPassword] = useState("");

    // The case which actually handles the registration.
    const handleRegister = (e) => {
        e.preventDefault();

        fetch("http://localhost:8080/api/users/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email,
                password
            })
        })
            // Go to the login page on successful registration.
            .then(() => {navigate("/");});
    };

    // The HTML display of the registration page.
    return(
        <div>
            <h1>Register</h1>

            <form onSubmit={handleRegister}>
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

                <button type="submit">Register</button>
            </form>
        </div>
    );
}

export default Register;
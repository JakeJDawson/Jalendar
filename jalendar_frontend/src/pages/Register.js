// Import statements.
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {registerUser} from "../services/userService"

function Register() {
    // Set up the navigate ability to get to other pages.
    const navigate = useNavigate();
    // Set up variables to hold and functions to set email and password.
    const[email, setEmail] = useState("");
    const[password, setPassword] = useState("");

    // The case which actually handles the registration.
    const handleRegister = (e) => {
        e.preventDefault();

        registerUser(email, password)
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
// Import statements.
import {useEffect, useState} from "react";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Tasks from "./pages/Tasks";
import Login from "./pages/Login";
import Register from "./pages/Register";
import ForceLogin from "./components/ForceLogin";

// Main application.
function App() {
    // Used to hold the current login token.
    const token = localStorage.getItem("token");

    // Simple function to handle logging out.
    const handleLogout = () => {
        //setUserID(null);
        localStorage.removeItem("token");
    };

    return(
        <Router>
            <Routes>
                <Route
                    path="/"
                    element={<Login/>}
                />

                <Route
                    path="/register"
                    element={<Register/>}
                />

                <Route
                    path="/tasks"
                    element={
                        <ForceLogin>
                            <Tasks
                                handleLogout={handleLogout}
                            />
                        </ForceLogin>
                    }
                />
            </Routes>
        </Router>
    );
}

export default App;

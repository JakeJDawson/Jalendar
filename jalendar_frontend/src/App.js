// Import statements.
import {useEffect, useState} from "react";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Tasks from "./pages/Tasks";
import Login from "./pages/Login";
import Register from "./pages/Register";
import ForceLogin from "./components/ForceLogin";

// Main application.
function App() {
    // Used to hold, as well as update, the currently logged in user.
    const[userID, setUserID] = useState(() => {
        return localStorage.getItem("userID");
    });

    // Simple function to handle logging out.
    const handleLogout = () => {
        setUserID(null);
        localStorage.removeItem("userID");
    };

    return(
        <Router>
            <Routes>
                <Route
                    path="/"
                    element={<Login onLogin={setUserID}/>}
                />

                <Route
                    path="/register"
                    element={<Register/>}
                />

                <Route
                    path="/tasks"
                    element={
                        <ForceLogin userID={userID}>
                            <Tasks
                                userID={userID}
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

// Import statements.
import{useEffect, useState} from "react";
import{BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Tasks from "./pages/Tasks";
import Login from "./pages/Login";
import Register from "./pages/Register";

// Main application.
function App() {
    // Used to hold, as well as update, the currently logged in user.
    const[userID, setUserID] = useState(null);

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
                    element={<Tasks userID={userID}/>}
                />
            </Routes>
        </Router>
    );
}

export default App;

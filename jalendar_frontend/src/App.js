// Import statements.
import{useEffect, useState} from "react";
import{BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Tasks from "./pages/Tasks";
import Login from "./pages/Login";

// Main application.
function App() {
    // Used to hold, as well as update, the currently logged in user.
    const[userID, setUserID] = useState(null);
    // Used to display, as well as update, a list of the user's tasks.
    const[tasks, setTasks] = useState([]);
    // Used to hold the title and description of a new task, and update them.
    const[title, setTitle] = useState("");
    const[description, setDescription] = useState("");

    // Currently used to determine whether the user is updating a task or not.
    const[editingTaskID, setEditingTaskID] = useState(null);

    useEffect(() => {
        fetch("http://localhost:8080/api/tasks?userID=1")
            .then(res => res.json())
            .then(data => {
                setTasks(data)
            });
    }, []);

    // Function that handles the creation AND modification of tasks.
    const handleSubmit = (e) => {
        e.preventDefault();

        // Will change later, currently hardcoded to user 1.
        const userID = 1;

        /** 
         * If the user is editing a task, this if condition runs, otherwise the
         * else runs.
         */
        if(editingTaskID) {
            // Update the task in question.
            fetch(`http://localhost:8080/api/tasks/
                ${editingTaskID}?userID=${userID}`, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        title: title,
                        description: description
                    })
                }).then(() => {
                    // Stop editing a task, return the input fields to blank.
                    setEditingTaskID(null);
                    setTitle("");
                    setDescription("");

                    // Refresh the displayed list of tasks.
                    fetch(`http://localhost:8080/api/tasks?userID=${userID}`)
                        .then(res => res.json())
                        .then(data => setTasks(data));
                });
        } else {
            // Add the task entered into the input fields.
            fetch(`http://localhost:8080/api/tasks?userID=${userID}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    title: title,
                    description: description
                })
            }).then(() => {
                // Return the input fields to blank.
                setTitle("");
                setDescription("");

                // Refresh the displayed list of tasks.
                fetch(`http://localhost:8080/api/tasks?userID=${userID}`)
                    .then(res => res.json())
                    .then(data => setTasks(data));
            });
        }
    };

    // Function to handle the deletion of tasks.
    const handleDelete = (taskID) => {
        // Will change later, currently user is hardcoded to user 1.
        const userID = 1;

        // Delete the task from the database.
        fetch(`http://localhost:8080/api/tasks/${taskID}`, {
            method: "DELETE"
        }).then(() => {
            // Refresh the displayed tasks.
            fetch(`http://localhost:8080/api/tasks?userID=${userID}`)
                .then(res => res.json())
                .then(data => setTasks(data));
        });
    };

    // Function which handles what the "edit" button does.
    const handleEdit = (task) => {
        /** 
         * Set the input fields to be the current title and description of the
         * task being edited.
         */
        setTitle(task.title);
        setDescription(task.description);

        /** 
         * Tell the program that we are now editing a task, rather than making
         * a new one.
         */
        setEditingTaskID(task.id);
    };

    return(
        <Router>
            <Routes>
                <Route
                    path="/"
                    element={<Login onLogin={setUserID}/>}
                />

                <Route
                    path="/tasks"
                    element={
                        <Tasks
                            tasks={tasks}
                            title={title}
                            description={description}
                            setTitle={setTitle}
                            setDescription={setDescription}
                            handleSubmit={handleSubmit}
                            handleDelete={handleDelete}
                            handleEdit={handleEdit}
                            editingTaskID={editingTaskID}
                        />
                    }
                />
            </Routes>
        </Router>
    );
}

export default App;

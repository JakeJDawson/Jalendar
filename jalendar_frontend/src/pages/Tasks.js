// Import statements.
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {getTasks, createTask, updateTask, deleteTask}
    from "../services/taskService";

function Tasks({handleLogout}) {
    // Set up the navigate ability to get to other pages.
    const navigate = useNavigate();
    // Used to display, as well as update, a list of the user's tasks.
    const[tasks, setTasks] = useState([]);
    // Used to hold the title and description of a new task, and update them.
    const[title, setTitle] = useState("");
    const[description, setDescription] = useState("");

    // Currently used to determine whether the user is updating a task or not.
    const[editingTaskID, setEditingTaskID] = useState(null);

    // Updates the tasks to get them to show initially.
    useEffect(() => {
        getTasks().then(setTasks);
    }, []);

    // Function to slightly modify handleLogout.
    const logout = () => {
        // To clean the user state.
        handleLogout();
        // To redirect to login.
        navigate("/");
    }

    // Function that handles the creation AND modification of tasks.
    const handleSubmit = (e) => {
        e.preventDefault();

        // Debugging line to assume user 1.
        //const userID = 1;

        /** 
         * If the user is editing a task, this if condition runs, otherwise the
         * else runs.
         */
        if(editingTaskID) {
            // Update the task in question.
            updateTask(editingTaskID, {
                title,
                description
            }).then(() => {
                // Stop editing a task, return the input fields to blank.
                setEditingTaskID(null);
                setTitle("");
                setDescription("");

                // Refresh the displayed list of tasks.
                return getTasks();
            }).then(setTasks);
        } else {
            // Add the task entered into the input fields.
            createTask({
                title,
                description
            }).then(() => {
                // Return the input fields to blank.
                setTitle("");
                setDescription("");

                // Refresh the displayed list of tasks.
                return getTasks();
            }).then(setTasks);
        }
    };

    // Function to handle the deletion of tasks.
    const handleDelete = (taskID) => {
        // Debugging line to assume user 1.
        //const userID = 1;

        // Delete the task from the database.
        deleteTask(taskID)
            // Refresh the displayed tasks.
            .then(() => getTasks())
            .then(setTasks);
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

    return (
        <div>
            {/* Simply displays the title of the page. */}
            <h1>Jalendar</h1>

            {/* Logout button. It logs you out. */}
            <button onClick={logout}>Log out</button>

            {/* The input fields and button used for creating new tasks, as
                well as updating currently existing ones. */}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />

                <input
                    type="text"
                    placeholder="Description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                />

                {/* The submit button displays different text, depending on
                    whether the application is currently updating a task, or
                    adding a new one. */}
                <button type="submit">
                    {editingTaskID ? "Update Task" : "Add Task"}
                </button>
            </form>

            {/* Creates the displayed task list. Each task gets its title and
                description shown, as well as a delete and an edit button. */}
            <ul>
                {tasks.map(task => (
                    <li key={task.id}>
                        {task.title} - {task.description}

                        <button onClick={() => handleDelete(task.id)}>
                            Delete
                        </button>

                        <button onClick={() => handleEdit(task)}>
                            Edit
                        </button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Tasks;
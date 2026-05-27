// Set a URL for the service to use.
const BASE_URL = "http://localhost:8080/api/tasks";

// Get tasks for a user.
export const getTasks = (userID) => {
    return fetch(`${BASE_URL}?userID=${userID}`)
        .then(res => res.json());
};

// Create task.
export const createTask = (userID, task) => {
    return fetch(`${BASE_URL}?userID=${userID}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(task)
    });
};

// Update task.
export const updateTask = (taskID, userID, task) => {
    return fetch(`${BASE_URL}/${taskID}?userID=${userID}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(task)
    });
};

// Delete task.
export const deleteTask = (taskID) => {
    return fetch(`${BASE_URL}/${taskID}`, {
        method: "DELETE"
    });
};
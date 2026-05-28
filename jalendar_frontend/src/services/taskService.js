// Import the api for ease of use.
import { api } from "./api";

// Get tasks.
export const getTasks = () => {
    return api.get("/tasks");
};

// Create task.
export const createTask = (task) => {
    return api.post("/tasks", task);
};

// Update task.
export const updateTask = (taskID, task) => {
    return api.put(`/tasks/${taskID}`, task);
};

// Delete task.
export const deleteTask = (taskID) => {
    return api.delete(`/tasks/${taskID}`);
};
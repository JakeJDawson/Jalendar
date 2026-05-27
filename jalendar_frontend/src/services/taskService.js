// Import the api for ease of use.
import { api } from "./api";

// Get tasks.
export const getTasks = (userID) => {
    return api.get(`/tasks?userID=${userID}`);
};

// Create task.
export const createTask = (userID, task) => {
    return api.post(`/tasks?userID=${userID}`, task);
};

// Update task.
export const updateTask = (taskID, userID, task) => {
    return api.put(`/tasks/${taskID}?userID=${userID}`, task);
};

// Delete task.
export const deleteTask = (taskID) => {
    return api.delete(`/tasks/${taskID}`);
};
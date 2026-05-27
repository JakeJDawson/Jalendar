// Import the api for ease of use.
import { api } from "./api";

// Register.
export const registerUser = (email, password) => {
    return api.post("/users/register", {email, password});
};

// Login.
export const loginUser = (email, password) => {
    return api.post("/users/login", {email, password});
};
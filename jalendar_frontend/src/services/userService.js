// Set a URL for the service to use.
const BASE_URL = "http://localhost:8080/api/users";

// Register user.
export const registerUser = (email, password) => {
    return fetch(`${BASE_URL}/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    });
};

// Login user.
export const loginUser = (email, password) => {
    return fetch(`${BASE_URL}/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    });
};
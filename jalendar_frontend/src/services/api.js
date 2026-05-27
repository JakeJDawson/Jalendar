// Set a URL for the service to use.
const BASE_URL = "http://localhost:8080/api";

const request = async (url, options = {}) => {
    const response = await fetch(BASE_URL + url, {
        headers: {
            "Content-Type": "application/json",
            ...options.headers
        },
        ...options
    });

    // Try to parse JSON safely
    const data = await response.json().catch(() => null);

    if (!response.ok) {
        const errorMessage =
            data?.message || "Something went wrong with the request";
        throw new Error(errorMessage);
    }

    return data;
};

export const api = {
    get: (url) => request(url, {method: "GET"}),

    post: (url, body) =>
        request(url, {
            method: "POST",
            body: JSON.stringify(body)
        }),

    put: (url, body) =>
        request(url, {
            method: "PUT",
            body: JSON.stringify(body)
        }),

    delete: (url) =>
        request(url, {method: "DELETE"})
};
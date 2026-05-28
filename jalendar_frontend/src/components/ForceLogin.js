// Import statements.
import {useEffect, useState} from "react";
import {Navigate} from "react-router-dom";

/**
 * Basically this function just allows me to enforce certain pages requiring
 * the user to login. Without this, you could just manually route to all of
 * the pages. And even if you can't see any sensitive data, this still isn't
 * amazing, so I'll enforce making the user login.
 */
function ForceLogin({children}) {
    // Check if a JWT token exists.
    const token = localStorage.getItem("token");

    // If there isn't a current userID, send the user to login.
    /**
     * Somewhat important note to my future self, this entire file is sort of
     * acting at an absolute bare minimum right now. It just checks for a
     * token, then assumes all is well if there is one. It could absolutely be
     * more secure if I confirm that token is VALID first, before giving the go
     * ahead.
     */
    if(!token) {
        return <Navigate to="/" replace />;
    }

    // Otherwise, carry on, you're free to go.
    return children;
}

export default ForceLogin;
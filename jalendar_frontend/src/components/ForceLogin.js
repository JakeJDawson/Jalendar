// Import statements.
import {useEffect, useState} from "react";
import {Navigate} from "react-router-dom";

/**
 * Basically this function just allows me to enforce certain pages requiring
 * the user to login. Without this, you could just manually route to all of
 * the pages. And even if you can't see any sensitive data, this still isn't
 * amazing, so I'll enforce making the user login.
 */
function ForceLogin({userID, children}) {
    // If there isn't a current userID, send the user to login.
    if(!userID) {
        return <Navigate to="/" replace />;
    }

    // Otherwise, carry on, you're free to go.
    return children;
}

export default ForceLogin;
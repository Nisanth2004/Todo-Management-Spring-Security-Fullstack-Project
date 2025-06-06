import axios from "axios";

const AUTH_REST_API_BASE_URL="https://todo-ybeh.onrender.com/api/auth"


export const registerAPICall=(registerObj)=>{
    return axios.post(AUTH_REST_API_BASE_URL +'/register',registerObj)
}

export const loginAPICall=(usernameOrEmail,password)=>{
    return axios.post(AUTH_REST_API_BASE_URL +'/login',{usernameOrEmail,password})
}

export const storeToken=(token)=>{
    return localStorage.setItem("token",token)
}

export const getToken=()=>{
    return localStorage.getItem("token")
}

export const saveLoggedInUser=(username,role)=>{

     sessionStorage.setItem("authenticatedUser",username);
     sessionStorage.setItem("role",role);
}

export const isUserLoggedIn=()=>{
    const username=sessionStorage.getItem("authenticatedUser");
    if(username==null)
    {
        return false;
    }
    else{
        return true;
    }
}

export const getLoggedInUser=()=>{
    const username= sessionStorage.setItem("authenticatedUser");
    return username;

}


export const logout=()=>{
    localStorage.clear()
    sessionStorage.clear()
    
}

// check whether the loggedin user is admin or not
export const isAdminUser=()=>{
    let role=sessionStorage.getItem("role");
    if(role!=null && role ==='ROLE_ADMIN')
    {
        return true;
    }
    else{
        return false;
    }
 
}


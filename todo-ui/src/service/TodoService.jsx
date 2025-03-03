import axios from "axios";
import { getToken } from "./AuthService";

const BASE_REST_API_URL='http://localhost:8080/api/todos';


// Add a request interceptor
axios.interceptors.request.use(function (config) {
    // Do something before request is sent

    config.headers['Authorization']=getToken();
    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });



export function getAllTodos()
{
    return axios.get(BASE_REST_API_URL);
}
export function saveTodo(todo)
{
    return axios.post(BASE_REST_API_URL,todo);
}

export function getTodo(id)
{
    return axios.get(BASE_REST_API_URL+'/'+id);
}

export function updateTodo(id,todo)
{
    return axios.put(BASE_REST_API_URL+'/'+id,todo);
}

export function deleteTodo(id)
{
    return axios.delete(BASE_REST_API_URL+'/'+id);
}

export function completeTodo(id)
{
    return axios.patch(BASE_REST_API_URL+'/'+id+'/complete');
}
export function inCompleteTodo(id)
{
    return axios.patch(BASE_REST_API_URL+'/'+id+'/incomplete');
}

import axios from "axios";

const BASE_REST_API_URL='http://localhost:8080/api/todos';

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

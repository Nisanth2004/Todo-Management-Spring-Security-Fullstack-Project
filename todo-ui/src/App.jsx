import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ListTodoComponent from './components/ListTodoComponent'
import Header from './components/Header'
import Footer from './components/Footer'
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import TodoComponent from './components/TodoComponent'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
import { isUserLoggedIn } from './service/AuthService'

function App() {

  function AuthenticatedRoute({children})
  {
    const isAuth=isUserLoggedIn();
    if(isAuth)
    {
      return children
    }

    // if user is not authenticated link to navigate component
    return <Navigate to='/' />
  }

  return (
    <>
    <BrowserRouter>
    
    <Header/>
      
      <Routes>
        {/* http://localhost:3000 */}
        <Route path='/' element={<LoginComponent/>}></Route>


          {/* http://localhost:3000/todos */}
          <Route path='/todos' element={
            
            <AuthenticatedRoute>
                <ListTodoComponent/>
            </AuthenticatedRoute>
            
            }>

            </Route>

          {/* http://localhost:3000/add-todo */}
          <Route path='/add-todo' element={
              <AuthenticatedRoute>
                 <TodoComponent/>
            </AuthenticatedRoute>
            
            }></Route>


            {/* http://localhost:3000/update-todo/:id */}
            <Route path='/update-todo/:id' element={
            <AuthenticatedRoute>
              <TodoComponent/>
              </AuthenticatedRoute>
              }></Route>

             {/* http://localhost:3000/register */}
          <Route path='/register' element={<RegisterComponent/>}></Route>

           {/* http://localhost:3000/login */}
           <Route path='/login' element={<LoginComponent/>}></Route>
      </Routes>
      <Footer/>

      </BrowserRouter>
    </>
  )
}

export default App

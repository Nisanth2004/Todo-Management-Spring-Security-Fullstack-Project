import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ListTodoComponent from './components/ListTodoComponent'
import Header from './components/Header'
import Footer from './components/Footer'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import TodoComponent from './components/TodoComponent'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <BrowserRouter>
    
    <Header/>
      
      <Routes>
        {/* http://localhost:3000 */}
        <Route path='/' element={<ListTodoComponent/>}></Route>
          {/* http://localhost:3000/todos */}
          <Route path='/todos' element={<ListTodoComponent/>}></Route>

          {/* http://localhost:3000/add-todo */}
          <Route path='/add-todo' element={<TodoComponent/>}></Route>


            {/* http://localhost:3000/update-todo/:id */}
            <Route path='/update-todo/:id' element={<TodoComponent/>}></Route>
      </Routes>
      <Footer/>

      </BrowserRouter>
    </>
  )
}

export default App

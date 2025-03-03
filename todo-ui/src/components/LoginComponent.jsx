import React, { useState } from 'react'
import { loginAPICall, storeToken } from '../service/AuthService';
import { useNavigate } from 'react-router-dom';

const LoginComponent = () => {

    const[username,setUsername]=useState('')
    const[password,setPassword]=useState('')

    const navigator=useNavigate();


    function handleLoginFomr(e)
    {
     e.preventDefault();


      loginAPICall(username,password).then((response)=>{

        // after sucessfull login ,create a token and store in local storage

            console.log(response.data)

            // covert username and password into base64 text
            const token='Basic '+window.btoa(username+":"+password);
            storeToken(token);
            navigator('/todos')
        }).catch(error=> {
            console.error(error);
        })
       
        


    }
  return (
    <div className='conatiner'>
        <br/><br/>
        <div className='row'>
            <div className='col-md-6 offset-md-3'>
                <div className='card'>
                    <div className='card-header'>
                        <h2 className='text-center'>User Login Form</h2>

                    </div>
                    <div className='card-body'>
                        <form>
                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'>Username or Email </label>
                                <div className='col-md-9'>
                                    <input
                                    type='text'
                                    name='username'
                                    className='form-control'
                                    placeholder='Enter username or email'
                                    value={username}
                                    onChange={(e)=>setUsername(e.target.value)}
                                    >
                                    </input>

                                </div>

                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'>Password</label>
                                <div className='col-md-9'>
                                    <input
                                    type='password'
                                    name='password'
                                    className='form-control'
                                    placeholder='Enter Password'
                                    value={password}
                                    onChange={(e)=>setPassword(e.target.value)}
                                    >
                                    </input>

                                </div>

                            </div>

                           

                            <div className='form-group mb-3'>
                                <button className='btn btn-primary' onClick={(e)=>handleLoginFomr(e)}>Submit</button>

                            </div>
                        </form>

                    </div>

                </div>

            </div>

        </div>
      
    </div>
  )
}

export default LoginComponent

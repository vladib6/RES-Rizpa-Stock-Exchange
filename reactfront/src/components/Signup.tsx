import React, { useState } from "react";
import API from "../api/api";
import { Redirect,useHistory } from "react-router-dom";
import { useGlobalContext } from "../App";
import { useEffect } from "react";


export const Signup = ()=>{
  const history=useHistory()
  const {setUser,setLogged,setType}=useGlobalContext();
  const [username,setUsername]=useState("")
  const [usertype,setUsertype]=useState("Trader")
  const [invalidUsername,setInvalid]=useState(false);

  const handleLogin= async(e:React.FormEvent<HTMLFormElement>)=>{
      e.preventDefault();
      let answer:boolean=await API.post('/api/login?user='+username+'&type='+usertype).then(res=>res.data).catch(err=>console.log(err));
      if(answer===true){
        console.log("true");
        setUser(username);
        setLogged(true);
        setType(usertype);
        history.push("/dashboard/"+username)
      }else{
        setInvalid(true)
      }
  }
  
  const handleUsernameChange=(e:React.ChangeEvent<HTMLInputElement>)=>{
        e.preventDefault()
        setUsername(e.target.value)
        
  }

  const handleUsertypeChoice=(e:React.MouseEvent<HTMLButtonElement, MouseEvent>)=>{
        e.preventDefault()
        setUsertype(e.currentTarget.textContent as string)
  }
  
return (
    <section className="clean-block clean-form dark">
    <div className="container">
      <div className="block-heading">
        <h2 className="text-info">Sign up</h2>
        <p>To use the system you must log in with a username and password<br /><br /></p>
      </div>
      <form onSubmit={(e)=> handleLogin(e)} >
        <div className="mb-3"><label className="form-label" htmlFor="email">Username</label><input required min="1" className="form-control item" type="username" id="email" onChange={(e)=>handleUsernameChange(e)}/></div>
        <div className="mb-3"><label className="form-label" htmlFor="password">Password</label><input required min="1" className="form-control" type="password" id="password"/></div>
        <div className="btn-group" role="group">
        <button  autoFocus={true} className="btn btn-primary" type="button" onClick={(e)=>handleUsertypeChoice(e)}>Trader</button>
        <button  className="btn btn-primary" type="button" onClick={(e)=>handleUsertypeChoice(e)}>Admin  </button>
        </div>
        {invalidUsername && <div><label style={{color:"red"}}>{username} already exist in the system please login with another name</label></div>}
        <div className="mb-3" /><button className="btn btn-primary" type="submit">Log In</button>
      </form>
    </div>
  </section>
)
}


import React, {useEffect, useState} from 'react';
import './App.css';
 
import API from "./api/api";
import {BrowserRouter as Router, Redirect, Route, Switch,useHistory } from 'react-router-dom'
import  {Homepage}  from './components/Homepage';
import {Dashboard} from './components/Dashboard';
import {Actions} from './components/Actions';
import { Signup } from './components/Signup';
import {Topnavbar} from './components/Navbar';
import { Contact } from './components/Contact';
import { createContext } from 'react';
import { useContext } from 'react';

export type GlobalContent={
    username:string,
    loggedIn:boolean,
    setUser:(u:string)=>void,
    setLogged:(s:boolean)=>void
}

export const GlobalContext=createContext<GlobalContent>({
   username:"",
   loggedIn:false,
   setUser:()=> {},
   setLogged:()=>{}
})

export const useGlobalContext=()=>useContext(GlobalContext)

function App() {
    const [username,setUser]=useState<string>("")
    const [loggedIn,setLogged]=useState<boolean>(false);
 

  
    return (
        <GlobalContext.Provider value={{username,setUser,loggedIn,setLogged}}>
        <Router> 
            {console.log("rerender")}
            <Topnavbar/>
            <div>
            <Switch>
                <Route exact path="/index.html" >{loggedIn?<Redirect to="/dashboard"/>:<Homepage/>}</Route>
                <Route exact path="/" >{loggedIn?<Redirect to={"/dashboard/"+username}/>:<Homepage/>}</Route>
                <Route path="/signup" >{loggedIn?<Redirect to={"/dashboard/"+username}/>:<Signup/>}</Route>
                <Route path="/dashboard/:name" >{loggedIn?<Dashboard  type="Trader"/>:<Redirect to="/signup"/>}</Route>
                <Route path="/actions/:stockname" >{loggedIn?<Actions/>:<Redirect to="/signup"/>}</Route>
                <Route path="/contact" component={Contact}/>

            </Switch>
            </div>
   
        </Router>
        </GlobalContext.Provider>
        
    )
}

export default App

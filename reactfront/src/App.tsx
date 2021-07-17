import React, {useEffect, useState} from 'react';
import './App.css';
 import {BrowserRouter as Router, Redirect, Route, Switch } from 'react-router-dom'
import  {Homepage}  from './components/Homepage';
import {Dashboard} from './components/Dashboard';
import {Actions} from './components/Actions';
import { Signup } from './components/Signup';
import {Topnavbar} from './components/Navbar';
import { Contact } from './components/Contact';
import { createContext } from 'react';
import { useContext } from 'react';
import { toast } from 'react-toastify';
export type GlobalContent={
    username:string,
    loggedIn:boolean,
    type:string,
    setUser:(u:string)=>void,
    setLogged:(s:boolean)=>void,
    setType:(t:string)=>void
}

export const GlobalContext=createContext<GlobalContent>({
   username:"",
   loggedIn:false,
   type:"",
   setUser:()=> {},
   setLogged:()=>{},
   setType:()=>{}
})


export const useGlobalContext=()=>useContext(GlobalContext)

//TODO: threads   , back button in page 3,
//TODO: sessions in server, update my photo at homepage
//TODO:QA,check all commands exchange
//TODO: QA on tomcat server
toast.configure();
function App() {
    const [username,setUser]=useState<string>("")
    const [loggedIn,setLogged]=useState<boolean>(false);
    const [type,setType]=useState<string>("");
    
    useEffect(()=>{
        const stateValues=JSON.parse(window.localStorage.getItem("user-profile")!);
        if(stateValues){
            setUser(stateValues.username)
            setLogged(stateValues.loggedIn)
            setType(stateValues.type)
        } 
    },[])

    useEffect(()=>{
        const stateValues={username,loggedIn,type};
        window.localStorage.setItem("user-profile",JSON.stringify(stateValues))
    })

   
    return (
        <GlobalContext.Provider value={{username,setUser,loggedIn,setLogged,type,setType}}>
        <Router > 
            <Topnavbar/>
            <div>
            <Switch>
                <Route exact path="/index.html" >{loggedIn?<Redirect to={"/dashboard"+username}/>:<Homepage/>}</Route>
                <Route exact path="/" >{loggedIn?<Redirect to={"/dashboard/"+username}/>:<Homepage/>}</Route>
                <Route path="/signup" >{loggedIn?<Redirect to={"/dashboard/"+username}/>:<Signup/>}</Route>
                <Route path="/dashboard/:name" >{loggedIn?<Dashboard />:<Redirect to="/signup"/>}</Route>
                <Route path="/actions/:stockname/:name"  component={Actions}/>
                <Route path="/contact" component={Contact}/>
            </Switch>
            </div>
   
        </Router>
        </GlobalContext.Provider> 
    )
}

export default App

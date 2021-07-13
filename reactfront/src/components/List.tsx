import { useState } from "react";
import api from "../api/api"
import { useGlobalContext } from "../App";
import {HiStatusOnline} from 'react-icons/hi'
import { useEffect } from "react";
export function List(){
    const [users,updateUsers]=useState<{type:string,name:string}[]>();

    useEffect(()=>{
        const interval=setInterval(async()=>{
            await api.get("/api/onlineusers").then((res)=>{ updateUsers(res.data) }).catch(err=>console.log(err))
       },5000);
       return ()=>clearInterval(interval)
    },[])

    return (
        <div className="card shadow mb-4">
                <div className="card-header py-3">
                    <h6 className="text-primary fw-bold m-0">Currently Online  <HiStatusOnline/></h6>
                </div>
                    <ul className="list-group list-group-flush">
                       {users?.map((user)=>{return (
                        <li key={user.name}  className="list-group-item">
                            <div  className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <h6 className="mb-0"><strong>{user.name}</strong></h6><span className="text-xs">{user.type}</span>
                                    </div>
                            </div>
                        </li> )     
                       })}
                    </ul>
        </div>
    )
}
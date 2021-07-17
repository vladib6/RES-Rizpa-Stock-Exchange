import React from "react"
import { useEffect } from "react"
import { useState } from "react"
import api from "../api/api"
import { useGlobalContext } from "../App";

interface userData{
    totalHoldings:number,
    username:string,
    accountBalance:number,
    totalTransactions:number
}

export function Userdata (){
    const {username}=useGlobalContext()
    const [data,setData]=useState<userData>()

    
    useEffect(()=>{
        const interval=setInterval(async()=>{
            await api.get('/api/user?user='+username).then(res=>{
                setData(res.data)
            }).catch(err=>console.log(err))
        },5000);
        return ()=>clearInterval(interval);
    },[])


    return (
        <div className="row">
                <div className="col-md-6 col-xl-4 mb-4">
                    <div className="card shadow border-start-primary py-2">
                        <div className="card-body">
                            <div className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <div className="text-uppercase text-primary fw-bold text-xs mb-1"><span>Account Balance</span></div>
                                    <div className="text-dark fw-bold h5 mb-0"><span>{data?.accountBalance} $</span></div>
                                </div>
                                <div className="col-auto"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-md-6 col-xl-4 mb-4">
                    <div className="card shadow border-start-success py-2">
                        <div className="card-body">
                            <div className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <div className="text-uppercase text-success fw-bold text-xs mb-1"><span>Stocks Holdings</span></div>
                                    <div className="text-dark fw-bold h5 mb-0"><span>{data?.totalHoldings} $</span></div>
                                </div>
                                <div className="col-auto"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>   
    )
}
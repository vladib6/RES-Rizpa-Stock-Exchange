import React from "react"
import { useEffect } from "react"
import { useState } from "react"
import api from "../api/api"
import { useGlobalContext } from "../App";



export function Userdata (){
    const {username}=useGlobalContext()
    const [data,setData]=useState<{balance:number,holdings:number,transactions:number}>()


    useEffect(()=>{
        setTimeout(async ()=>{
            await api.get('/api/getuserdata?user='+username).then(res=>console.log(res.data)).catch(err=>console.log(err))
        },8000)
    },[data])


    return (
        <div className="row">
                <div className="col-md-6 col-xl-3 mb-4">
                    <div className="card shadow border-start-primary py-2">
                        <div className="card-body">
                            <div className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <div className="text-uppercase text-primary fw-bold text-xs mb-1"><span>Account Balance</span></div>
                                    <div className="text-dark fw-bold h5 mb-0"><span>{data?.balance}</span></div>
                                </div>
                                <div className="col-auto"><i className="fas fa-calendar fa-2x text-gray-300"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-md-6 col-xl-3 mb-4">
                    <div className="card shadow border-start-success py-2">
                        <div className="card-body">
                            <div className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <div className="text-uppercase text-success fw-bold text-xs mb-1"><span>Stocks Holdings</span></div>
                                    <div className="text-dark fw-bold h5 mb-0"><span>{data?.holdings}</span></div>
                                </div>
                                <div className="col-auto"><i className="fas fa-dollar-sign fa-2x text-gray-300"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
               
                <div className="col-md-6 col-xl-3 mb-4">
                    <div className="card shadow border-start-warning py-2">
                        <div className="card-body">
                            <div className="row align-items-center no-gutters">
                                <div className="col me-2">
                                    <div className="text-uppercase text-warning fw-bold text-xs mb-1"><span>Total Transactions</span></div>
                                    <div className="text-dark fw-bold h5 mb-0"><span>{data?.transactions}</span></div>
                                </div>
                                <div className="col-auto"><i className="fas fa-comments fa-2x text-gray-300"></i></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>   
    )
}
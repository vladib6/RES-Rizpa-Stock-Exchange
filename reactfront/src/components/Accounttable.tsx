import React, { useState } from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import api from "../api/api";
import { useGlobalContext } from "../App";
interface actions {
    type:string,
    date:string,
    amount:number,
    rateBefore:number,
    rateAfter:number,
}
interface accountData{  
    accounteBalance:number,
    actionsHistory:actions[],
}
export function Accounttable(){
    const [actionsArray,setAccountData]=useState<accountData>()
    const {username}=useGlobalContext();
    const [amount,setAmount]=useState("0");
    const [chargeMsg,setChargeMsg]=useState(null);
    const [chargeBtn,setChargeBtn]=useState(true);
    useEffect(()=>{
        const interval=setInterval(async()=>
        await api.get('/api/account?user='+username).then(res=>{
            console.log(res.data)
            setAccountData(res.data)}).catch(err=>console.log(err))
        ,5000);
        return ()=>clearInterval(interval)
    },[])

    const handleCharging=async()=>{
        await api.post('/api/charge?user='+username+'&amount='+amount).then(res=>setChargeMsg(res.data)).catch(err=>setChargeMsg(err))
    }
    return (
    <div className="card shadow">
                        <div className="card-header py-3">
                            <p className="text-primary m-0 fw-bold">Your Account</p>
                        </div>
                        <div className="card-body">
                            <div className="row">
                               <div className="col-md-6 text-nowrap">
                                <button disabled={chargeBtn} className="btn btn-primary btn-sm d-none d-sm-inline-block" onClick={handleCharging} ><i className="fas fa-download fa-sm text-white-50"></i>Charge Money</button>
                                <div className="mb-3"><input required min="1" className="form-control item" type="username" id="amount" placeholder="amount" onChange={(e)=>{
                                    if(e.target.value =="0"||e.target.value ==""){
                                        setChargeBtn(true)
                                    }else if(isNaN(parseInt(e.target.value))||parseInt(e.target.value)<=0){
                                        setChargeBtn(true)
                                    }else{
                                        setChargeBtn(false)
                                    }
                                }}/>
                                </div>
                                {chargeMsg && <span> {chargeMsg}</span>}
                                </div>
                            </div>
                            <div className="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                <table className="table my-0" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>Type</th>
                                            <th>Date</th>
                                            <th>Amount</th>
                                            <th>Rate Before</th>
                                            <th>Rate After</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {actionsArray?.actionsHistory?.map(action=>{
                                            return(
                                            <tr>
                                                <td>{action.type}</td>
                                                <td>{action.date}</td>
                                                <td>{action.amount}</td>
                                                <td>{action.rateBefore}</td>
                                                <td>{action.rateAfter}</td>
                                            </tr>
                                            )
                                        })}
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td><strong>Type</strong></td>
                                            <td><strong>Date</strong></td>
                                            <td><strong>Amount</strong></td>
                                            <td><strong>Rate Before</strong></td>
                                            <td><strong>Total Amount: {actionsArray?.accounteBalance}</strong></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div className="row">
                                <div className="col-md-6 align-self-center">
                                    <p id="dataTable_info" className="dataTables_info" role="status" aria-live="polite">Showing 1 to 10 of 27</p>
                                </div>
                                <div className="col-md-6">
                                    <nav className="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
                                        <ul className="pagination">
                                            <li className="page-item disabled"><a className="page-link"  aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                            <li className="page-item active"><a className="page-link" >1</a></li>
                                            <li className="page-item"><a className="page-link" >2</a></li>
                                            <li className="page-item"><a className="page-link" >3</a></li>
                                            <li className="page-item"><a className="page-link"  aria-label="Next"><span aria-hidden="true">»</span></a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
    )
   
}
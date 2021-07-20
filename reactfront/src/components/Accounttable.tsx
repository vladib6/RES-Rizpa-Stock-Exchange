import React, { useState } from "react";
import { useEffect } from "react";
import api from "../api/api";
import { useGlobalContext } from "../App";
interface actions {
    type:string,
    date:string,
    actionSum:number,
    oldSum:number,
    newSum:number,
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
                                <div className="mb-3"><input required min="1" className="form-control item" type="number" id="amount" placeholder="How much to charge ?" onChange={(e)=>{
                                    if(e.target.value ==="0"||e.target.value ===""){
                                        setChargeBtn(true)
                                    }else if(isNaN(parseInt(e.target.value))||parseInt(e.target.value)<=0){
                                        setChargeBtn(true)
                                    }else{
                                        setAmount(e.target.value)
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
                                            <th>Action Sum</th>
                                            <th>Rate Before</th>
                                            <th>Rate After</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {actionsArray?.actionsHistory?.map(action=>{
                                            return(
                                            <tr key={action.date}>
                                                <td>{action.type}</td>
                                                <td>{action.date}</td>
                                                <td>{action.actionSum}</td>
                                                <td>{action.oldSum}</td>
                                                <td>{action.newSum}</td>
                                            </tr>
                                            )
                                        })}
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td><strong>Type</strong></td>
                                            <td><strong>Date</strong></td>
                                            <td><strong>Action Sum</strong></td>
                                            <td><strong>Rate Before</strong></td>
                                            <td><strong>Account Balance: {actionsArray?.accounteBalance}</strong></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
    )
   
}
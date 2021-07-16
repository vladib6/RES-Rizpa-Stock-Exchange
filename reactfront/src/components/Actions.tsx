import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useParams } from "react-router";
import api from "../api/api";
import { useGlobalContext } from "../App";
import { Cmdtable } from "./Cmdtable";
import MyAccordion from "./MyAccordion";
import {Stockdata} from './Stockdata'
import {Transactionstable} from './Transactionstable'

export interface RouteParams{
    stockname:string,
    type:string
}

export interface Cmd {
    time:string,
    type:string,
    initiativeUser:string,
    direction:string,
    numOfStocks:number,
    price:number,
}

export interface CmdtableProps{
    data:Cmd[]|undefined,
    title:string
}

export function Actions (){
    const {type}=useGlobalContext()
    const {stockname}=useParams<RouteParams>()
    const [buyCmds,setBuyCmds]=useState<Cmd[]>()
    const [sellCmds,setSellCmds]=useState<Cmd[]>()
 
        useEffect(()=>{
            const interval=setInterval(async()=>{
                if(type==="Admin  "){
                    await api.get('/api/buycommands?stock='+stockname)
                    .then(res=>setBuyCmds(res.data))
                    .catch(err=>console.log(err));
        
                    await api.get('/api/sellcommands?stock='+stockname)
                    .then(res=>setSellCmds(res.data))
                    .catch(err=>console.log(err));
                }
            },7000)

            return ()=>clearInterval(interval)
         },[])
    
    
    return (
                <section>
            <div className="container-fluid">
            <div className="d-sm-flex justify-content-between align-items-center mb-4">
                <h3 className="text-dark mb-0"> {stockname} </h3>
            </div>
            <Stockdata />
            </div>
            <div className="row">
                    <div className="col-lg-7 col-xl-8">
                        <div className="card shadow mb-4">
                            <Transactionstable stockname={stockname}/>
                        </div>
                    </div>
                    <div className="col-lg-5 col-xl-3"> 
                    </div>
            </div>
           
            <div className="row">
                    {type==="Trader"
                   ?  <div className="col-lg-6  col-xl-8">
                        <div className="card shadow mb-7">
                            <MyAccordion stockname={stockname} />
                        </div>
                    </div>
                    : <><div className="col-lg-5 col-xl-6">
                        <div className="card shadow mb-4">
                            <Cmdtable data={buyCmds} title={"Buy Commands"} />
                        </div>
                    </div>
                    <div className="col-lg-7 col-xl-6">
                        <div className="card shadow mb-4">
                          <Cmdtable data={sellCmds} title={"Sell Commands"} />
                        </div>
                    </div>
                    </>
                }
                    
            </div>
            </section>

    )
}


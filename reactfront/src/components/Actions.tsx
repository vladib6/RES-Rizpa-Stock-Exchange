import React from "react";
import { useParams } from "react-router";
import { Alerts } from "./Alerts";
import {Stockdata} from './Stockdata'
import {Transactionstable} from './Transactionstable'

export interface RouteParams{
    stockname:string,
    type:string
}

interface Usertype{
    type:string;
}
export function Actions (usertype:Usertype){
    const {stockname}=useParams<RouteParams>()
    return (
                <section>
                <Alerts/>
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
                    <div className="col-lg-7 col-xl-8">
                        <div className="card shadow mb-4">
                        </div>
                    </div>
                    <div className="col-lg-1 col-xl-1">
                        <div className="card shadow mb-4">
                        </div>
                    </div>
            </div>
            </section>

    )
}


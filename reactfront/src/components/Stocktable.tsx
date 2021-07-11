import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";
import {BsClipboardData} from 'react-icons/bs'
import api from "../api/api";
interface Stock{
    symbol:string,
    companyName:string,
    currentPrice:number,
    TransactionTurnover:number
}

export function Stocktable(){
    const [stocks,setStocks]=useState<Stock[]>()
 

    useEffect(()=>{
        setTimeout(async()=>
        await api.get('/api/stocks').then(res=>{setStocks(res.data)}).catch(err=>console.log(err))
        ,5000)
    },[stocks])


    return (
    <div className="card shadow">
                        <div className="card-header py-3">
                            <p className="text-primary m-0 fw-bold">Stocks in system</p>
                        </div>
                        <div className="card-body">
                            <div className="row">
                                <div className="col-md-6 text-nowrap">
                                    <div id="dataTable_length" className="dataTables_length" aria-controls="dataTable"><label className="form-label">Show<select defaultValue={10} className="d-inline-block form-select form-select-sm">
                                                <option value="10" >10</option>
                                                <option value="25">25</option>
                                                <option value="50">50</option>
                                                <option value="100">100</option>
                                            </select></label></div>
                                </div>
                                <div className="col-md-6">
                                    <div className="text-md-end dataTables_filter" id="dataTable_filter"><label className="form-label"><input type="search" className="form-control form-control-sm" aria-controls="dataTable" placeholder="Search"/></label></div>
                                </div>
                            </div>
                            <div className="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                <table className="table my-0" id="dataTable">
                                    <thead>
                                        <tr>
                                            <th>Company Name</th>
                                            <th>Symbol</th>
                                            <th>Current Rate</th>
                                            <th>Trading Turnover</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       {stocks?.map(stock=>{return (
                                        <tr key={stock.symbol} >
                                        <td><Link to={"/actions/"+stock.symbol }> <BsClipboardData/>  {stock.companyName}</Link></td>
                                        <td>{stock.symbol}</td>
                                        <td>{stock.currentPrice}</td>
                                        <td>{stock.TransactionTurnover}</td>
                                    </tr>)})}
                                  
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td><strong>Company Name</strong></td>
                                            <td><strong>Symbol</strong></td>
                                            <td><strong>Current Rate</strong></td>
                                            <td><strong>Trading Turnover</strong></td>
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
                                            <li className="page-item disabled"><a className="page-link" href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                                            <li className="page-item active"><a className="page-link" href="#">1</a></li>
                                            <li className="page-item"><a className="page-link" href="#">2</a></li>
                                            <li className="page-item"><a className="page-link" href="#">3</a></li>
                                            <li className="page-item"><a className="page-link" href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
    )
   
}
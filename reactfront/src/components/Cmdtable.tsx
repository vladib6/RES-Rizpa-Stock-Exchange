import React, { useState } from "react";
import { useEffect } from "react";
import api from "../api/api";
import { useGlobalContext } from "../App";
import { CmdtableProps,Cmd } from "./Actions";


export function Cmdtable( params:CmdtableProps){
    
     return (
        <div className="card shadow">
        <div className="card-header py-3">
            <p className="text-primary m-0 fw-bold">{params.title}</p>
        </div>
        <div className="card-body">
            <div className="row">
                <div className="col-md-6 text-nowrap"> </div>
                <div className="col-md-6">
                    <div className="text-md-end dataTables_filter" id="dataTable_filter"><label className="form-label"><input type="search" className="form-control form-control-sm" aria-controls="dataTable" placeholder="Search"/></label></div>
                </div>
            </div>
            <div className="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                <table className="table my-0" id="dataTable">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Type</th>
                            <th>Direction</th>
                            <th>Initiative User</th>
                            <th>Num Of Stocks</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                       {params.data?.map(cmd=>{return (
                        <tr key={cmd.date} >
                        <td>{cmd.date}</td>
                        <td>{cmd.type}</td>
                        <td>{cmd.direction}</td>
                        <td>{cmd.initiativeUser}</td>
                        <td>{cmd.numOfStocks}</td>
                        <td>{cmd.price}</td>
                    </tr>)})}
                  
                    </tbody>
                    <tfoot>
                        <tr>
                            <td><strong>Date</strong></td>
                            <td><strong>Type</strong></td>
                            <td><strong>Direction</strong></td>
                            <td><strong>Initiative User</strong></td>
                            <td><strong>Num Of Stocks</strong></td>
                            <td><strong>Price</strong></td>
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
                            <li className="page-item active"><a className="page-link" href="#">1</a></li>
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
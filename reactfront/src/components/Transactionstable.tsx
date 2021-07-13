import { useEffect, useState } from "react";
import api from "../api/api";


interface Transactions{
    date:string,
    amount:number,
    price:number
}

interface TransactionsProps {
    stockname:string,
}

export function Transactionstable(stock:TransactionsProps){
    const [stocks,setStocks]=useState<Transactions[]>()
  
    useEffect(()=>{
        const interval=setInterval(async()=>
        await api.get('/api/transactions?stock='+stock.stockname).then(res=>{setStocks(res.data)}).catch(err=>console.log(err))
        ,5000);
        return ()=>clearInterval(interval)
    },[])


    return (
    <div className="card shadow">
                        <div className="card-header py-3">
                            <p className="text-primary m-0 fw-bold">Transactions</p>
                        </div>
                        <div className="card-body">
                            <div className="row">
                                <div className="col-md-6 text-nowrap">
                                   
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
                                            <th>Current Rate ($)</th>
                                            <th>Trading Turnover ($)</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       {/* {stocks?.map(stock=>{return (
                                        // <tr key={stock.symbol} >
                                        // <td> {stock.companyName}</td>
                                        // <td>{stock.symbol}</td>
                                        // <td>{stock.currentPrice}</td>
                                        // <td>{stock.TransactionTurnover}</td>
                                    </tr>)})} */}
                                  
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
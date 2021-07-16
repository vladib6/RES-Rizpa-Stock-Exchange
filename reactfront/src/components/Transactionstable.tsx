import { useEffect, useState } from "react";
import api from "../api/api";


interface Transactions{
    date:string,
    price:number
    numOfStock:number,
}

interface TransactionsProps {
    stockname:string,
}

export function Transactionstable(stock:TransactionsProps){
    const [transactions,setTransactions]=useState<Transactions[]>()
  
    useEffect(()=>{
        const interval=setInterval(async()=>
        await api.get('/api/transactions?stock='+stock.stockname).then(res=>setTransactions(res.data)).catch(err=>console.log(err))
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
                                            <th>Date</th>
                                            <th>Price $</th>
                                            <th>Num of Stocks </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       {transactions?.map(tr=>{return (
                                        <tr key={tr.date} >
                                        <td> {tr.date}</td>
                                        <td>{tr.price}</td>
                                        <td>{tr.numOfStock}</td>
                                    </tr>)})}
                                  
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td><strong>Date</strong></td>
                                            <td><strong>Price $</strong></td>
                                            <td><strong>Num Of Stocks</strong></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
    )
   
}
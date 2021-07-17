import React, { useEffect } from "react";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'
import { useState } from "react";
import { useParams } from "react-router-dom";
import api from "../api/api";
import { List } from "./List";
import {Stocktable} from './Stocktable'
import { Accounttable } from "./Accounttable";
import { Userdata } from "./Userdata";
import { useGlobalContext } from "../App";

export interface RouteParams{
    name:string
}

export interface Popupmsg {
    message:string
}

export function Dashboard(){
    const {name}=useParams<RouteParams>()
    const [file,setFile]=useState<Blob>()
    const {type}=useGlobalContext()
    const [loadMsg,setLoadMsg]=useState("");
    const [showLoadMsg,setShowLoadMsg]=useState(false);

    const handleFileChange=(e:React.ChangeEvent<HTMLInputElement>)=>{
        setFile(e.target.files![0])
    }

    const handleSubmission=()=>{
        const formData=new FormData();
        formData.append('File',file as Blob)
        api.post('/api/upload?user='+name,formData)
        .then(res=>{
            setLoadMsg(res.data)
            setShowLoadMsg(true)
        })
        .catch(err=>console.log(err))  
    }

    useEffect(()=>{
        const interval=setInterval(async()=>{
            await api.get('/api/alerts?user='+name)
            .then(res=>{
                res.data?.map((msg:Popupmsg)=>toast(msg.message,{
                    position: "top-right",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    }))
            })
        },7000)

        return ()=>clearInterval(interval)
    },[])
        return (
            <section>
                <ToastContainer position="top-right"
                    autoClose={5000}
                    hideProgressBar={false}
                    newestOnTop={false}
                    closeOnClick
                    rtl={false}
                    pauseOnFocusLoss
                    draggable
                    pauseOnHover/>
            <div className="container-fluid">
           
            <div className="row">
                <div className="col-md-6 col-xl-4 mb-4">
                    <div className="card shadow border-start-primary py-2">
                        <div className="card-body">
                        <h3 className="text-dark mb-0"> Welcome {name} </h3>
                        </div>
                    </div>
                </div>
                <div className="col-md-6 col-xl-4 mb-4">
                    <div className="card shadow border-start-primary py-2">
                        <div className="card-body">
                        <input onChange={(e)=>{handleFileChange(e)}}   className="btn btn-primary btn-sm d-none d-sm-inline-block" type="file" />
                <p>{loadMsg}</p>
                        </div>
                    </div>
                </div>
                <div className="col-md-2 col-xl-2 mb-4">
                    <div className="card shadow border-start-primary py-2">
                        <div className="card-body">
                        <button onClick={handleSubmission} className="btn btn-primary btn-sm d-none d-sm-inline-block" ><i className="fas fa-download fa-sm text-white-50"></i>Upload File</button>

                        </div>
                    </div>
                </div>
            </div>
          
               {type==="Trader"?<Userdata/>:null} 
            </div>
           
            <div className="row">
                     <div className="col-lg-7 col-xl-8">
                         <div className="card shadow mb-4">
                            <Stocktable/>
                         </div>
                     </div>
                    <div className="col-lg-5 col-xl-3">
                             <List/>
                    </div>
            </div>
            <div className="row">
                     <div className="col-lg-7 col-xl-8">
                         <div className="card shadow mb-4">
                         {type==="Trader"?<Accounttable/>:null} 
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
   
    


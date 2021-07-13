import React from "react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import api from "../api/api";
import { Alerts } from "./Alerts";
import { List } from "./List";
import {Stocktable} from './Stocktable'
import { Accounttable } from "./Accounttable";
import { Userdata } from "./Userdata";
import { useGlobalContext } from "../App";

export interface RouteParams{
    name:string
}
export function Dashboard(){
    const {name}=useParams<RouteParams>()
    const [file,setFile]=useState<Blob>()
    const {type}=useGlobalContext()
    const handleFileChange=(e:React.ChangeEvent<HTMLInputElement>)=>{
        setFile(e.target.files![0])
    }

    const handleSubmission=()=>{
        const formData=new FormData();
        formData.append('File',file as Blob)
        api.post('/api/upload?user='+name,formData).then(res=>console.log(res.data)).catch(err=>console.log(err))
        
    }

        return (
            <section>
                <Alerts/>
            <div className="container-fluid">
            <div className="d-sm-flex justify-content-between align-items-center mb-4">
                <h3 className="text-dark mb-0"> Welcome {name} </h3><input onChange={(e)=>{handleFileChange(e)}}   className="btn btn-primary btn-sm d-none d-sm-inline-block" type="file" /><button onClick={handleSubmission} className="btn btn-primary btn-sm d-none d-sm-inline-block" ><i className="fas fa-download fa-sm text-white-50"></i>Upload File</button>
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
   
    


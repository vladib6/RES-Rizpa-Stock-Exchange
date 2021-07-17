import React, { useState } from "react";
import { Button, Modal } from "react-bootstrap";
import api from "../api/api";
import { useGlobalContext } from "../App";
interface ModalProps{
    setMsg:(msg:string)=>void,
}

export function MyModal(setFunc:ModalProps){
    const {username}=useGlobalContext();
    const [show, setShow] = useState(false);
    const [company,setCompany]=useState("");
    const [symbol,setSymbol]=useState("");
    const [amount,setAmount]=useState(0);
    const [value,setValue]=useState(0);
    const [generalErrMsg,setGeneralErrMsg]=useState("")

    const handleSubmit=()=>{
        if(company==="" || symbol==="" || amount===0 || value===0){
          setGeneralErrMsg("All the fields are required")
        }else{
          api.post('/api/newstock?user='+username+'&company='+company+'&symbol='+symbol+'&amount='+amount+'&value='+value)
          .then(res=>{setFunc.setMsg(res.data)})
          .catch(err=>{setFunc.setMsg(err)});
          setShow(false);
        }
        
    }
    const handleClose = () => {setShow(false);}
    
    const handleShow = () => setShow(true);
    const handleAmountChange=(e:React.ChangeEvent<HTMLInputElement>)=>{
          setAmount(parseInt(e.target.value))
    }

    const handleValueChange=(e:React.ChangeEvent<HTMLInputElement>)=>{
          setValue(parseInt(e.target.value))
    }
    return (
      <>
        <Button variant="primary" onClick={handleShow}>
          Create New Stock
        </Button>
        <Modal show={show} onHide={handleClose}  >
          <Modal.Header closeButton>
            <Modal.Title>Create New Stock</Modal.Title>
          </Modal.Header>
          <Modal.Body>
          <form >
                <div className="mb-3"><label className="form-label" htmlFor="name">Company Name</label><input required min="1" onChange={(e)=>setCompany(e.target.value)} className="form-control item" type="text" id="company" /></div>
                <div className="mb-3"><label className="form-label" htmlFor="symbol">Stock Symbol</label><input required min="1" onChange={(e)=>setSymbol(e.target.value)} className="form-control" type="text" id="symbol"/></div>
                <div className="mb-3"><label className="form-label" htmlFor="amount">Amount Of Stocks</label><input required min="1" onChange={(e)=>handleAmountChange(e)} className="form-control" type="number" id="amount"/></div>
                <div className="mb-3"><label className="form-label" htmlFor="value">Company Value</label><input required min="1" onChange={(e)=>handleValueChange(e)} className="form-control" type="number" id="value"/></div>
          </form>

          </Modal.Body>
          <p style={{color:"red",paddingLeft:"18px"}}>{generalErrMsg}</p>

          <Modal.Footer>
            <Button variant="secondary"  onClick={()=>handleClose()}>
              Cancel
            </Button>
            <Button variant="primary"  onClick={()=>handleSubmit()}>
              Submit
            </Button>
          </Modal.Footer>
        </Modal>
      </>
    );
}

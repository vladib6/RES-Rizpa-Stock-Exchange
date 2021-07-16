import React, { useState } from "react";
import { ButtonGroup, ToggleButton } from "react-bootstrap";
import api from "../api/api";
import { useGlobalContext } from "../App";

interface FormProps {
    stockname:string,
}

export function CommandForm(params:FormProps){
    const {username}=useGlobalContext()
    const [checked, setChecked] = useState(false);
    const [typeValue, setTypeValue] = useState("");
    const [directionValue, setDirectionValue] = useState("");
    const [limitPrice,setLimitPrice]=useState(0);
    const [priceErrMsg,setMsg]=useState("");
    const [showMsg,setShow]=useState(false);
    const [quantity,setQuantity]=useState(0)
    const [quantityMsg,setQuantityMsg]=useState("")
    const [showQuantityMsg,setShowQuantityMsg]=useState(false)
    const [showGeneralErr,setShowGeneralErr]=useState(false);
    const [generalMsg,setGeneralMsg]=useState("");

    const cmdTypes = [
      { name: 'LMT', value: '1' },
      { name: 'MKT', value: '2' },
      { name: 'IOC', value: '3' },
      { name: 'FOK', value: '4' },
    ];
    const directions = [
        { name: 'Sell', value: '1' },
        { name: 'Buy', value: '2' },
      ];
  
    const handleSubmitCommand=async(e:React.FormEvent<HTMLFormElement>)=>{
            e.preventDefault()
            if(typeValue ==="" || directionValue==="" || quantity==0){
                setGeneralMsg("Fill all the fields correctly")
                setShowGeneralErr(true);
            }else if(typeValue!=="MKT" && limitPrice==0){
                setGeneralMsg("Fill all the fields correctly")
                setShowGeneralErr(true);
            } else{
                await api.post('/api/exchange?user='+username+'&stock='+params.stockname+'&direction='+directionValue+'&type='+typeValue+'&quantity='+quantity+'&limit='+limitPrice)
                .then(res=>{setShowGeneralErr(true);
                        setGeneralMsg("Transactions made :"+res.data)})
                .catch(err=>{setShowGeneralErr(true);
                    setGeneralMsg(err)})
            }
    }

    return (
        <section className="clean-block clean-form dark">
        <div className="container">
          <div className="block-heading">
            <h2 className="text-info">Command Properties</h2>
            <p>Please fill all the properties<br /><br /></p>
          </div>
          <form onSubmit={(e)=>handleSubmitCommand(e) }>
                            <div>
                        <ButtonGroup className="mb-2">
                        {cmdTypes.map((type, idx) => (
                        <ToggleButton
                            key={idx}
                            id={`radio-${idx}`}
                            type="radio"
                            variant="secondary"
                            name="radio"
                            value={type.name}
                            checked={typeValue === type.name}
                            onChange={(e) => setTypeValue(e.currentTarget.value)}>
                            {type.name}
                        </ToggleButton>))}
                        </ButtonGroup>
                        </div>
                        <div>
                        <ButtonGroup className="mb-2">
                        {directions.map((dir, idx) => (
                        <ToggleButton
                            key={idx}
                            id={`radio-${idx}`}
                            type="radio"
                            variant="secondary"
                            name="radio"
                            value={dir.name}
                            checked={directionValue === dir.name}
                            onChange={(e) => setDirectionValue(e.currentTarget.value)}>
                            {dir.name}
                        </ToggleButton>))}
                        </ButtonGroup>
                        </div>
                        <div className="mb-3"><label className="form-label" htmlFor="limitprice">Quantity</label>
                             <input required min="1" className="form-control item" type="username" id="limitprice"
                             onChange={(e)=>{
                                if(isNaN(parseInt(e.target.value))){
                                    setQuantityMsg("Write only digits");
                                    setShowQuantityMsg(true)
                                }else if(parseInt(e.target.value)<=0){
                                    setQuantityMsg("Limit price can't be zero of negative");
                                    setShowQuantityMsg(true)
                                }else{
                                    setQuantity(parseInt(e.target.value));
                                    setShowQuantityMsg(false);
                                }
                            }}  />
                             {showQuantityMsg?<label style={{color:"red"}}>{quantityMsg}</label>:null}
                             </div>

                        {(typeValue!=="MKT" && typeValue!=="")?
                             <div className="mb-3"><label className="form-label" htmlFor="limitprice">Limit Price</label>
                             <input required min="1" className="form-control item" type="username" id="limitprice" 
                             onChange={(e)=>{
                                 if(isNaN(parseInt(e.target.value))){
                                     setMsg("Write only digits");
                                     setShow(true)
                                 }else if(parseInt(e.target.value)<=0){
                                     setMsg("Limit price can't be zero of negative");
                                     setShow(true)
                                 }else{
                                     setLimitPrice(parseInt(e.target.value));
                                     setShow(false);
                                 }
                             }} />
                             {showMsg?<label style={{color:"red"}}>{priceErrMsg}</label>:null}
                             </div>:
                             null
                        }
           
                        {showGeneralErr?<label style={{color:"red"}}>{generalMsg}</label>:null}
            <div className="mb-3" /><button className="btn btn-primary" type="submit">Exchange {params.stockname} !</button>
          </form>
          </div>
      </section>
    )   
}
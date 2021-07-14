import React, { useState } from "react";
import { ButtonGroup, ToggleButton } from "react-bootstrap";
import api from "../api/api";

interface FormProps {
    stockname:string,
}

export function CommandForm(params:FormProps){
    const [checked, setChecked] = useState(false);
    const [typeValue, setTypeValue] = useState("");
    const [directionValue, setDirectionValue] = useState("");
    const [limitPrice,setLimitPrice]=useState(0);
    const [priceErrMsg,setMsg]=useState("");
    const [showMsg,setShow]=useState(false);
    const [showGeneralErr,setShowGeneralErr]=useState(false);
    const cmdTypes = [
      { name: 'LMT', value: '1' },
      { name: 'MKT', value: '2' },
      { name: 'IOC', value: '3' },
      { name: 'FOK', value: '4' },
    ];
    const directions = [
        { name: 'SELL', value: '1' },
        { name: 'BUY', value: '2' },
      ];
  
    const handleSubmitCommand=async(e:React.FormEvent<HTMLFormElement>)=>{
            e.preventDefault()
            if(typeValue ==="" || directionValue==="" || limitPrice==0){
                setShowGeneralErr(true);
            }else{
                console.log("type:"+typeValue+" direction:"+directionValue+" price+"+limitPrice)
                // await api.post('/api/exchange').then(res=>console.log(res)).catch(err=>console.log(err))
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
           
                        {showGeneralErr?<label style={{color:"red"}}>Fill all the fields correctly</label>:null}
            <div className="mb-3" /><button className="btn btn-primary" type="submit">Exchange {params.stockname} !</button>
          </form>
          </div>
      </section>
    )   
}
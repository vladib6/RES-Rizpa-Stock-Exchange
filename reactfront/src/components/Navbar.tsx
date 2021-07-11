import React from "react";
import { Link } from "react-router-dom";
import { useGlobalContext } from "../App";

export function Topnavbar (){
        const {username}=useGlobalContext()
    return (

        <nav className="navbar navbar-dark navbar-expand-lg fixed-top bg-dark" id="mainNav" style={{background: "var(--bs-indigo)",color: "var(--bs-red)"}}>
        <div className="container"><Link className="navbar-brand" to="/">RSE-Rizpa-Stock-Exchange</Link><button data-bs-toggle="collapse" data-bs-target="#navbarResponsive" className="navbar-toggler navbar-toggler-right" type="button" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><i className="fa fa-bars"></i></button>
            <div className="collapse navbar-collapse" id="navbarResponsive">
                <ul className="navbar-nav ms-auto text-uppercase">
                    <li className="nav-item"><Link className="nav-link" to="/">Home</Link></li>
                    <li className="nav-item"><Link className="nav-link" to="/signup">Sign-up</Link></li>
                    <li className="nav-item"><Link className="nav-link" to={"/dashboard/"+username}>Dashboard</Link></li>
                    <li className="nav-item"><Link className="nav-link" to="/contact">Contact</Link></li>
                </ul>
            </div>
        </div>
        </nav>

    )
}
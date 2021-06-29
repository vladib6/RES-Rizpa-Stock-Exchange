import React from "react";
import './signup.css'
export function Signup (){

return (
    <section className="clean-block clean-form dark">
    <div className="container">
      <div className="block-heading">
        <h2 className="text-info">Sign up</h2>
        <p>To use the system you must log in with a username and password<br /><br /></p>
      </div>
      <form>
        <div className="mb-3"><label className="form-label" htmlFor="email">Username</label><input className="form-control item" type="email" id="email" /></div>
        <div className="mb-3"><label className="form-label" htmlFor="password">Password</label><input className="form-control" type="password" id="password" /></div>
        <div className="btn-group" role="group"><button className="btn btn-primary" type="button">Admin</button><button className="btn btn-primary" type="button">Trader</button></div>
        <div className="mb-3" /><button className="btn btn-primary" type="submit">Log In</button>
      </form>
    </div>
  </section>
)
}


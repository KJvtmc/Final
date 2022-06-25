import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import { NavLink } from 'react-router-dom';

import '../../App.css';

import http from '../10Services/httpService';
import apiEndpoint from '../10Services/endpoint';
import swal from 'sweetalert';

import inputValidator from '../08CommonComponents/InputValidator';

class CreateNewAccount extends Component {

    constructor(props) {
        super(props);
        this.state = {
            role: "USER",
            name: "",
            surname: "",
            birthdate: "",
            personalCode: "",
            address: "",
            phone: "",
            email: ""
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    drawSelector() {
        return (
            <div className="form">
                <div className="form-group ">
                    <label htmlFor="txtEmail"
                     className="form-label">El. paštas <span className="fieldRequired">*</span></label>
                    <input
                        type="text"
                        className="form-control"
                        id="txtEmail"
                        name="email"
                        value={this.state.email}
                        placeholder="El. paštas"
                        onChange={this.handleChange}
                        onInvalid={(e) => inputValidator(e)}
                        required
                        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}"
                    />
                </div>
            </div>
        )
    }

    drawForm() {
        console.log("graw")
            return (
                <div className="mb-3">
                    <div className="form">
                        <div className="mb-3">
                            <label htmlFor="txtName">Vardas <span className="fieldRequired">*</span></label>
                            <input
                                type="text"
                                className="form-control"
                                id="txtName"
                                name="name"
                                value={this.state.name}
                                onChange={this.handleChange}
                                onInvalid={(e) => inputValidator(e)}
                                placeholder="Vardas"
                                required
                                pattern="[A-zÀ-ž]{2,32}"
                            />
                        </div>
                    </div>
                    <div className="form">
                        <div className="mb-3">
                            <label htmlFor="txtSurname">Pavardė <span className="fieldRequired">*</span></label>
                            <input
                                type="text"
                                className="form-control"
                                id="txtSurname"
                                name="surname"
                                value={this.state.surname}
                                onChange={this.handleChange}
                                onInvalid={(e) => inputValidator(e)}
                                placeholder="Pavardė"
                                required
                                pattern="[A-zÀ-ž]{2,32}"
                            />
                        </div>
                    </div>
                    <div className="form">                       
                        <div className="mb-3">
                            <label htmlFor="txtIdentificationCode">Asmens kodas <span className="fieldRequired">*</span></label>
                            <input
                                type="text"
                                className="form-control"
                                id="txtPersonalCode"
                                name="personalCode"
                                value={this.state.personalCode}
                                onChange={this.handleChange}
                                onInvalid={(e) => inputValidator(e)}
                                placeholder="Asmens kodas"
                                required
                                pattern="[0-9]{11}"
                            />
                        </div>
                    </div>
                    <div className="form">
                        <div className="mb-3">
                            <label htmlFor="txtTelNo">Telefonas <span className="fieldRequired">*</span></label>
                            <div className="input-group">
                               
                                <input
                                    type="tel"
                                    className="form-control"
                                    id="txtTelNo"
                                    name="phone"
                                    value={this.state.phone}
                                    onChange={this.handleChange}
                                    onInvalid={(e) => inputValidator(e)}
                                    placeholder="+37012345678"
                                    required pattern="[+]{1}[0-9]{4,19}">
                                </input>
                            </div>
                        </div>
                    </div>

                    <div className="form">
                        <div className="mb-3">
                            <label htmlFor="txtAddress"
                             className="form-label">Adresas <span className="fieldRequired">*</span></label>
                            <input
                                type="text"
                                className="form-control"
                                id="txtAddress"
                                name="address"
                                value={this.state.address}
                                onChange={this.handleChange}
                                onInvalid={(e) => inputValidator(e)}
                                placeholder="Adresas"
                                required
                            />
                        </div>
                    </div>
                </div>
            )
        
    }

    resetState = () => {
        this.setState({
            name: "",
            surname: "",
            birthdate: "",
            personalCode: "",
            address: "",
            phone: "",
            email: ""
        })

    }



    handleChange(event) {
        const target = event.target;
        inputValidator(event);
        this.setState({
            [target.name]: target.value
        })
    }

    handleSubmit = (event) => {
        event.preventDefault();
        console.log(this.state)
        http.post(`${apiEndpoint}/api/users/main/createuser`, {
            "address": this.state.address,
            //"birthdate": this.state.birthdate,
            "email": this.state.email,
            "name": this.state.name,
            "password": this.state.email,
            "personalCode": this.state.personalCode,
            "phone": this.state.phone,
            "role": "USER",
            "surname": this.state.surname,
            "username": this.state.email
        })
            .then(() => {
                swal({
                    text: "Naujas naudotojas buvo sėkmingai sukurtas.",
                    button: "Gerai"
                }).then(() => {
                    this.props.history.push("/new")
                    this.props.history.replace("/login")
                })
            })
            .catch((error) => {
                
                swal({                   
                    text: error.response.data,                   
                    button: "Gerai"
                })
            })

    }

    
    render() {
        console.log("prerender")
        return (
            <>
            <div className="row">
                <h6 className="py-3"><b>Naujo naudotojo sukūrimas</b></h6>
                <form onSubmit={this.handleSubmit}>
                    {this.drawSelector()}
                    {this.drawForm()}
                    <h6 className="py-3"><b>Naudotojo prisijungimai</b></h6>

                    <div className="row">
                        <div className="col-12">
                            <p><b>Naudotojo vardas</b></p>
                        </div>
                        <div className="col-12">
                            <p>{this.state.email}</p>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-12">
                            <p><b>Slaptažodis</b></p>
                        </div>
                        <div className="col-12">
                            <p>{this.state.email}</p>
                        </div>
                        <div className="col-12">
                            <p>Slaptažodį pakeisti galėsite prisijungę, skiltyje Mano paskyra</p>
                        </div>
                    </div>
                    <div className="mb-3">
                        <button className="btn btn-outline-danger float-start" onClick={this.resetState} id="btnClean">Išvalyti</button>
                        <button type="submit" className="btn btn-primary float-end" id="btnCreate">Sukurti</button>
                    </div>
                </form>
                
            </div>
            <div className="row  mt-3">
            <div className="col ">
                <NavLink className="nav-link" id="login" to={"/login"}><button type="submit" className="btn btn-primary float-end" id="btnCreate">Grįžti</button></NavLink>
            </div>
            </div>
            </>
        )
    }
}

export default withRouter(CreateNewAccount)
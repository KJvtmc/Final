import React, { Component } from "react";
import { withRouter } from "react-router-dom";

import DatePicker from "react-datepicker";
import { registerLocale } from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import lt from "date-fns/locale/lt";

import http from "../10Services/httpService";
import apiEndpoint from "../10Services/endpoint";
import swal from "sweetalert";

import compensationInputValidator from "../08CommonComponents/InputValidator";

import "../../App.css";
import "../08CommonComponents/datePickerStyle.css";
import { subYears } from "date-fns";

import { parseISO } from "date-fns/esm";

registerLocale("lt", lt);

class CreateCompensationApplicationFormContainer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            mainGuardian: {
                name: "",
                surname: "",
                personalCode: "",
                phone: "",
                email: "",
                address: "",
                username: "",
                role: "",
            },
            privateKindergarten: {
                kindergartenName: "",
                kindergartenCode: "",
                kindergartenAddress: "",
                kindergartenPhone: "",
                kindergartenEmail: "",
                kindergartenBankName: "",
                kindergartenAccountNumber: "",
                kindergartenBankCode: "",
            },
            birthdate: "",
            childName: "",
            childPersonalCode: "",
            childSurname: "",

        };
        this.kindergartenOnChange = this.kindergartenOnChange.bind(this);
        this.mainGuardianOnChange = this.mainGuardianOnChange.bind(this);
        this.childOnChange = this.childOnChange.bind(this);
        this.submitHandle = this.submitHandle.bind(this);
    }

    componentDidMount() {
        /** get logged in user data */
        http
            .get(`${apiEndpoint}/api/users/user`)
            .then((response) => {
                this.setState({
                    mainGuardian: {
                        ...this.state.mainGuardian,
                        name: response.data.name,
                        surname: response.data.surname,
                        personalCode: response.data.personalCode,
                        phone: response.data.phone,
                        email: response.data.username,
                        address: response.data.address,
                        username: response.data.username,
                        role: response.data.role,
                    },
                });
            })
            .catch((error) => {
                swal({
                  text: "??vyko klaida perduodant duomenis i?? serverio.",
                  button: "Gerai",
                });
              });
    }

    /** FORMOS */
    /** Atstovo forma */
    userForm() {
        return (
            <div className="form">
                <h6 className="formHeader">Vaiko atstovo duomenys</h6>
                <div className="mb-3">
                    <label htmlFor="txtCMainName"
                        className="form-label">
                        Vardas <span className="fieldRequired">*</span>
                    </label>
                    <input
                        type="text"
                        title=""
                        id="txtMainName"
                        name="name"
                        placeholder="Vardas"
                        className="form-control"
                        value={this.state.mainGuardian.name}
                        onChange={this.mainGuardianOnChange}
                        onInvalid={(e) => compensationInputValidator(e)}
                        required
                        minlenght="2"
                        maxlenght="70"
                        pattern="^\p{L}+(( )+(?:\p{L}+))*$"
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="txtMainSurname"
                        className="form-label">
                        Pavard?? <span className="fieldRequired">*</span>
                    </label>
                    <input
                        type="text"
                        title=""
                        id="txtMainSurname"
                        name="surname"
                        placeholder="Pavard??"
                        className="form-control"
                        value={this.state.mainGuardian.surname}
                        onChange={this.mainGuardianOnChange}
                        onInvalid={(e) => compensationInputValidator(e)}
                        required
                        minlenght="2"
                        maxlenght="70"
                        pattern="^\p{L}+((-)+(?:\p{L}+))*$"
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="txtMainPersonalCode"
                        className="form-label">
                        Asmens kodas <span className="fieldRequired">*</span>
                    </label>
                    <input
                        type="text"
                        title=""
                        id="txtMainPersonalCode"
                        name="personalCode"
                        placeholder="Asmens kodas"
                        className="form-control"
                        value={this.state.mainGuardian.personalCode}
                        onChange={this.mainGuardianOnChange}
                        onInvalid={(e) => compensationInputValidator(e)}
                        required
                        pattern="[0-9]{11}"
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="txtMainPhone" className="form-label">
                        Telefonas <span className="fieldRequired">*</span>
                    </label>
                    <input
                        type="tel"
                        title=""
                        id="txtMainPhone"
                        name="phone"
                        placeholder="+37012345678"
                        className="form-control"
                        value={this.state.mainGuardian.phone}
                        onChange={this.mainGuardianOnChange}
                        onInvalid={(e) => compensationInputValidator(e)}
                        required
                        pattern="[+]{1}[0-9]{5,19}"
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="txtMainEmail" className="form-label">
                        El. pa??tas <span className="fieldRequired">*</span>
                    </label>
                    <input
                        type="text"
                        title=""
                        id="txtMainEmail"
                        name="email"
                        placeholder="El. pa??tas"
                        className="form-control"
                        value={this.state.mainGuardian.email}
                        onChange={this.mainGuardianOnChange}
                        onInvalid={(e) => compensationInputValidator(e)}
                        required
                        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}"
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="txtMainAddress" className="form-label">
                        Adresas <span className="fieldRequired">*</span>
                    </label>
                    <input
                        type="text"
                        title=""
                        className="form-control"
                        id="txtMainAddress"
                        name="address"
                        placeholder="Adresas"
                        value={this.state.mainGuardian.address}
                        onChange={this.mainGuardianOnChange}
                        onInvalid={(e) => compensationInputValidator(e)}
                        required
                        pattern="[^\n]{2,32}"
                    />
                </div>
            </div>
        );
    }

    /** Vaiko forma */
    childForm() {
        return (
            <div className="form">
                <div >
                    <h6 className="formHeader">Vaiko duomenys</h6>
                </div>

                    <div className="mb-3">
                        <label htmlFor="txtChildName" className="form-label">
                            Vaiko vardas <span className="fieldRequired">*</span>
                        </label>
                        <input
                            type="text"
                            title=""
                            id="txtChildName"
                            name="childName"
                            placeholder="Vaiko vardas"
                            className="form-control"
                            value={this.state.childName}
                            onChange={this.childOnChange}
                            onInvalid={(e) => compensationInputValidator(e)}
                            required
                            minlenght="2"
                            maxlenght="70"
                            pattern="^\p{L}+(( )+(?:\p{L}+))*$"
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="txtChildSurname" className="form-label">
                            Vaiko pavard?? <span className="fieldRequired">*</span>
                        </label>
                        <input
                            type="text"
                            title=""
                            id="txtChildSurname"
                            name="childSurname"
                            placeholder="Vaiko pavard??"
                            className="form-control"
                            value={this.state.childSurname}
                            onChange={this.childOnChange}
                            onInvalid={(e) => compensationInputValidator(e)}
                            required
                            minlenght="2"
                            maxlenght="70"
                            pattern="^\p{L}+((-)+(?:\p{L}+))*$"
                        />
                    </div>
                    <div className="row mb-3">
                        <div className="col-lg-9">
                        <label htmlFor="txtChildPersonalCode" className="form-label">
                            Asmens kodas <span className="fieldRequired">*</span>
                        </label>
                        <input
                            type="text"
                            title=""
                            id="txtChildPersonalCode"
                            name="childPersonalCode"
                            placeholder="Asmens kodas"
                            className="form-control"
                            value={this.state.childPersonalCode}
                            onChange={this.childOnChange}
                            onInvalid={(e) => compensationInputValidator(e)}
                            required
                            pattern="[0-9]{11}"
                        />
                        </div>
                    </div>
                    {/** Gimimo data */}
                    <div className="mb-3">
                        <label htmlFor="txtBirthdate" className="form-label">
                            Gimimo data <span className="fieldRequired">*</span>
                        </label>
                        <DatePicker
                            className="form-control"
                            locale="lt"
                            id="chilBirthday"
                            dateFormat="yyyy/MM/dd"
                            placeholderText="YYYY-MM-DD"
                            selected={this.state.birthdate}
                            onChange={(e) => {
                                this.setState({ birthdate: e });
                            }}
                            minDate={subYears(new Date(), 6)}
                            maxDate={subYears(new Date(), 1)}
                        />
                    </div>
            </div>
        );
    }

    /** Privataus dar??elio forma */
    kindergartenForm() {
        return (
            <div className="form">
                <div>
                    <h6 className="formHeader">Dar??elio duomenys</h6>
                </div>
                <div className="row">
                    <div className="col-6">
                        <div className="mb-3">
                            <label htmlFor="txtKindergartenName"
                                className="form-label">
                                Ugdymo ??staigos pavadinimas <span className="fieldRequired">*</span>
                            </label>
                            <input
                                type="text"
                                title=""
                                id="txtKindergartenName"
                                name="kindergartenName"
                                placeholder="Pavadinimas"
                                className="form-control"
                                value={this.state.privateKindergarten.kindergartenName}
                                onChange={this.kindergartenOnChange}
                                onInvalid={(e) => compensationInputValidator(e)} 
                                required
                                pattern="[^\n]{2,70}"
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="txtKindergartenCode"
                                className="form-label">
                                Ugdymo ??staigos kodas <span className="fieldRequired">*</span>
                            </label>
                            <input
                                type="text"
                                title=""
                                id="txtKindergartenCode"
                                name="kindergartenCode"
                                placeholder="??staigos kodas"
                                className="form-control"
                                value={this.state.privateKindergarten.kindergartenCode}
                                onChange={this.kindergartenOnChange}
                                onInvalid={(e) => compensationInputValidator(e)}
                                required
                                pattern="[0-9]{2,20}"
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="txtKindergartenPhone" className="form-label">
                                Ugdymo ??staigos telefonas <span className="fieldRequired">*</span>
                            </label>
                            <input
                                type="tel"
                                title=""
                                id="txtKindergartenPhone"
                                name="kindergartenPhone"
                                placeholder="+37012345678"
                                className="form-control"
                                value={this.state.privateKindergarten.kindergartenPhone}
                                onChange={this.kindergartenOnChange}
                                onInvalid={(e) => compensationInputValidator(e)}
                                required
                                pattern="[+]{1}[0-9]{5,19}"
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="txtKindergartenEmail" className="form-label">
                                Ugdymo ??staigos el. pa??tas <span className="fieldRequired">*</span>
                            </label>
                            <input
                                type="text"
                                title=""
                                id="txtKindergartenEmail"
                                name="kindergartenEmail"
                                placeholder="El. pa??tas"
                                className="form-control"
                                value={this.state.privateKindergarten.kindergartenEmail}
                                onChange={this.kindergartenOnChange}
                                onInvalid={(e) => compensationInputValidator(e)}
                                required
                                pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}"
                            />
                        </div>
                    </div>
                    <div className="col-6">
                        <div className="mb-3">
                            <label htmlFor="txtKindergartenAddress" className="form-label">
                                Ugdymo ??staigos adresas <span className="fieldRequired">*</span>
                            </label>
                            <input
                                type="text"
                                title=""
                                className="form-control"
                                id="txtKindergartenAddress"
                                name="kindergartenAddress"
                                placeholder="Adresas"
                                value={this.state.privateKindergarten.kindergartenAddress}
                                onChange={this.kindergartenOnChange}
                                onInvalid={(e) => compensationInputValidator(e)}
                                required
                                pattern="[^\n]{2,70}"
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="txtKindergartenBankName"
                                className="form-label">
                                Banko pavadinimas <span className="fieldRequired">*</span>
                            </label>
                            <input
                                type="text"
                                title=""
                                id="txtKindergartenBankName"
                                name="kindergartenBankName"
                                placeholder="Banko pavadinimas"
                                className="form-control"
                                value={this.state.privateKindergarten.kindergartenBankName}
                                onChange={this.kindergartenOnChange}
                                onInvalid={(e) => compensationInputValidator(e)}
                                required
                                pattern="[^\n]{2,70}"
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="txtKindergartenAccountNumber"
                                className="form-label">
                                Ugdymo ??staigos s??skaitos numeris <span className="fieldRequired">*</span>
                            </label>
                            <input
                                type="text"
                                title=""
                                id="txtKindergartenAccountNumber"
                                name="kindergartenAccountNumber"
                                placeholder="LT00-0000-0000-0000-0000"
                                className="form-control"
                                value={this.state.privateKindergarten.kindergartenAccountNumber}
                                onChange={this.kindergartenOnChange}
                                onInvalid={(e) => compensationInputValidator(e)}
                                required
                                pattern="[L]{1}[T]{1}[0-9]{2}-[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="txtKindergartenBankCode"
                                className="form-label">
                                Banko kodas <span className="fieldRequired">*</span>
                            </label>
                            <input
                                type="text"
                                title=""
                                id="txtKindergartenBankCode"
                                name="kindergartenBankCode"
                                placeholder="Banko kodas"
                                className="form-control"
                                value={this.state.privateKindergarten.kindergartenBankCode}
                                onChange={this.kindergartenOnChange}
                                onInvalid={(e) => compensationInputValidator(e)}
                                required
                                pattern="[0-9]{5}"
                            />
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    /** Pagrindinio atstovo formos onChange */
    mainGuardianOnChange(e) {
        compensationInputValidator(e);
        this.setState({
            mainGuardian: {
                ...this.state.mainGuardian,
                [e.target.name]: e.target.value,
            },
        });
    }

    /** Vaiko formos onChange */
    childOnChange(e) {
        compensationInputValidator(e);
        this.setState({
            [e.target.name]: e.target.value,
        });
    }

    /** Privataus dar??elio formos onChange */
    kindergartenOnChange(e) {
        compensationInputValidator(e);
        this.setState({
            privateKindergarten: {
                ...this.state.privateKindergarten,
                [e.target.name]: e.target.value,
            },
        });
    }

    /** Handle submit */
    submitHandle(e) {
        e.preventDefault();
      
        const data = {
            birthdate: this.state.birthdate.toLocaleDateString("en-CA"),
            childName: this.state.childName,
            childPersonalCode: this.state.childPersonalCode,
            childSurname: this.state.childSurname,
            mainGuardian: this.state.mainGuardian,
            privateKindergarten: this.state.privateKindergarten
        }
        http
            .post(`${apiEndpoint}/api/naujas_kompensacija/user/new`, data)
            .then((response) => {
                swal({
                    text: `${response.data}`,
                    button: "Gerai",
                });
                this.props.history.push("/prasymai")
            })
            .catch((error) => {
                swal({
                    text: "??vyko klaida. " + error.response.data,
                    button: "Gerai"
                });
            });
    }
    


    render() {
        return (
            <div className="container pt-4">
                <div className="form">
                    <form onSubmit={this.submitHandle} >
                        <div className="row">
                            <div className="col-6">
                                {
                                    /** Atstovas */
                                    this.userForm()
                                }
                            </div>

                            <div className="col-6">
                                {
                                    /** Vaiko forma */
                                    this.childForm()
                                }
                            </div>
                        </div>

                        <div className="row">
                            <div className="col">
                                {
                                    /** Privataus dar??elio forma */
                                    this.kindergartenForm()
                                }
                            </div>
                        </div>
                        <div className="row">
                            <div className="col">
                                <button type="submit" className="btn btn-primary mt-3" >
                                    Sukurti pra??ym??
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
        }
    }              

export default withRouter(CreateCompensationApplicationFormContainer);

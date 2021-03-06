import React from "react";
import axios from "axios";
import { useHistory } from "react-router-dom";
import AdminCreateUser from "../04Admin/AdminCreateUser"
import "../../App.css";
import { NavLink } from 'react-router-dom';

import apiEndpoint from "../10Services/endpoint";
import AuthContext from "../11Context/AuthContext";
import logo from "../../images/logo.png";
import swal from "sweetalert";

import ForgotPasswordWindow from "../01Login/ForgotPasswordWindow";

axios.defaults.withCredentials = true;

export const LoginContainer = () => {
  const initState = {
    username: "",
    password: "",
    loginError: false,
    loggingIn: false,
  };

  const [data, setData] = React.useState(initState);
  const { dispatch } = React.useContext(AuthContext);
  const history = useHistory();

  const loginInstance = axios.create();

  loginInstance.interceptors.response.use(
    (response) => response,
    async (error) => {
      const expectedError =
        error.response &&
        error.response.status >= 400 &&
        error.response.status < 500;
      if (!expectedError) {
        swal("Įvyko klaida, puslapis nurodytu adresu nepasiekiamas");
        dispatch({
          type: "ERROR",
          payload: null,
        });
        setData({
          ...data,
          loginError: false,
          loggingIn: false,
          username: "",
          password: "",
        });
      } else if (error.response) {
       if (error.response.status === 401) {
        setData({
          ...data,
          loginError: true,
          loggingIn: false,
          username: "",
          password: "",
        });
      } else if (error.response.status === 403){
        swal("Prieiga uždrausta")
        setData({
          ...data,
          loginError: false,
          loggingIn: false,
          username: "",
          password: "",
        });
      } 
    }
    } 
  );

  const handleChange = (event) => {
    validateText(event);
    setData({
      ...data,
      loginError: false, // po nesėkmingo įvedimo pradėjus vesti duomenis iš naujo, paslepia klaidos pranešimą
      [event.target.name]: event.target.value,
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setData({
      ...data,
      loginError: false,
      loggingIn: true,
    });
    let userData = new URLSearchParams();
    userData.append("username", data.username);
    userData.append("password", data.password);
    loginInstance
      .post(`${apiEndpoint}/login`, userData, {
        headers: { "Content-type": "application/x-www-form-urlencoded" },
      })
      .then((resp) => {
        dispatch({
          type: "LOGIN",
          payload: resp.data,
        });
        history.push("/home");
      })
     .catch(() => {})
  };

  const validateText = (event) => {
    const target = event.target;

    if (target.validity.valueMissing && target.id === "username") {
      target.setCustomValidity("Būtina įvesti naudotojo prisijungimo vardą");
    } else if (target.validity.valueMissing && target.id === "password") {
      target.setCustomValidity("Būtina įvesti slaptažodį");
    } else {
      target.setCustomValidity("");
    }
  };

  return (
    <div className="d-flex flex-column justify-content-center align-items-center min-vh-100">
      <div className="card p-5">
      {/* Dropped form-specific layout classes for our grid system. Use our grid and utilities instead 
      of .form-group, .form-row, or .form-inline. */}
          {/* Form labels now require .form-label. */}
        <img
          src={logo}
          alt="Vilniaus miesto savivaldybės vaikų darželių informacinė sistema"
          className="img-flex mb-3"
        />
        <form onSubmit={handleSubmit}>
          <h3>Prisijungti</h3>
          <div className="m-3">
            <label htmlFor="username" className="form-label">
              Naudotojo vardas <span className="fieldRequired">*</span>
            </label>
            <input
              type="text"
              className="form-control"
              name="username"
              id="username"
              value={data.username}
              onChange={handleChange}
              onInvalid={validateText}
              required
              data-toggle="tooltip"
              data-placement="top"
              title="Įveskite naudotojo prisijungimo vardą"
            />
          </div>

          <div className="m-3">
            <label htmlFor="password" className="form-label">
              Slaptažodis <span className="fieldRequired">*</span>
            </label>
            <input
              type="password"
              className="form-control"
              name="password"
              id="password"
              value={data.password}
              onChange={handleChange}
              onInvalid={validateText}
              required
              data-toggle="tooltip"
              data-placement="top"
              title="Įveskite naudotojo slaptažodį"
            />
          </div>

          <button
            type="button"
            className="btn btn-link"
            style={{ paddingLeft: "0px" }}
            onClick={() => {
              return ForgotPasswordWindow();
            }}
            formNoValidate
          >
            Pamiršau slaptažodį
          </button>
        {/* Renamed .float-left and .float-right to .float-start and .float-end. */}
          <button
            type="submit"
            className="btn btn-primary float-end"
            id="btnLogin"
            disabled={data.loggingIn}
          >
            {data.loggingIn ? "Jungiamasi..." : "Prisijungti"}
          </button>
        </form>
        {data.loginError && (
          <span
            className="alert alert-danger mt-3"
            role="alert"
            id="incorrectLoginData"
          >
            Neteisingas prisijungimo vardas ir/arba slaptažodis!
          </span>
        )}
      </div>
      <div className="row">
        <div className="col">
          <h6 className="py-3">Nesate prisiregistravę?
          <NavLink className="nav-link" id="createNewAccount" to={"/newAccount"}>Sukurti paskyrą</NavLink>
          </h6>
          
        </div>
      </div>
    </div>
  );
};

export default LoginContainer;

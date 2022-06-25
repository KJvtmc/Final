import React from 'react';
import { NavLink } from 'react-router-dom';

import logo from '../../images/logo.png';
import '../../App.css';

import LogoutContainer from './LogoutContainer';

function Navigation(props) {
    return (
        <div className="pb-4" >
            <nav className="navbar navbar-expand-lg py-4 navbar-light bg-light">
            {/* Renamed .ml-* and .mr-* to .ms-* and .me-*. */}
                <div className="container">

                    <NavLink className="navbar-brand" to={"/home"}>
                        <img className="nav-img" src={logo} alt="logotipas" loading="lazy" />
                    </NavLink>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav ms-auto ">


                            <li className="dropdown nav-item me-1 container-flexible">
                                <button className=" btn btn-outline-light btn-sm  dropdown-toggle dropdown-button text-wrap px-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">Darželių eilės prašymai
                                </button>
                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li><NavLink className="nav-link dropdown-item" id="navManagerApplicationQueue" to={"/eile"}>Prašymų eilė</NavLink></li> 
                                    <li><NavLink className="nav-link dropdown-item" id="navManagerKindergartenList" to={"/darzeliai"}>Darželių sąrašas</NavLink></li>  
                                    <li><NavLink className="nav-link dropdown-item" id="navManagerApplicationStats" to={"/statistika"}>Prašymų statistika</NavLink></li>                              </ul>
                            </li>

                            <li className="nav-item me-2">
                                <NavLink className="nav-link" id="navManagerDocumentContainer" to={"/dokumentai"}>Pažymos</NavLink>
                            </li>

                            <li className="nav-item me-2">
                                <NavLink className="nav-link" id="navManagerCompensationsList" to={"/kompensacijos"}>Kompensacijų prašymai</NavLink>
                            </li>
                            
                            <li className="nav-item me-2">
                                <NavLink className="nav-link" id="navManagerMyAccount" to={"/profilis/atnaujinti"}>Mano paskyra</NavLink>
                            </li>

                            <li className="nav-item nav-item me-2" id="navManagerLogout">
                                <LogoutContainer />
                            </li>

                        </ul>

                    </div>
                </div>
            </nav>
            <div>{props.children}</div>
        </div >

    );

}

export default Navigation;
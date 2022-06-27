import React from 'react';
import { NavLink } from 'react-router-dom';

import logo from '../../images/logo.png';
import '../../App.css';

import LogoutContainer from './LogoutContainer';

function Navigation(props) {
    return (
        <div className="pb-4" >
            <nav className="navbar navbar-expand-lg py-4 navbar-light bg-light">

                <div className="container">

                    <NavLink className="navbar-brand" to={"/home"}>
                        <img className="nav-img" src={logo} alt="logotipas" loading="lazy" />
                    </NavLink>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav ms-auto ">
                    {/* Renamed .ml-* and .mr-* to .ms-* and .me-*. */}
                            <li className="nav-item me-2">
                                <NavLink className="nav-link" id="navAdminUserList" to={"/admin"}>Naudotojai</NavLink>
                            </li>

                            <li className="nav-item me-2">
                                <NavLink className="nav-link" id="navAdminProviders" to={"/tiekejai"}>Tiekėjai</NavLink>
                            </li>

                            <li className="nav-item me-2">
                                <NavLink className="nav-link" id="navAdminGroups" to={"/grupes"}>Grupės</NavLink>
                            </li>

                            <li className="nav-item me-2">
                                <NavLink className="nav-link" id="navAdminItems" to={"/produktai"}>Produktai</NavLink>
                            </li>

                            <li className="nav-item me-2">
                                <NavLink className="nav-link" id="navAdminCompensationsList" to={"/prasymai"}>Prašymai</NavLink>
                            </li>
                            
                            <li className="nav-item me-2">
                                <NavLink className="nav-link" id="navAdminMyAccount" to={"/profilis/atnaujinti"}>Mano paskyra</NavLink>
                            </li>

                            <li className="nav-item me-2">
                                <NavLink className="nav-link" id="navAdminEventLog" to={"/zurnalas"}>Įvykių žurnalas</NavLink>
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

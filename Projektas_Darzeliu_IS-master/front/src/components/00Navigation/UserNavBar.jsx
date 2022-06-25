import React from 'react';
import { NavLink } from 'react-router-dom';

import logo from '../../images/logo.png';
import '../../App.css';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faQuestionCircle } from '@fortawesome/free-solid-svg-icons'

import instructionsPdf from '../../documents/VMS_VDIS_naudotojo_gidas.pdf';

import LogoutContainer from './LogoutContainer';


function Navigation(props) {

    function handleRefreshForApplication(url) {
        if (url === "/darzelis/prasymai/naujas_registracija") {
            window.location.reload()
        }
    }
    function handleRefreshForCompensation(url) {
        if (url === "/darzelis/prasymai/naujas_kompensacija") {
            window.location.reload()
        }
    }
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


                         {/* Added dropdown to link different type of applications */}
                            <li className="dropdown nav-item me-1">
                                <button className=" btn btn-outline-light btn-sm  dropdown-toggle dropdown-button text-wrap px-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                Sukurti prašymą
                                </button>
                                    <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li><NavLink className="nav-link dropdown-item" id="navUserNewApplicationEnter" onClick={() => handleRefreshForApplication(window.location.pathname)} to={"/prasymai/naujas_registracija"}>Registracijai į darželį</NavLink></li>
                                    <li><NavLink className="nav-link dropdown-item" id="navUserNewApplicationGet" onClick={() => handleRefreshForCompensation(window.location.pathname)} to={"/prasymai/naujas_kompensacija"}>Gauti kompensaciją</NavLink></li>
                                </ul>
                            </li>

                            <li className="nav-item me-1">
                                <NavLink className="nav-link" id="navUserMyApplications" to={"/prasymai"}>Mano prašymai</NavLink>
                            </li>

                            <li className="nav-item me-1">
                                <NavLink className="nav-link" id="navUserDocuments" to={"/pazymos"}>Mano pažymos</NavLink>
                            </li>

                            <li className="nav-item me-1">
                                <NavLink className="nav-link" id="navUserApplicationStats" to={"/statistika"}>Prašymų statistika</NavLink>
                            </li>

                            <li className="nav-item me-1">
                                <NavLink className="nav-link" id="navUserMyAccount" to={"/profilis/atnaujinti"}>Mano paskyra</NavLink>
                            </li>

                            <li className="nav-item me-1">
                                <a className="nav-link"
                                id="navInstructions"
                                target="_blank"
                                rel="noopener noreferrer"
                                href={instructionsPdf}
                                title="Parsisiųsti naudotojo instrukciją"
                            >
                                <FontAwesomeIcon icon={faQuestionCircle} />
                                </a>
                            </li>

                            <li className="nav-item nav-item me-2">
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

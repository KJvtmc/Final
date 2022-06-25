import React, { Component } from 'react';

import '../../App.css';

import CreateNewAccount from './CreateNewAccount';


export class NewAccountContainer extends Component {


    render() {
        return (
            <div>
                <div className="container pt-4">
                    
                    <div className="row ">
                        <div >
                            <CreateNewAccount />
                        </div>




                    </div>

                </div>


            </div>
        )
    }
}

export default NewAccountContainer

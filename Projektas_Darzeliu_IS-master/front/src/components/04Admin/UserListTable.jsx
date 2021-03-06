import React, { Component } from 'react';

import '../../App.css';

import Table from '../08CommonComponents/Table';
//import Modal from '../08CommonComponents/Modal';

class UserListTable extends Component {

    columns = [
        {                              
            key: 'username',
            path: 'username',
            label: 'Naudotojo vardas',
            content: naudotojas => <span>{naudotojas.username}</span>
        },

        {            
            key: 'role',
            path: 'role',
            label: 'Naudotojo rolė',
            content: naudotojas => <span>{naudotojas.role === "ADMIN" ? "Administratorius" : (naudotojas.role === "USER" ? "Vaiko atstovas" : "Švietimo specialistas")}</span>
        },

       
        {            
            key: 'update',
            label: 'Pirminis slaptažodis',
            content: naudotojas => {
                if(naudotojas.isRequestingPasswordReset) {
                    return (
                        <button onClick={() => this.props.onRestorePassword(naudotojas)} id="btnRestoreUserPassword" className="btn btn-secondary btn-sm w-100"><b>Atkurti</b></button>
                    )
                }
                else {
                    return (
                        <button onClick={() => this.props.onRestorePassword(naudotojas)} id="btnRestoreUserPassword" className="btn btn-outline-primary btn-sm w-100">Atkurti</button>
                    )
                }
            }
        },
       
        {            
            key: 'delete',
            label: 'Ištrinti naudotoją',
            content: naudotojas => <button onClick={() => this.props.onDelete(naudotojas)} id="btnDeleteUser" className="btn btn-outline-danger btn-sm w-100">Ištrinti</button>
           
        }
        
    ]


    render() {
        const { naudotojai } = this.props;
        return (
            <Table
                columns={this.columns}
                data={naudotojai}

            />
        );
    }
}


export default UserListTable;
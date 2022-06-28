import React, { Component } from 'react';
import NotFoundMessage from '../08CommonComponents/NotFoundMessage';

import '../../App.css';
class BookListTable extends Component {

    render() {
        const {
            knygos,
            kategorijos,
            inEditMode,
            editRowId,
            errorMessages,
            hasErrors,
            onEditData,
            onSave,
            onChange,
            onDelete,
            search } = this.props;
        if (search !== "" && knygos.length === 0) {
            return (
                <NotFoundMessage message="Darželių pagal įvestą pavadinimą nerasta" />
            )
        } else {
            return (
                <div className="table-responsive-md">

                    <table className="table" >

                        <thead className="no-top-border">
                            <tr >
                                <th>Pavadinimas</th>
                                <th>Kodas</th>
                                <th>Aprašymas</th>
                                <th>Puslapių skaičius</th>
                                <th>Kategorija</th>
                                <th>Išsaugoti į mėgstamų knyg sąrašą</th>
                                <th>Užsakyti</th>
                            </tr>
                        </thead>
                        <tbody >
                            {
                                knygos.map((knyga) => {
                                if(knyga.order===null)
                                {return(

                                    <tr key={knyga.code}>


                                        <td>{knyga.name}</td>
                                        <td>{knyga.code}</td>
                                        <td>{knyga.description}</td>
                                        <td>{knyga.pages}</td>
                                        <td>{knyga.serviceGroup}</td>

                                        <td>
                                            <button
                                                className="btn btn-outline-primary btn-sm btn-block"
                                                id="btnUpdateKindergarten"
                                                onClick={() => onEditData(knyga)}>
                                                Išsaugoti
                                            </button>
                                        </td>

                                        <td>
                                            <button
                                                onClick={() => onDelete(knyga)}
                                                id="btnDeleteKindergarten"
                                                className="btn btn-outline-danger btn-sm btn-block">
                                                Rezervuoti
                                            </button>
                                        </td>
                                    </tr>)}
                                   
                                   }
                                )
                            }
                        </tbody>
                    </table>
                </div>

            );
        }
    }
}

export default BookListTable;

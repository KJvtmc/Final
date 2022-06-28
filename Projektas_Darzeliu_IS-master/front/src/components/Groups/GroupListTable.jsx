import React, { Component } from 'react';
import NotFoundMessage from '../08CommonComponents/NotFoundMessage';

import '../../App.css';
class GroupListTable extends Component {

    render() {
        const {
            groups,
            inEditMode,
            editRowId,
            errorMessages,
            hasErrors,
            onEditData,
            onSave,
            onChange,
            onDelete,
            search } = this.props;
        if (search !== "" && groups.length === 0) {
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
                                <th>Redaguoti duomenis</th>
                                <th className="deleteColumn">Ištrinti darželį</th>
                            </tr>
                        </thead>
                        <tbody >
                            {
                                groups.map((darzelis) => (

                                    <tr key={darzelis.id}>
                                        {inEditMode && editRowId === darzelis.id ?
                                            (
                                                <React.Fragment>
                                                    <td >
                                                        <input
                                                            type="text"
                                                            className="form-control"
                                                            id="txtKindergartenName"
                                                            name="name"
                                                            value={darzelis.name}
                                                            onChange={(event) => onChange(event)}
                                                            placeholder="Pavadinimas"
                                                            title="Pavadinimas turi būti 3-50 simbolių ir negali prasidėti tarpu"
                                                            required
                                                        // minLength="3"
                                                        // maxLength="50"
                                                        // pattern="[^ ][A-zÀ-ž0-9\x22 \-'.,]*"
                                                        />
                                                        {errorMessages.name && <div className="text-danger">{errorMessages.name}</div>}
                                                    </td>
                                                   
                                                    <td>
                                                        <button
                                                            type="submit"
                                                            className="btn btn-primary btn-sm btn-block"
                                                            id="btnSaveUpdatedKindergarten"
                                                            onClick={() => onSave({ id: darzelis.id, item: darzelis })}
                                                            disabled={hasErrors}
                                                            >
                                                            Saugoti
                                                        </button>
                                                    </td>
                                                </React.Fragment>
                                            ) : (
                                                <React.Fragment>
                                                    <td>{darzelis.name}</td>
                                                    <td>
                                                        <button
                                                            className="btn btn-outline-primary btn-sm btn-block"
                                                            id="btnUpdateKindergarten"
                                                            onClick={() => onEditData(darzelis)}>
                                                            Redaguoti
                                                        </button>
                                                    </td>
                                                </React.Fragment>
                                            )}
                                        <td>
                                            <button
                                                onClick={() => onDelete(darzelis)}
                                                id="btnDeleteKindergarten"
                                                className="btn btn-outline-danger btn-sm btn-block">
                                                Ištrinti
                                            </button>
                                        </td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>

            );
        }
    }
}

export default GroupListTable;

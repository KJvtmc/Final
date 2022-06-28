import React, { Component } from 'react';
import NotFoundMessage from '../08CommonComponents/NotFoundMessage';

import '../../App.css';
class KindergartenListTable extends Component {

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
                                <th>Redaguoti duomenis</th>
                                <th className="deleteColumn">Ištrinti darželį</th>
                            </tr>
                        </thead>
                        <tbody >
                            {
                                knygos.map((darzelis) => (

                                    <tr key={darzelis.code}>
                                        {inEditMode && editRowId === darzelis.code ?
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
                                                        <input
                                                            type="text"
                                                            className="form-control"
                                                            id="txtKindergartenDirector"
                                                            name="code"
                                                            value={darzelis.code}
                                                            onChange={(event) => onChange(event)}
                                                            placeholder="ISBN"
                                                            title="Neteisingo formato vardas ir pavardė"
                                                            required

                                                        />
                                                        {errorMessages.code && <div className="text-danger">{errorMessages.code}</div>}
                                                    </td>
                                                    <td>
                                                        <input
                                                            type="text"
                                                            className="form-control"
                                                            id="description"
                                                            name="description"
                                                            value={darzelis.description}
                                                            onChange={(event) => onChange(event)}
                                                            title="Negali būti mažiau nei 0 ir daugiau nei 999"
                                                            required
                                                        />
                                                        {errorMessages.description && <div className="text-danger">{errorMessages.description}</div>}
                                                    </td>
                                                    <td>
                                                        <input
                                                            type="text"
                                                            className="form-control"
                                                            id="pages"
                                                            name="pages"
                                                            value={darzelis.pages}
                                                            onChange={(event) => onChange(event)}
                                                            title="Negali būti mažiau nei 0 ir daugiau nei 999"
                                                            required
                                                        />
                                                        {errorMessages.pages && <div className="text-danger">{errorMessages.pages}</div>}
                                                    </td>
                                                    <td>
                                                        <select
                                                            type="text"
                                                            className="form-select"
                                                            name="serviceGroup"
                                                            id="serviceGroup"
                                                            value={darzelis.serviceGroup}
                                                            // onChange={(event) => setSelectedGroup(event.target.value)}
                                                            // required
                                                            placeholder="serviceGroup"
                                                            data-toggle="tooltip"
                                                            data-placement="top"
                                                            title="Pasirinkite"
                                                        >
                                                            <option value="" disabled hidden label="Pasirinkite" />
                                                            {kategorijos.map((option) => (
                                                                <option value={option.name} label={option.name} key={option.name} />
                                                            ))}
                                                        </select>
                                                        {/* {errorMessages.elderate && <div className="text-danger">{errorMessages.elderate}</div>} */}
                                                    </td>
                                                    <td>
                                                        <button
                                                            type="submit"
                                                            className="btn btn-primary btn-sm btn-block"
                                                            id="btnSaveUpdatedKindergarten"
                                                            onClick={() => onSave({ id: darzelis.code, item: darzelis })}
                                                            disabled={hasErrors}>
                                                            Saugoti
                                                        </button>
                                                    </td>
                                                </React.Fragment>
                                            ) : (
                                                <React.Fragment>
                                                    <td>{darzelis.name}</td>
                                                    <td>{darzelis.code}</td>
                                                    <td>{darzelis.description}</td>
                                                    <td>{darzelis.pages}</td>
                                                    <td>{darzelis.serviceGroup}</td>
                                                    <td>{
                                                        console.log(darzelis)
                                                    // darzelis.serviceGroup.map(g => (<p>{g.name}</p>)) 
                                                    }</td>
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

export default KindergartenListTable;

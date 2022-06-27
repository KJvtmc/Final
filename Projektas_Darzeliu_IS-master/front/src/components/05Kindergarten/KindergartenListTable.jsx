import React, { Component } from 'react';
import NotFoundMessage from '../08CommonComponents/NotFoundMessage';

import '../../App.css';
class KindergartenListTable extends Component {

    render() {
        const {
            darzeliai,
            elderates,
            inEditMode,
            editRowId,
            errorMessages,
            hasErrors,
            onEditData,
            onSave,
            onChange,
            onDelete,
            search } = this.props;
        if (search !== "" && darzeliai.length === 0) {
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
                                <th>Adresas</th>
                                <th>Kodas</th>
                                <th>Telefonas</th>
                                <th>El. paštas</th>
                                <th>Grupės</th>
                                <th>Redaguoti duomenis</th>
                                <th className="deleteColumn">Ištrinti darželį</th>
                            </tr>
                        </thead>
                        <tbody >
                            {
                                darzeliai.map((darzelis) => (

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
                                                            id="txtKindergartenAddress"
                                                            name="address"
                                                            value={darzelis.address}
                                                            onChange={(event) => onChange(event)}
                                                            placeholder="Adresas"
                                                            title="Adresas privalomas"
                                                            required
                                                        // pattern="[^\n]{3,50}"
                                                        />
                                                        {errorMessages.address && <div className="text-danger">{errorMessages.address}</div>}
                                                    </td>

                                                    <td>
                                                        <input
                                                            type="text"
                                                            className="form-control"
                                                            id="txtKindergartenDirector"
                                                            name="code"
                                                            value={darzelis.code}
                                                            onChange={(event) => onChange(event)}
                                                            placeholder="Direktorius"
                                                            title="Neteisingo formato vardas ir pavardė"
                                                            required
                                                        // pattern="^(?:\p{L}{2,70})+((-)*( )*(?:\p{L}+))*$"
                                                        />
                                                        {errorMessages.code && <div className="text-danger">{errorMessages.code}</div>}
                                                    </td>
                                                    <td>
                                                        <input
                                                            type="text"
                                                            className="form-control"
                                                            id="phone"
                                                            name="phone"
                                                            value={darzelis.phone}
                                                            onChange={(event) => onChange(event)}
                                                            title="Negali būti mažiau nei 0 ir daugiau nei 999"
                                                            required
                                                        />
                                                        {errorMessages.phone && <div className="text-danger">{errorMessages.phone}</div>}
                                                    </td>
                                                    <td>
                                                        <input
                                                            type="text"
                                                            className="form-control"
                                                            id="email"
                                                            name="email"
                                                            value={darzelis.email}
                                                            onChange={(event) => onChange(event)}
                                                            title="Negali būti mažiau nei 0 ir daugiau nei 999"
                                                            required
                                                        />
                                                        {errorMessages.email && <div className="text-danger">{errorMessages.email}</div>}
                                                    </td>
                                                    <td>
                                                        <select
                                                            type="text"
                                                            className="form-select"
                                                            name="selectedGroup"
                                                            id="selectedGroup"
                                                            value={""}
                                                            // onChange={(event) => setSelectedGroup(event.target.value)}
                                                            // required
                                                            placeholder="serviceGroup"
                                                            data-toggle="tooltip"
                                                            data-placement="top"
                                                            title="Pasirinkite"
                                                        >
                                                            <option value="" disabled hidden label="Pasirinkite" />
                                                            {elderates.map((option) => (
                                                                <option value={option.name} label={option.name} key={option.name} />
                                                            ))}
                                                        </select>
                                                        {errorMessages.elderate && <div className="text-danger">{errorMessages.elderate}</div>}
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
                                                    <td>{darzelis.address}</td>
                                                    <td>{darzelis.code}</td>
                                                    <td>{darzelis.phone}</td>
                                                    <td>{darzelis.email}</td>
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

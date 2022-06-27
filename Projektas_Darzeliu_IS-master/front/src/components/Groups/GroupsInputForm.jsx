import React, { useState, useEffect } from "react";
import { useHistory } from 'react-router-dom';
import "../../App.css";
import http from "../10Services/httpService";
import apiEndpoint from "../10Services/endpoint";
import swal from "sweetalert";

function GroupsInputForm() {
  const initKindergartenData = {
    description: "",
    id: 0,
    name: "",
    serviceItems:[],
  };


  var savingStatus = false;

  const [data, setData] = useState(initKindergartenData);
  const [groups, setGroups] = useState([])
  const history = useHistory();

  // useEffect(() => {
  //   http
  //     .get(`${apiEndpoint}/api/serviceGroup/manager/groups`)
  //     .then((response) => {
  //       console.log(response);
  //       if (!response.data)
  //       setGroups(response.data);
  //     })
  //     .catch((error) => {
  //       swal({
  //         text: "Įvyko klaida nuskaitant. " + error.response.data,
  //         button: "Gerai",
  //       });
  //     });
  // }, [setGroups]);

  const handleSubmit = (event) => {
    event.preventDefault();
    //console.log("saugoma į serverį");
    //console.log(data);
    savingStatus = true;
    http
      .post(`${apiEndpoint}/api/ServiceGroup/manager/createServiceGroup`, data)
      .then((response) => {
        console.log(data);
        console.log(response);
        swal({
          text: "Naujas produktas  pridėtas sėkmingai!",
          button: "Gerai",
        });
        savingStatus = false;
        resetForm(event);
        history.push("/new");
        history.replace("/grupes")
      })
      .catch((error) => {
        if (error.response.status === 409) {
          swal({
            text:
              "Įvyko klaida įrašant naują darželį. " +
              error.response.data +
              "\n\nPatikrinkite duomenis ir bandykite dar kartą",
            button: "Gerai",
          });
        }
        savingStatus = false;
      });
  };

  const validateField = (event) => {
    const target = event.target;

  //   if (target.validity.valueMissing) {
  //     if (target.id === "elderate") {
  //       target.setCustomValidity("Reikia pasirinkti seniūniją");
  //     } else target.setCustomValidity("Būtina užpildyti šį laukelį");
  //   } else if (target.validity.patternMismatch) {
  //     if (target.id === "id")
  //       target.setCustomValidity("Įstaigos kodą turi sudaryti 9 skaitmenys");
  //     if (target.id === "name")
  //       target.setCustomValidity("Pavadinimas turi būti 3-50 simbolių ir negali prasidėti tarpu");
  //     if (target.id === "address")
  //       target.setCustomValidity("Adresas turi būti 3-50 simbolių");
  //     if (target.id === "director")
  //       target.setCustomValidity("Netinkamo formato vardas ir pavardė");
  //   } else if (target.validity.rangeUnderflow || target.validity.rangeOverflow) {
  //     target.setCustomValidity("Negali būti mažiau nei 0 ir daugiau nei 999");

  //   } else {
  //     target.setCustomValidity("");
  //   }
  };

  const handleChange = (event) => {
    // validateField(event);
   if(event.target.name ==="id"){
    console.log(event.target.value)
    setData({
      ...data,
      [event.target.name]: 1*event.target.value,
    })
   }else{
    setData({
      ...data,
      [event.target.name]: event.target.value,
    });
   }
    
    
  };

  const resetForm = (event) => {
    event.preventDefault();
    setData(initKindergartenData);
  };

  return (
    <div>
      <form onSubmit={handleSubmit} onReset={resetForm}>
        <h6 className="py-3">
          <b>Pridėti naują produktą </b>
        </h6>
        {/* <div className="mb-3">
          <label htmlFor="id" className="form-label">
            Kodas <span className="fieldRequired">*</span>
          </label>
          <input
            type="number"
            className="form-control"
            name="id"
            id="id"
            value={data.id}
            onChange={handleChange}
            onInvalid={validateField}
            required

            placeholder="123456789"
            data-toggle="tooltip"
            data-placement="top"
            title="Įveskite įstaigos (darželio) kodą (9 skaitmenys)"
          />
        </div> */}

        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Pavadinimas <span className="fieldRequired">*</span>
          </label>
          <input
            type="text"
            className="form-control "
            name="name"
            id="name"
            value={data.name}
            onChange={handleChange}
            onInvalid={validateField}
            required
            // minLength="3"
            // maxLength="50"
            // pattern="[^ ][A-zÀ-ž0-9\x22 \-'.,]*"
            placeholder="3-50 simbolių"
            data-toggle="tooltip"
            data-placement="top"
            title="Įveskite darželio pavadinimą (nuo 3 iki 50 simbolių)"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="description" className="form-label">
            Aprašymas <span className="fieldRequired">*</span>
          </label>
          <input
            type="text"
            className="form-control"
            name="description"
            id="description"
            value={data.description}
            onChange={handleChange}
            onInvalid={validateField}
            required
            placeholder="Aprašymas"
            data-toggle="tooltip"
            data-placement="top"
            title="Įveskite darželio aprašymą"
            // pattern="[^\n]{3,50}"
          />
        </div>

        {/* <div className="mb-3">
          <label htmlFor="elderate" className="form-label">
            Grupė <span className="fieldRequired">*</span>
          </label>
          <select
            type="text"
            className="form-select"
            name="group"
            id="group"
            value={data.serviceGroup}
            onChange={handleChange}
            onInvalid={validateField}
            // required
            placeholder="serviceGroup"
            data-toggle="tooltip"
            data-placement="top"
            title="Pasirinkite"
          >
            <option value="" disabled hidden label="Pasirinkite" />
            {groups.map((option) => (
              <option value={option} label={option} key={option} />
            ))}
          </select>
        </div> */}
        

        <button
          type="reset"
          className="btn btn-outline-danger ms-2 mb-3"
          id="btnClearKindergartenForm"
        >
          Išvalyti
        </button>
        <button
          type="submit"
          className="btn btn-primary mb-3"
          id="btnSaveKindergarten"
          disabled={savingStatus}
        >
          {savingStatus ? "Pridedama..." : "Pridėti"}
        </button>
      </form>
    </div>
  );
}

export default GroupsInputForm;

import React, { useState, useEffect } from "react";
import { useHistory } from 'react-router-dom';
import "../../App.css";
import http from "../10Services/httpService";
import apiEndpoint from "../10Services/endpoint";
import swal from "sweetalert";
import { Row } from "react-bootstrap";

function KindergartenInputForm() {
  const initKindergartenData = {
    code: "",
    name: "",
    address: "",
    phone: "",
    email: "",
    serviceGroup:[]
  };

  var [selectedGroup, setSelectedGroup] = useState();
  var savingStatus = false;

  const [data, setData] = useState(initKindergartenData);
  const [groups, setGroups] = useState([])
  const history = useHistory();

  useEffect(() => {
    console.log("use")
    http
    .get(`${apiEndpoint}/api/ServiceGroup/manager/groups`)
    .then((response) => {
        // console.log(response)
        setGroups(response.data);
      })
      .catch((error) => {
        swal({
          text: "Įvyko klaida nuskaitant. ",
          button: "Gerai",
        });
      });
  }, []);



  const handleSubmit = (event) => {
    console.log("handleSubmit")
    event.preventDefault();
    //console.log("saugoma į serverį");
    console.log(data);
    savingStatus = true;
    http
      .post(`${apiEndpoint}/api/serviceProvider/manager/createServiceProvider`, data)
      .then((response) => {
        //console.log("įrašyta: " + response.data);
        swal({
          text: "Naujas darželis „" + data.name + "“ pridėtas sėkmingai!",
          button: "Gerai",
        });
        savingStatus = false;
        resetForm(event);
        history.push("/new");
        history.replace("/tiekejai")
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
    console.log("handleChange")
    setData({
      ...data,
      [event.target.name]: event.target.value,
    });
    
  };

  // useEffect(() => {
  //   getList()
  // }, [data])
  
  // function getList (){
  //   return (
  //   <li>

  //   {data.serviceGroup.map((group)=>(
  //     <ul key={group.name}>{group.name}</ul>
  //   ))}
    
  // </li>)
  
  // }
  useEffect(() => {
    addGroup()
  }, [selectedGroup])

  function addGroup () {
    console.log("addGroup")
    let group = groups.find(g=>g.name === selectedGroup)
    if (group){
    let groupsNew  =[...data.serviceGroup]
    groupsNew.push(group)
    setData({
      ...data,
      serviceGroup: [...groupsNew],
    });
    }
  }

  const removeGroup = (e, name) =>{
    e.preventDefault()
    console.log("removeGroup")
    let group = groups.find(g=>g.name === name)
    if (group){
    let groupsNew  =[...data.serviceGroup]
    groupsNew  =groupsNew.filter(gr=>gr.name!==name)
    setData({
      ...data,
      serviceGroup: [...groupsNew],
    });
    }
  }


  const resetForm = (event) => {
    event.preventDefault();
    setData(initKindergartenData);
  };

  console.log("prerender")
  // console.log(data)
  return (
    <div>
      <form onSubmit={handleSubmit} onReset={resetForm}>
        <h6 className="py-3">
          <b>Pridėti naują knygą </b>
        </h6>
        <div className="mb-3">
          <label htmlFor="id" className="form-label">
            Knygos ISBN kodas <span className="fieldRequired">*</span>
          </label>
          <input
            type="text"
            className="form-control"
            name="code"
            id="code"
            value={data.code}
            onChange={handleChange}
            onInvalid={validateField}
            required
            // pattern="\d{9}"
            placeholder="123456789-0"
            data-toggle="tooltip"
            data-placement="top"
            title="Įveskite įstaigos (darželio) kodą (9 skaitmenys)"
          />
        </div>

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
            minLength="3"
            maxLength="50"
            pattern="[^ ][A-zÀ-ž0-9\x22 \-'.,]*"
            placeholder="3-50 simbolių"
            data-toggle="tooltip"
            data-placement="top"
            title="Įveskite darželio pavadinimą (nuo 3 iki 50 simbolių)"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="address" className="form-label">
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
            title="Įveskite darželio adresą"
            pattern="[^\n]{3,50}"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="director" className="form-label">
            Puslapių skaičius <span className="fieldRequired">*</span>
          </label>
          <input
            type="number"
            className="form-control"
            name="pages"
            id="pages"
            value={data.pages}
            onChange={handleChange}
            onInvalid={validateField}
            required
            placeholder="100"
            data-toggle="tooltip"
            data-placement="top"
            title="Įveskite telefoną"
          />
        </div>

        <div className="mb-3">
          <label htmlFor="selectedGroup" className="form-label">
            Grupė <span className="fieldRequired">*</span>
          </label>
          <div className="row">
          <div className="col">
            <select
            type="text"
            className="form-select"
            name="selectedGroup"
            id="selectedGroup"
            value={selectedGroup}
            onChange={(event)=>setSelectedGroup(event.target.value)}
            // onInvalid={validateField}
            // required
            placeholder="serviceGroup"
            data-toggle="tooltip"
            data-placement="top"
            title="Pasirinkite"
          >

            {groups.map((option) => (
              <option value={option.name} label={option.name} key={option.id} />
            ))}
          </select>
          </div>
          <div className="col-4">
          
          </div>
          </div>

        </div>
        <ul>
          {data.serviceGroup.map((option) => (
              <div key={option.name} className="row">
              <div className="col-9">

                <li key={option.id} > {option.name}</li> 
              </div>
              <div className="col-3">
              <button  onClick={(e)=>removeGroup(e, option.name)} className="btn btn-outline-danger ms-2 mb-3"> - </button>
              </div>
              </div>
            ))}
        </ul>
        

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

export default KindergartenInputForm;

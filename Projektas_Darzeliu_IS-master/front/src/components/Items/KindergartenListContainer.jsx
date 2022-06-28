import React, { Component } from 'react';
import swal from 'sweetalert';

import http from '../10Services/httpService';
import apiEndpoint from '../10Services/endpoint';
import '../../App.css';

import KindergartenListTable from './KindergartenListTable';
import Pagination from '../08CommonComponents/Pagination';
import SearchBox from '../08CommonComponents/SeachBox';
export class KindergartenListContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {
            knygos: [],
            kategorijos: [],
            pageSize: 10,
            currentPage: 1,
            totalPages: 0,
            totalElements: 0,
            numberOfElements: 0,
            searchQuery: "",
            inEditMode: false,
            editRowId: "",
            editedKindergarten: null,
            errorMessages: {}
        }
    }
    componentDidMount() {
        this.getKindergartenInfo(this.state.currentPage, "");
        this.getkategorijos();
        document.addEventListener("keydown", this.handleEscape, false);
    }

    componentWillUnmount() {
        document.removeEventListener("keydown", this.handleEscape, false);
    }

    handleEscape = (e) => {
        if (e.key === 'Escape') {
            this.onCancel();
             
            setTimeout(function () {
                window.location.reload();
            }, 10);
        }
    }


    getKindergartenInfo(currentPage, name) {

        const { pageSize } = this.state;

        let page = currentPage - 1;

        if (page < 0 ) page = 0;

        var uri = `${apiEndpoint}/api/serviceProvider/manager/page?page=${page}&size=${pageSize}`;

        if (name !== "") {
            let encodedName = encodeURIComponent(name);
            uri = `${apiEndpoint}/api/serviceProvider/manager/page?page=${page}&size=${pageSize}&search=${encodedName}`;

        }

        http
            .get(uri)
            .then((response) => {

                this.setState({
                    knygos: response.data.content,
                    totalPages: response.data.totalPages,
                    totalElements: response.data.totalElements,
                    numberOfElements: response.data.numberOfElements,
                    currentPage: response.data.number + 1
                });

            }).catch(() => {});
    }

    getkategorijos() {
        http
            .get(`${apiEndpoint}/api/ServiceGroup/manager/groups`)
            .then((response) => {
                console.log(response)
                this.setState({ kategorijos: response.data });
                console.log(this.state.kategorijos)
            })
            .catch(() => {});
    }

    handleSearch = (e) => {

        const name = e.currentTarget.value;
        this.setState({ searchQuery: name });
        this.getKindergartenInfo(1, name);
    }

    handleDelete = (item) => {

        swal({
            text: "Ar tikrai norite ištrinti darželį?",
            buttons: ["Ne", "Taip"],
            dangerMode: true,
        }).then((actionConfirmed) => {
            if (actionConfirmed) {
                const id = item.code;
                const { currentPage, numberOfElements } = this.state;
                const page = numberOfElements === 1 ? (currentPage - 1) : currentPage;
                //console.log("Trinti darzeli", id);

                http
                    .delete(`${apiEndpoint}/api/serviceProvider/manager/delete/${id}`)
                    .then((response) => {
                        swal({
                            text: response.data,
                            button: "Gerai"
                        });
                        this.setState({searchQuery: ""});
                        this.getKindergartenInfo(page, "");

                    }).catch(() => {});
            }
        });
    }

    handleEditKindergarten = (item) => {

        this.setState({
            inEditMode: true,
            editRowId: item.code,
            editedKindergarten: item
        });
    }

    onCancel = () => {
        this.setState(
            {
                inEditMode: false,
                editRowId: "",
                editedKindergarten: null
            }
        )
    }

    handleChange = ({ target: input }) => {

        const errorMessages = this.state.errorMessages;

        if (input.validity.valueMissing || input.validity.patternMismatch || input.validity.rangeUnderflow || input.validity.rangeOverflow || input.validity.tooLong || input.validity.tooShort) {
            errorMessages[input.name] = `*${input.title}`;
        } else {
            delete errorMessages[input.name];
        }
        const kindergarten = this.state.editedKindergarten;
        kindergarten[input.name] = input.value;
        this.setState({
            editedKindergarten: kindergarten,
            errorMessages: errorMessages
        });
    }

    handleSaveEdited = () => {
        const { editedKindergarten, editRowId, errorMessages } = this.state;
     
        if (Object.keys(errorMessages).length === 0) {
            http.put(`${apiEndpoint}/api/serviceProvider/manager/update/${editRowId}`, editedKindergarten)
                .then(() => {
                    this.onCancel();
                    this.getKindergartenInfo(this.state.currentPage, this.state.searchQuery);
                }).catch(error => {
                    if (error && error.response.status === 409) {
                        swal({
                            text: error.response.data,
                            button: "Gerai"
                        });
                    }
                    
                })
        }
    }


    handlePageChange = (page) => {
        this.setState({ currentPage: page });
        this.getKindergartenInfo(page, this.state.searchQuery);
    };



    render() {

        const placeholder = "Ieškoti pagal pavadinimą...";

        const { knygos, kategorijos, totalElements, pageSize, searchQuery, inEditMode, editRowId, errorMessages } = this.state;

        const hasErrors = Object.keys(errorMessages).length === 0 ? false : true;

        return (
            <React.Fragment>

                <SearchBox
                    value={searchQuery}
                    onSearch={this.handleSearch}
                    placeholder={placeholder}
                />

                <KindergartenListTable
                    knygos={knygos}
                    kategorijos={kategorijos}
                    inEditMode={inEditMode}
                    editRowId={editRowId}
                    errorMessages={errorMessages}
                    hasErrors={hasErrors}
                    onDelete={this.handleDelete}
                    onEditData={this.handleEditKindergarten}
                    onEscape={this.handleEscape}
                    onChange={this.handleChange}
                    onSave={this.handleSaveEdited}
                    search={searchQuery}
                />

                <Pagination
                    itemsCount={totalElements}
                    pageSize={pageSize}
                    onPageChange={this.handlePageChange}
                    currentPage={this.state.currentPage}
                />


            </React.Fragment>
        )
    }
}

export default KindergartenListContainer;

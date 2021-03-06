import React, { Component } from 'react';

import '../../App.css';

import GroupsInputForm from './GroupsInputForm';
import GroupListContainer from './GroupListContainer';

export class GroupsContainer extends Component {

    render() {
        return (
            <div>
                <div className="container pt-4">

                    <div className="row ">
                        <div className="bg-light pb-3 col-12 col-sm-12 col-md-12 col-lg-3 pt-1">
                            <GroupsInputForm />
                        </div>

                        <div className="col-12 col-sm-12 col-md-12 col-lg-9 pt-1">
                            <h6 className="py-3"><b>Kategorijų sąrašas</b></h6>
                            <GroupListContainer />
                        </div>

                    </div>
                </div>
            </div>
        )
    }
}

export default GroupsContainer;

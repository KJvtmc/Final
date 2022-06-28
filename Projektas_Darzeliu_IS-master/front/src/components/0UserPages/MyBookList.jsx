import React, { Component } from 'react';

import '../../App.css';

import { MyBookListContainer } from './MyBookListContainer';

export class MyBookList extends Component {

    render() {
        return (
            <div>
                <div className="container pt-4">

                    <div className="row ">

                        <div className="col-12 col-sm-12 col-md-12 col-lg-12 pt-1">
                            <h6 className="py-3"><b>Laisvos knygos</b></h6>
                            <MyBookListContainer />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default MyBookList;
import React, { Component } from 'react';

import '../../App.css';

import { BookListContainer } from './BookListContainer';

export class BookList extends Component {

    render() {
        return (
            <div>
                <div className="container pt-4">

                    <div className="row ">

                        <div className="col-12 col-sm-12 col-md-12 col-lg-12 pt-1">
                            <h6 className="py-3"><b>Laisvos knygos</b></h6>
                            <BookListContainer />
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default BookList;
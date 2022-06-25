import React, { Component } from "react";
import ProductListComponent from "./ProductListComponent";
import axios from 'axios';

// axios.get('https://itpro2017.herokuapp.com/api/products')
// .then( (response) => {
// console.log(response);
// })
// .catch( (error) => {
// console.log(error);
// });


class ProductListContainer extends Component {

    constructor(props) {
        super(props);
        this.state = {
            products: props.products,
        };
        // this.api = axios.create({
        //     baseURL: "http://localhost:8081/korys-war/vakcinacija/centrai/"
        // });

    }

    // componentDidMount() {
    //     const loadProducts = async () => {
    //         const response = await this.api.get("/");
    //         // console.log("state ");
    //         // console.log(response);
    //         const productsFromServer = response.data;
    //         // console.log("from service ");
    //         // console.log(productsFromServer);
            
    //         this.setState({ products: productsFromServer });
    //         // console.log("state ");
    //         // console.log(this.state.products);
            
    //     }
    //     setTimeout(loadProducts, 1000);
    //     console.log(this.state.products);
    //     if(this.state.products.length===0){
    //         const context=this.context;
    //         this.setState({products: [context.productdetails]})
    //     }
    // }


    render() {
        return (
            <div className="container">
                <div className="row">
                    <ProductListComponent products={this.state.products} />
                </div>
            </div>
        );
    }
}
export default ProductListContainer
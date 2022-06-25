import React from "react";
import ProductDetailsContainer from "./ProductDetailsContainer"


const ProductListComponent = props => {
    let products = props.products;
    return (
        <div className="container ">
        <div className="row">
                {props.products.map(product => (
                    <div key={(props.products.indexOf(product))}  className="col-4">
                        <ProductDetailsContainer product={product} id={(products.indexOf(product))}/>
                    </div>
                ))}
            </div>
            </div>
        )
}

export default ProductListComponent
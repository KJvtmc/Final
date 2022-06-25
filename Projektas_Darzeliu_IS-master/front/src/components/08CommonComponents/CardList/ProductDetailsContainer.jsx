import ProductDetailsComponent from "./ProductDetailsComponent";
import React from "react";

const ProductDetailsContainer = props => {
    
        return (
            
                    <ProductDetailsComponent product= {props.product} id={(props.id)}/>
              
        );
}
export default ProductDetailsContainer
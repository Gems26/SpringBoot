import axios from "axios";

const url="http://localhost:8080/api/products";
const addCartUrl="http://localhost:8080/api/cart/add";
const carturl="http://localhost:8080/api/cart";
const removeurl ="http://localhost:8080/api/cart/product";
export const getproducts = ()=> axios.get(url)
export const cartProducts = () => axios.get(carturl)



export const sendToCart = (cartItems)=>{
   return  axios.post(addCartUrl,cartItems,{headers:{
        "Content-Type":"application/json"
    }})
}

export const deleteFromCart = (productid) =>{
    return axios.delete(removeurl+`/${productid}`);
}
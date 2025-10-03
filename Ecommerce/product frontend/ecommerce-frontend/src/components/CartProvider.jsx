import React, { createContext, useContext } from 'react'
import { useEffect, useState } from "react";
import { cartProducts, deleteFromCart, sendToCart } from "../api";

const cartContext = createContext();

export default function CartProvider({ children }) {
    const [cart, setCart] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const addToCart = (product) => {
        // setCart((preCart)=>[...preCart,product]);
        const cartItems = {
            productid: product.id, quantity: 1
        }
        sendToCart(cartItems).then(() => {
            cartProducts().then((response) => {
                setCart(mergeProducts(response.data));
                console.log("got data", response.data);
                setLoading(false)
            }).catch((err) => {
                console.error(err);
                setError("Error fetching cart data");
                setLoading(false);
            });

        }).catch((err) => {
            console.error(err);
            setError("Error fetching cart data");
            setLoading(false);
        });
    };

    useEffect(() => {
        cartProducts()
            .then((response) => {
                setCart(mergeProducts(response.data));
                console.log("got data", response.data);
                setLoading(false);
            })
            .catch((err) => {
                console.error(err);
                setError("Error fetching cart data");
                setLoading(false);
            });
    }, []);

    const deleteCartItems = (productid) => {
        deleteFromCart(productid).then(() => {
            cartProducts()
                .then((response) => {
                    setCart(mergeProducts(response.data));
                    console.log("got data", response.data);
                    setLoading(false);
                })
                .catch((err) => {
                    console.error(err);
                    setError("Error fetching cart data");
                    setLoading(false);
                });
        })
    }

    const mergeProducts = (data) => {
        const merged = {};
        data.forEach((item) => {
            const productid = item.product.id;
            if (merged[productid]) {
                merged[productid].quantity += item.quantity;
            }
            else {
                merged[productid] = { ...item }
            }
        });
        return Object.values(merged)
    }

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <cartContext.Provider value={{ cart, addToCart, deleteCartItems }}>
                {children}
            </cartContext.Provider>
        </div>
    )
}

export const useCart = () => useContext(cartContext);
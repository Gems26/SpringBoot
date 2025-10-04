import React, { useEffect, useState } from "react";
import { useCart } from "./CartProvider";

export default function CartPage() {
  const { cart, deleteCartItems } = useCart();
  const [total, SetTotal] = useState(0);

  useEffect(() => {
    const totalPrice = cart.reduce(
      (sum, item) => sum + item.product.price * item.quantity,
      0
    );
    SetTotal(totalPrice);
  }, [cart]);

  return (
    <div>
      <header className="header">
        <h1>Sky Mart</h1>
        <p className="tagline">Groceries, Snacks, Essentials – All Under One Sky</p>
      </header>

      <div className="cart-page">
        <h1>Cart</h1>
        <ul className="cart-list">
          {cart.map((item) => (
            <li key={item.product.id} className="cart-item">
              <img src={item.product.img} alt={item.product.name} />
              <div className="cart-item-details">
                <h3>{item.product.name} x {item.quantity}</h3>
                <p>{item.product.description}</p>
                <button className="remove-btn" onClick={() => deleteCartItems(item.product.id)}>Remove</button>
              </div>
              <div className="cart-item-price">
                ₹{item.product.price * item.quantity}
              </div>
            </li>
          ))}
        </ul>
        <div className="cart-total">Total Amount: ₹{total}</div>
      </div>
    </div>
  );
}

import { useEffect, useState } from "react";
import ProductCard from "./components/ProductCard";
import "./App.css";
import { getproducts } from "./api";
import { useCart } from "./components/CartProvider";
import { useNavigate } from "react-router-dom";

function App() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const { cart, addToCart, deleteCartItems } = useCart();
  const navigate = useNavigate();

  useEffect(() => {
    getproducts()
      .then((response) => {
        setProducts(response.data);
        console.log("got data", response.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setError("Error fetching product data");
        setLoading(false);
      });
  }, []);




  if (loading) return <p>Loading...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <header className="header">
        <h1>Sky Mart</h1>
        <p className="tagline">Groceries, Snacks, Essentials – All Under One Sky</p>
      </header>

      <div className="content">

        <div className="container">
          {products.map((product) => (
            <ProductCard key={product.id} product={product} addToCart={addToCart} />
          ))}
        </div>

        {
          cart.length > 0 ? (
            <div className="right-cart">
              <h1>Cart</h1>
              <button className="proceed-btn" onClick={()=>navigate("/cart")}>Proceed To CheckOut</button>
              <ul>
                {
                  cart.map((item) => (
                    <li>
                      <img src={item.product.img} alt='' className='prodimg' />
                      {item.product.name} - {item.quantity}
                      <button onClick={() => deleteCartItems(item.product.id)}>Remove</button>
                    </li>
                  ))
                }

              </ul>
            </div>) : null
        }

      </div>

    </div>
  );
}

export default App;

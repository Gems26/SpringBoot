import { useEffect, useState } from "react";
import ProductCard from "./components/ProductCard";
import "./App.css";
import {cartProducts, getproducts, sendToCart} from "./api";

function App() {
  const [products, setProducts] = useState([]);
  const [cart,setCart]=useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const addToCart=(product)=>{
   // setCart((preCart)=>[...preCart,product]);
    const cartItems={
      productid:product.id,quantity:1
    }
    sendToCart(cartItems).then(()=>{
      cartProducts().then((response)=>{setCart(mergeProducts(response.data));
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

  const mergeProducts = (data)=>{
      const merged = {};
      data.forEach((item) => {
        const productid = item.product.id;
        if(merged[productid]){
          merged[productid].quantity+=item.quantity;
        }
        else{
          merged[productid] = {...item}
        }
      });
      return Object.values(merged)
  }

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
          <ProductCard key={product.id} product={product} addToCart={addToCart}/>
        ))}
      </div>

      {
        cart.length>0 ?(
      <div className="right-cart">
          <h1>Cart</h1>
          <ul>
           {
            cart.map((item)=>(
              <li> 
              <img src={item.product.img} alt='' className='prodimg' />
              {item.product.name} - {item.quantity}
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

import { useEffect, useState } from "react";
import axios from "axios";
import ProductCard from "./components/ProductCard";
import "./App.css";

function App() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/products")
      .then((response) => {
        setProducts(response.data);
        console.log("got data", response.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setError("Error fetching data");
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
    <div className="container">
      {products.map((product) => (
        <ProductCard key={product.id} product={product} />
      ))}
    </div>
    </div> 
  );
}

export default App;

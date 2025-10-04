import React from 'react'

function ProductCard({ product ,addToCart }) {
    return (
        
        <div>
                
                <div className='card' >

                    <img src={product.img} alt='' className='prodimg' />

                    <h3 className='prodname'>{product.name}</h3>
                    <p className='desc'>{product.description}</p>
                    <div className='rating'>
                        {
                            [1, 2, 3, 4, 5].map((count) => (
                                <span style={{ color: count <= product.rating ? "#f39c12" : "#ccc" }}>&#9733;</span>
                            ))
                        }

                    </div>
                    <div className='price'>
                        <p>Rs.{product.price}</p>
                    </div>
                    <div className='stock'>
                        <p>Available stock:{product.stock}</p>
                    </div>
                    <button className='addcart' onClick={()=>addToCart(product)}>Add to Cart</button>
                </div>


        </div>
    );
}



export default ProductCard

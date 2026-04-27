package isi.shoppingCart.usecases.services;


import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.CartItem;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.CustomerRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;
import isi.shoppingCart.usecases.ports.PurchaseRepository;
import isi.shoppingCart.entities.CartItem;

import java.util.List;

public class DeleteProductOfCart {
    private CartRepository cartRepository;

    public DeleteProductOfCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
    public OperationResult execute(int productId){
        Cart cart=cartRepository.getCart();
        List<CartItem> items = cart.getItems();

        if (cart == null || cart.getItems().isEmpty()) {
            return OperationResult.fail("El carrito está vacío.");
        }

       /*CartItem cartItem = items.get(productId-1);
       Product product=cartItem.getProduct();
       if(cartItem.getProduct().getId()==product.getId()){

       }*/
        cart.removeItemCart(productId);
        cartRepository.save(cart);

        return OperationResult.ok("Producto eliminado de carrito");

    }

}

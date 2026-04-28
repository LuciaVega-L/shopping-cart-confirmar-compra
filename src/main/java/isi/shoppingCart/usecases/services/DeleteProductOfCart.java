package isi.shoppingCart.usecases.services;


import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.CartItem;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;

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
        cart.removeItemCart(productId);
        cartRepository.save(cart);

        return OperationResult.ok("Producto eliminado de carrito");

    }

}

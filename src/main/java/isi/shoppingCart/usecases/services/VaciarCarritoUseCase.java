package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.CartItem;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;

import java.util.List;

public class VaciarCarritoUseCase {
    CartRepository cartRepository;

    public VaciarCarritoUseCase(CartRepository cartRepository){
        this.cartRepository=cartRepository;
    }

    public OperationResult execute(){
        Cart cart= cartRepository.getCart();
        if(cart==null){
            OperationResult.fail("Carrito no existe");
        }
        //List<CartItem> cartItems=cart.getItems();
        while (!cart.getItems().isEmpty()) {
            cart.removeItemCart(cart.getItems().get(0).getProduct().getId());
        }

        cartRepository.save(cart);
        return OperationResult.ok("Carrito Vaciado");
    }
}

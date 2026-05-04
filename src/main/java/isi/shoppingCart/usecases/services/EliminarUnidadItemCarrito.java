package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.CartItem;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.services.DeleteProductOfCart;

import java.util.List;

public class EliminarUnidadItemCarrito {
    CartRepository cartRepository;
    DeleteProductOfCart deleteProductOfCart;

    public EliminarUnidadItemCarrito(CartRepository cartRepository, DeleteProductOfCart deleteProductOfCart){
        this.cartRepository=cartRepository;
        this.deleteProductOfCart=deleteProductOfCart;
    }

    public OperationResult execute(int idItem){
        Cart cart=cartRepository.getCart();

        if (cart == null || cart.getItems().isEmpty()) {
            return OperationResult.fail("El carrito está vacío.");
        }

        List<CartItem> cartItems=cart.getItems();
        for(int i=0;i<cartItems.size();i++){
            if(cartItems.get(i).getProduct().getId()==idItem){
                cartItems.get(i).decreaseAvailableQuantity();
                if(cartItems.get(i).getQuantity()==0){
                    deleteProductOfCart.execute(cartItems.get(i).getProduct().getId());
                }

            }
        }

        cartRepository.save(cart);
        return OperationResult.ok("Unidad restada");

    }
}

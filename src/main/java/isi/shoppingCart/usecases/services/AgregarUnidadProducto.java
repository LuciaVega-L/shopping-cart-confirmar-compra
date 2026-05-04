package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.ProductRepository;

public class AgregarUnidadProducto {
    ProductRepository productRepository;

    public AgregarUnidadProducto(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    public OperationResult execute(int idProduct){
        Product product=productRepository.findById(idProduct);
        if(product==null){
            OperationResult.fail("El producto no existe.");
        }
        product.increaseAvailableQuantity(1);
        return OperationResult.ok("Unidad agregada.");
    }
}

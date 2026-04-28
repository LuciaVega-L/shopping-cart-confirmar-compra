package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.entities.Purchase;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.ProductRepository;
import isi.shoppingCart.usecases.ports.PurchaseRepository;

import java.util.List;

public class DevolverCompraUseCase {
    private PurchaseRepository purchaseRepository;
    private ProductRepository productRepository;

    public DevolverCompraUseCase(PurchaseRepository purchaseRepository, ProductRepository productRepository){
        this.purchaseRepository=purchaseRepository;
        this.productRepository=productRepository;
    }

    public OperationResult execute(int idPurchase){
        Purchase purchase=purchaseRepository.findById(idPurchase);
        List<Product>listProducts=productRepository.findAll();

        if(purchase==null){
            return OperationResult.fail("No existe la compra");
        }
        else{
            int i;int z;

            for(i=0;i<purchase.getItems().size();i++) {
                for(z=0;z<listProducts.size();z++){
                    if(purchase.getItems().get(i).getProduct().getId()==listProducts.get(z).getId()){
                        listProducts.get(z).increaseAvailableQuantity(purchase.getItems().get(i).getQuantity());
                    }
                }
            }

            purchaseRepository.eliminarCompra(idPurchase);

            return OperationResult.ok("Compra eliminada");
        }

    }

}

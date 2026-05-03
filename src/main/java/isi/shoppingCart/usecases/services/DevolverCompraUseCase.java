package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.entities.Purchase;
import isi.shoppingCart.entities.PurchaseItem;
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

        if(purchase==null){
            return OperationResult.fail("No existe la compra");
        }
        else{
            int i;

            List<PurchaseItem> items = purchase.getItems();
            for( i=0;i<purchase.getItems().size();i++){
                Product product=productRepository.findById(items.get(i).getProduct().getId());
                product.increaseAvailableQuantity(items.get(i).getQuantity());
            }

            purchaseRepository.eliminarCompra(idPurchase);

            return OperationResult.ok("Compra eliminada");
        }

    }

}

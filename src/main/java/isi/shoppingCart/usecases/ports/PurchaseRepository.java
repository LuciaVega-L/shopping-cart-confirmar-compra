package isi.shoppingCart.usecases.ports;

import isi.shoppingCart.entities.Purchase;
import java.util.List;

public interface PurchaseRepository {
    int getNextId();
    List<Purchase> findAll();
    void save(Purchase purchase);
    Purchase findById(int id);
    void eliminarCompra(int idPurchase);
}

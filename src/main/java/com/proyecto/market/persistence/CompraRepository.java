package com.proyecto.market.persistence;

import com.proyecto.market.domain.Purchase;
import com.proyecto.market.domain.repository.PurchaseRepository;
import com.proyecto.market.persistence.crud.CompraCrudRepository;
import com.proyecto.market.persistence.entity.Compra;
import com.proyecto.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clienteId) {
        return compraCrudRepository.findByIdCliente(clienteId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(comprasProducto -> comprasProducto.setCompra(compra));

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}

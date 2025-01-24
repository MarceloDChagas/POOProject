package domain.repository;

import domain.entity.Sale;

import java.util.List;

public interface ISaleRepository {
    void save(Sale sale);                       // Salva uma nova venda
    Sale findById(String id);                   // Encontra uma venda pelo ID
    List<Sale> findAll();                       // Retorna todas as vendas
    void delete(String id);                     // Remove uma venda pelo ID
    void saveAll(List<Sale> sales);             // Salva todas as vendas
    double getTotalSalesAmount(int month, int year);
    int getTotalSalesCount(int month, int year);
    Sale getDayWithMostSales(int month, int year);
}

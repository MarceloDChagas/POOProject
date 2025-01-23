package domain.repository;
import domain.entity.Sale;
import java.util.List;

public interface ISaleRepository {
    void save(Sale sale);
    List<Sale> findAll();
    List<Sale> findSalesByMonth(int month, int year);
    double getTotalSalesAmount(int month, int year); // Total de vendas do mês
    int getTotalSalesCount(int month, int year);     // Quantidade total de vendas do mês
    Sale getDayWithMostSales(int month, int year);   // Dia com mais vendas

}

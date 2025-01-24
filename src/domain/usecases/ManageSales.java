package domain.usecases;

import domain.entity.Sale;
import domain.repository.ISaleRepository;

import java.util.List;

public class ManageSales {
    private final ISaleRepository saleRepository;

    public ManageSales(ISaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void registerSale(Sale sale) {
        saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public double getTotalSalesAmount(int month, int year) {
        return saleRepository.getTotalSalesAmount(month, year);
    }

    public int getTotalSalesCount(int month, int year) {
        return saleRepository.getTotalSalesCount(month, year);
    }

    public Sale getDayWithMostSales(int month, int year) {
        return saleRepository.getDayWithMostSales(month, year);
    }
}

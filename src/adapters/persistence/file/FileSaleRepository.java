package adapters.persistence.file;

import domain.entity.Sale;
import domain.repository.ISaleRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FileSaleRepository extends FileRepository implements ISaleRepository {
    private final String filePath = System.getProperty("user.dir") + "/src/adapters/persistence/file/sale.txt";

    @Override
    public void save(Sale sale) {
        List<Sale> sales = findAll();
        sales.add(sale);
        saveAll(sales);
    }

    @Override
    public Sale findById(String id) {
        return findAll().stream()
                .filter(sale -> sale.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Sale> findAll() {
        List<Sale> sales = new ArrayList<>();
        List<String> lines = readFile(filePath);
        for (String line : lines) {
            sales.add(Sale.fromString(line)); // O método `fromString` deve ser implementado na classe `Sale`
        }
        return sales;
    }

    @Override
    public void delete(String id) {
        List<Sale> sales = findAll();
        sales.removeIf(sale -> sale.getId().equals(id));
        saveAll(sales);
    }

    @Override
    public void saveAll(List<Sale> sales) {
        List<String> lines = new ArrayList<>();
        for (Sale sale : sales) {
            lines.add(sale.toString()); // O método `toString` deve estar implementado na classe `Sale`
        }
        writeFile(filePath, lines);
    }

    @Override
    public double getTotalSalesAmount(int month, int year) {
        return findAll().stream()
                .filter(sale -> sale.getDate().getMonthValue() == month && sale.getDate().getYear() == year)
                .mapToDouble(Sale::getTotalAmount)
                .sum();
    }

    @Override
    public int getTotalSalesCount(int month, int year) {
        return (int) findAll().stream()
                .filter(sale -> sale.getDate().getMonthValue() == month && sale.getDate().getYear() == year)
                .count();
    }

    @Override
    public Sale getDayWithMostSales(int month, int year) {
        Map<LocalDate, Long> salesByDay = findAll().stream()
                .filter(sale -> sale.getDate().getMonthValue() == month && sale.getDate().getYear() == year)
                .collect(Collectors.groupingBy(Sale::getDate, Collectors.counting()));

        return salesByDay.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    return new Sale(date, entry.getValue().intValue()); // Criar uma venda representativa para o dia com mais vendas
                })
                .orElse(null);
    }
}

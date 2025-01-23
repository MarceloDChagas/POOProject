package adapters.persistence.file;

import domain.entity.Sale;

import java.util.ArrayList;
import java.util.List;

public class FileSaleRepository extends FileRepository {
    private final String filePath;

    public FileSaleRepository(String filePath) {
        this.filePath = filePath;
    }

    public void saveSales(List<Sale> sales) {
        List<String> lines = new ArrayList<>();
        for (Sale sale : sales) {
            lines.add(sale.toString());
        }
        writeFile(filePath, lines);
    }

    public List<Sale> loadSales() {
        List<Sale> sales = new ArrayList<>();
        List<String> lines = readFile(filePath);
        for (String line : lines) {
            sales.add(Sale.fromString(line));
        }
        return sales;
    }
}

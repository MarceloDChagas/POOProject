package domain.usecases.registerSaleUseCases;

import adapters.persistence.file.FileSaleRepository;
import domain.entity.Sale;

import java.util.List;

public class RegisterSaleUseCase {
    private final FileSaleRepository saleRepository;

    public RegisterSaleUseCase(FileSaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void execute(Sale newSale) {
        // Carrega as vendas existentes
        List<Sale> sales = saleRepository.findAll();

        // Adiciona a nova venda
        sales.add(newSale);

        // Salva as vendas atualizadas
        saleRepository.saveAll(sales);

        System.out.println("Venda registrada com sucesso: " + newSale);
    }
}

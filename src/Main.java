import adapters.persistence.file.FileCustomerRepository;
import adapters.persistence.file.FileProductRepository;
import adapters.persistence.file.FileSaleRepository;
import domain.entity.Customer;
import domain.entity.Product;
import domain.entity.Sale;
import domain.usecases.createProduct.CreateProductUseCase;
import domain.usecases.registerSaleUseCases.RegisterSaleUseCase;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicializar os repositórios
        FileCustomerRepository customerRepository = new FileCustomerRepository();
        FileProductRepository productRepository = new FileProductRepository();
        FileSaleRepository saleRepository = new FileSaleRepository();

        // Teste de criação de cliente
        System.out.println("===== Testando Criação de Cliente =====");
        Customer newCustomer = new Customer("1", "John Doe", "john.doe@example.com", 0.0, 0 );
        customerRepository.save(newCustomer);
        System.out.println("Cliente criado e salvo no arquivo: " + newCustomer);

        // Carregar e listar todos os clientes
        System.out.println("\n===== Listando Todos os Clientes =====");
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            System.out.println(customer);
        }

        // Teste de criação de produto
        System.out.println("\n===== Testando Criação de Produto =====");
        CreateProductUseCase createProductUseCase = new CreateProductUseCase(productRepository);
        createProductUseCase.execute("Notebook", 2500.00, 10, "Um notebook para estudo e trabalho");

        // Carregar e listar todos os produtos
        System.out.println("\n===== Listando Todos os Produtos =====");
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            System.out.println(product);
        }

        // Teste de registro de venda
        System.out.println("\n===== Testando Registro de Venda =====");
        Product soldProduct = products.get(0); // Assume que o primeiro produto será vendido
        Sale newSale = new Sale(LocalDate.now(), soldProduct, newCustomer, soldProduct.getPrice());
        RegisterSaleUseCase registerSaleUseCase = new RegisterSaleUseCase(saleRepository);
        System.out.println("dnmaosjd");
        registerSaleUseCase.execute(newSale);

        // Carregar e listar todas as vendas
        System.out.println("\n===== Listando Todas as Vendas =====");
        List<Sale> sales = saleRepository.findAll();
        for (Sale sale : sales) {
            System.out.println(sale);
        }

        // Teste de métricas (exemplo: total de vendas em um mês/ano)
        System.out.println("\n===== Total de Vendas no Mês Atual =====");
        double totalSalesAmount = saleRepository.getTotalSalesAmount(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        System.out.println("Total de vendas: R$ " + totalSalesAmount);
    }
}

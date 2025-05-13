import Modelo.Product;
import UseCase.ProductServiceImpl;
import UseCase.ProductService;
import UseCase.InMemoryProductRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static ProductService productService;

    public static void main(String[] args) {
        // Inicializa o serviço com o repositório
        productService = new ProductServiceImpl(new InMemoryProductRepository());

        // Cria produtos de exemplo
        try {
            initSampleProducts();
        } catch (Exception e) {
            System.out.println("Erro ao inicializar produtos de exemplo: " + e.getMessage());
        }

        // Executa a interface de linha de comando
        runCLI();
    }

    private static void initSampleProducts() {
        productService.registerProduct("P001", "Notebook", 3499.99);
        productService.registerProduct("P002", "Smartphone", 1899.99);
        productService.registerProduct("P003", "Fones de Ouvido", 249.99);
    }

    private static void runCLI() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== Sistema de Gerenciamento de Produtos =====");
            System.out.println("1. Listar todos os produtos");
            System.out.println("2. Buscar produto por código");
            System.out.println("3. Cadastrar novo produto");
            System.out.println("4. Atualizar preço do produto");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int option = 0;
            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Consome a quebra de linha
            } catch (InputMismatchException e) {
                // Limpa o buffer do scanner se o usuário digitar algo inválido
                scanner.nextLine();
                System.out.println("Opção inválida. Digite um número.");
                continue;
            }

            switch (option) {
                case 1:
                    listAllProducts();
                    break;
                case 2:
                    findProductByCode(scanner);
                    break;
                case 3:
                    registerNewProduct(scanner);
                    break;
                case 4:
                    updateProductPrice(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Saindo da aplicação. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void listAllProducts() {
        List<Product> products = productService.getAllProducts();
        System.out.println("\n----- Todos os Produtos -----");

        if (products.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return;
        }

        for (Product product : products) {
            displayProduct(product);
        }
    }

    private static void findProductByCode(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        String code = scanner.nextLine();

        Product product = productService.getProduct(code);
        if (product != null) {
            System.out.println("\n----- Produto Encontrado -----");
            displayProduct(product);
        } else {
            System.out.println("Produto com código " + code + " não encontrado.");
        }
    }

    private static void registerNewProduct(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        String code = scanner.nextLine();

        System.out.print("Digite o nome do produto: ");
        String name = scanner.nextLine();

        double price = 0;
        boolean validPrice = false;

        while (!validPrice) {
            try {
                System.out.print("Digite o preço do produto: ");
                price = scanner.nextDouble();
                validPrice = true;
            } catch (InputMismatchException e) {
                System.out.println("Preço inválido. Use um valor numérico (ex: 99.99)");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        }

        scanner.nextLine(); // Consome a quebra de linha

        try {
            productService.registerProduct(code, name, price);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    private static void updateProductPrice(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        String code = scanner.nextLine();

        // Verifica se o produto existe
        Product product = productService.getProduct(code);
        if (product == null) {
            System.out.println("Produto com código " + code + " não encontrado.");
            return;
        }

        double newPrice = 0;
        boolean validPrice = false;

        while (!validPrice) {
            try {
                System.out.print("Digite o novo preço: ");
                newPrice = scanner.nextDouble();
                validPrice = true;
            } catch (InputMismatchException e) {
                System.out.println("Preço inválido. Use um valor numérico (ex: 99.99)");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        }

        scanner.nextLine(); // Consome a quebra de linha

        try {
            productService.updateProductPrice(code, newPrice);
            System.out.println("Preço atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar preço: " + e.getMessage());
        }
    }

    private static void displayProduct(Product product) {
        System.out.println("Código: " + product.getCode() +
                " | Nome: " + product.getName() +
                " | Preço: R$ " + String.format("%.2f", product.getPrice()));
    }
}

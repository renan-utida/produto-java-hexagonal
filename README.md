# Sistema de Gerenciamento de Produtos

Um sistema simples de gerenciamento de produtos implementado com Arquitetura Hexagonal em Java.

## Estrutura do Projeto

O projeto segue a arquitetura hexagonal (portas e adaptadores) com a seguinte estrutura:

```
src/
├── Modelo/
│    └── Product.java
├── usecase/
│    ├── ProductService.java
│    ├── ProductRepository.java
│    ├── ProductServiceImpl.java
│    └── InMemoryProductRepository.java
└── ConsoleApp.java
test/
├── ProductServiceImplTest.java
└── InMemoryProductRepositoryTest.java
```

## Requisitos

- Java 8 ou superior
- JUnit 4 para testes

## Compilando e Executando

1. Abra o projeto no IntelliJ IDEA
2. Compile o projeto: `Build > Build Project`
3. Execute a classe principal: `ConsoleApp.java`

## Executando Testes

Execute os testes JUnit através do IntelliJ IDEA:
- Clique com o botão direito no diretório `test`
- Selecione "Run All Tests"

## Funcionalidades

A aplicação fornece uma interface de linha de comando simples para gerenciar produtos:

1. Listar todos os produtos
2. Buscar produto por código
3. Cadastrar novo produto
4. Atualizar preço do produto
5. Sair

### Tratamento de Erros

O sistema inclui tratamento de erros para:
- Códigos de produto duplicados
- Entradas inválidas (como letras em campos numéricos)
- Produtos não encontrados
- Erros de formato de números

Todos os erros são tratados de forma que o programa continue funcionando, exibindo mensagens claras ao usuário e solicitando novas entradas quando necessário.

## Arquitetura Hexagonal

O projeto segue o padrão de arquitetura hexagonal:

- **Modelo de domínio**: classe `Product` contendo código, nome e preço
- **Portas de entrada**: interface `ProductService` que define os casos de uso
- **Portas de saída**: interface `ProductRepository` para persistência de dados
- **Adaptadores**: implementação `InMemoryProductRepository` para armazenamento em memória
- **Console App**: adaptador de interface com o usuário

### Princípios aplicados

1. **Separação de responsabilidades**: A lógica de negócios é isolada da infraestrutura
2. **Inversão de dependência**: As classes de domínio não dependem de detalhes de implementação
3. **Testabilidade**: Os componentes são facilmente testáveis de forma isolada
4. **Flexibilidade**: É possível trocar componentes (ex: repositório) sem alterar a lógica de negócios

## Testes Unitários

O projeto inclui testes unitários para:

1. **ProductServiceImplTest**:
   - Cadastro de produtos
   - Atualização de preço
   - Validação de produtos inexistentes
   - Validação de códigos duplicados

2. **InMemoryProductRepositoryTest**:
   - Salvamento e busca de produtos
   - Listagem de todos os produtos

## Classes e Interfaces Principais

### Product.java
```java
public class Product {
    private final String code;
    private String name;
    private double price;
    
    // Getters e setters
}
```

### ProductService.java (Porta de Entrada)
```java
public interface ProductService {
    void registerProduct(String code, String name, double price);
    void updateProductPrice(String code, double newPrice);
    Product getProduct(String code);
    List<Product> getAllProducts();
}
```

### ProductRepository.java (Porta de Saída)
```java
public interface ProductRepository {
    void save(Product product);
    Product findByCode(String code);
    List<Product> findAll();
}
```

### ProductServiceImpl.java (Implementação do Caso de Uso)
```java
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    
    // Implementação dos métodos e regras de negócio
    // Inclui validação de código duplicado
}
```

### InMemoryProductRepository.java (Adaptador)
```java
public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> storage = new HashMap<>();
    
    // Implementação dos métodos para persistência em memória
}
```

## Extensões Possíveis
A arquitetura hexagonal facilita extensões como:

1. Implementar um repositório baseado em banco de dados
2. Adicionar uma interface de usuário gráfica ou web
3. Implementar novos casos de uso (remoção de produtos, categorização, etc.)
4. Adicionar validação mais complexa e regras de negócio

## Descrição do Desafio Proposto

### Desafio de Programação: Gerenciamento de Produtos

#### Objetivo: Criar um sistema simples de gerenciamento de produtoscom arquitetura hexagonal.

#### Requisitos:
• Produto com nome, preço e código;
• Porta de entrada (serviço) para cadastrar e alterar preço doproduto.
• Porta de saída para salvar/buscar produtos;
• Implementar o repositório em memória.
• Criar testes unitários.
• Código-fonte Java com aplicação de Arquitetura Hexagonal;
• Mínimo de 2 testes unitários;
• Criar um arquivo (pode ser ReadMe) com instruções de execução.

---

## Desenvolvedor

[<img loading="lazy" src="https://github.com/user-attachments/assets/b4f96f4b-542e-4988-9bc1-b1acf22a41a1" width=115><br><sub>Renan Dias Utida</sub>](https://github.com/renan-utida)

### Linkedin: https://www.linkedin.com/in/renan-dias-utida-1b1228225/

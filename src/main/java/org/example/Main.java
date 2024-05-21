package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductDAO productDAO = new ProductDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Pesquisar Produto");
            System.out.println("3. Alterar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("5. Listar Produtos");
            System.out.println("6. Sair");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consumir a nova linha

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    searchProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    listProducts();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void addProduct() {
        System.out.println("Tipo:");
        String type = scanner.nextLine();
        System.out.println("Descrição:");
        String description = scanner.nextLine();
        System.out.println("Peso:");
        double weight = scanner.nextDouble();
        System.out.println("Quantidade:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consumir a nova linha
        System.out.println("Unidade de Medida (metro, metro quadrado, litro, kg):");
        String unitOfMeasure = scanner.nextLine();

        Product product = new Product(type, description, weight, quantity, unitOfMeasure);
        productDAO.addProduct(product);
        System.out.println("Produto adicionado com sucesso!");
    }

    private static void searchProduct() {
        System.out.println("ID do Produto:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consumir a nova linha

        Product product = productDAO.getProduct(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private static void updateProduct() {
        System.out.println("ID do Produto:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consumir a nova linha

        Product product = productDAO.getProduct(id);
        if (product != null) {
            System.out.println("Novo Tipo:");
            String type = scanner.nextLine();
            System.out.println("Nova Descrição:");
            String description = scanner.nextLine();
            System.out.println("Novo Peso:");
            double weight = scanner.nextDouble();
            System.out.println("Nova Quantidade:");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consumir a nova linha
            System.out.println("Nova Unidade de Medida (metro, metro quadrado, litro, kg):");
            String unitOfMeasure = scanner.nextLine();

            Product updatedProduct = new Product(type, description, weight, quantity, unitOfMeasure);
            productDAO.updateProduct(id, updatedProduct);
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    private static void deleteProduct() {
        System.out.println("ID do Produto:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consumir a nova linha

        productDAO.deleteProduct(id);
        System.out.println("Produto excluído com sucesso!");
    }

    private static void listProducts() {
        List<Product> products = productDAO.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
package app;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0)
        );

        Stream<Product> productStream = products.stream();
        Map<String, List<Product>> mapCategory = productStream
                .collect(Collectors.groupingBy(Product::getCategory)
                );

        Map<String, Double> mapAvrPrice = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice))
                );

        Optional<Map.Entry<String, Double>> maxAvr = mapAvrPrice.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        String category = maxAvr.get().getKey();
        double maxAvrPrice = maxAvr.get().getValue();
        System.out.println("\nCategory - " + category + ", max average price " + maxAvrPrice);
    }
}
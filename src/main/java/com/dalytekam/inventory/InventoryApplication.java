package com.dalytekam.inventory;

import com.dalytekam.inventory.Dao.ProductRepository;
import com.dalytekam.inventory.entities.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class InventoryApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(InventoryApplication.class, args);

        ProductRepository productRepository =ctx.getBean(ProductRepository.class);

        productRepository.save(new Product("Tshirt",6.55,31));
        productRepository.save(new Product("bottle",4.02,12));
        productRepository.save(new Product("Shoes",66.55,18));
        productRepository.save(new Product("Tomatoes",25.05,39));
        productRepository.save(new Product("bottle",8.02,98));
        productRepository.save(new Product("Banana",66.55,3));
        productRepository.save(new Product("Tshirt",6.55,31));
        productRepository.save(new Product("bottle",4.02,12));
        productRepository.save(new Product("Shoes",66.55,18));
        productRepository.save(new Product("Tomatoes",25.05,39));
        productRepository.save(new Product("bottle",8.02,98));
        productRepository.save(new Product("Banana",66.55,3));
        productRepository.save(new Product("Tshirt",6.55,31));
        productRepository.save(new Product("bottle",4.02,12));
        productRepository.save(new Product("Shoes",66.55,18));
        productRepository.save(new Product("Tomatoes",25.05,39));
        productRepository.save(new Product("bottle",8.02,98));
        productRepository.save(new Product("Banana",66.55,3));
        productRepository.save(new Product("Tshirt",6.55,31));
        productRepository.save(new Product("bottle",4.02,12));
        productRepository.save(new Product("Shoes",66.55,18));
        productRepository.save(new Product("Tomatoes",25.05,39));
        productRepository.save(new Product("bottle",8.02,98));
        productRepository.save(new Product("Banana",66.55,3));
        productRepository.save(new Product("Tshirt",6.55,31));
        productRepository.save(new Product("bottle",4.02,12));
        productRepository.save(new Product("Shoes",66.55,18));
        productRepository.save(new Product("Tomatoes",25.05,39));
        productRepository.save(new Product("bottle",8.02,98));
        productRepository.save(new Product("Banana",66.55,3));




        productRepository.findAll().forEach(p ->
        {
            System.out.println(p.getName());
        });
    }

}

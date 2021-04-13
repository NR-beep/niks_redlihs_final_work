package finalwork.finalwork.service;

import finalwork.finalwork.model.Product;
import finalwork.finalwork.model.ProductCategory;
import finalwork.finalwork.model.ProductData;
import finalwork.finalwork.model.ProductInputData;
import finalwork.finalwork.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    public void save(ProductInputData productInputData) {
        Product product = convertFrom(productInputData);
        repository.save(product);

    }

    public List<ProductData> findAll() {
        List<ProductData> result = new ArrayList<>();
        for (Product product : repository.findAll()) {
            result.add(convertFrom(product));
        }
        return result;
    }

    private ProductData convertFrom(Product product) {
        return  new ProductData(
                 product.getId().toString(),
                 product.getName(),
                 product.getPrice().toPlainString());
    }

    private Product convertFrom(ProductInputData productInputData) {
        Product product = new Product();
        product.setName(productInputData.getName());
        product.setPrice(BigDecimal.valueOf(productInputData.getPrice()));
        product.setCategory(ProductCategory.valueOf(productInputData.getCategory()));
        if (productInputData.getDiscount() != null) {
            product.setDiscount(BigDecimal.valueOf(productInputData.getDiscount()));
        }

        if (productInputData.getDescription() != null) {
            product.setDescription(productInputData.getDescription());
        }

        return product;
    }
}

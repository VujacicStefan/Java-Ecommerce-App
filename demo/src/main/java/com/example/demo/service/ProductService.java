package com.example.demo.service;

import com.example.demo.Entity.Product;
import com.example.demo.Entity.Users;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.UsersDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ProductService {
    private ProductDao product_dao;
    private UsersDao users_dao;

    private Users users;

    private Product product;



    public ProductService(ProductDao product_dao, UsersDao users_dao) {
        this.product_dao = product_dao;
        this.users_dao = users_dao;
    }

    public Product addNewProduct(Product product) {
        return product_dao.save(product);

    }

    public List<Product> findAll() {
        return product_dao.findAll();
    }

    public void delete_product_by_id(long id) {
        if (users.getProducts().contains(product)) {
            product_dao.deleteById(id);
        }
    }

    public Product product_by_id(long product_id) {
        if (product_dao.findById(product_id).isPresent()) {
            return product_dao.findById(product_id).get();
        }
        return null;
    }

    public Product get_product_details(String productDescription) {
        if (product_dao.findByProductDescription(productDescription).isPresent()) {
            return product_dao.findByProductDescription(productDescription).get();
        }

        return null;
    }


    public Optional<Product> productName(String productName) {
        Optional<Product> product = product_dao.findByProductName(productName);
        return product;
    }

    public List<Product> getProductByPage(int pageNumber, String search_key) {
        PageRequest pageable = PageRequest.of(pageNumber, 14);
        Page<Product> page;
        if (search_key.equals("")) {
            page = product_dao.findAll(pageable);
        } else {
            page = product_dao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(search_key, search_key, pageable);
        }
        return page.getContent();
    }


    public List<Product> find_by_price_more_than(int pageNumber, String search_key) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);
        Page<Product> page;
        if (search_key.equals("")) {
            page = product_dao.findAll(pageRequest);
        } else {
            page = product_dao.findByActualPriceGreaterThanEqual(pageRequest, search_key);
        }
        return page.getContent();
    }

        public List<Product> find_by_price_less_than ( int pageNumber, String search_key){
            PageRequest pageable = PageRequest.of(pageNumber, 10);
            Page<Product> page;
            if (search_key.equals("")) {
                page = product_dao.findAll(pageable);
            } else {
                page = product_dao.findByActualPriceLessThan(pageable, search_key);
            }
              return page.getContent();
        }

    public Product updateProduct(long id, Product productRequest) {
        // get the product from DB by id
        // update with new value getting from request
        Product existingProduct = product_dao.findById(id).get();
        existingProduct.setProductName(productRequest.getProductName());
        existingProduct.setProductDescription(productRequest.getProductDescription());
        existingProduct.setActualPrice(productRequest.getActualPrice());
        existingProduct.setDiscountPrice(productRequest.getDiscountPrice());
        return product_dao.save(existingProduct);
    }



    public Product updateFieldsOfUserById(long id, Map<String, Optional> map) {

        Product product = product_dao.findById(id).get();

        for (Map.Entry<String, Optional> hm : map.entrySet()) {

            String keyFromMap = hm.getKey();

            if (keyFromMap.equals("productName")) {

                Optional<Object> oa = hm.getValue();
                String value = oa.map(Object::toString).orElse(null);
                product.setProductName(value);

            }

            if (keyFromMap.equals("productDescription")) {

                Optional<Object> oa = hm.getValue();
                String value1 = oa.map(Object::toString).orElse(null);
                product.setProductDescription(value1);

            }

            if (keyFromMap.equals("actualPrice")) {

                Optional<Object> oa = hm.getValue();
                String value2 = oa.map(Object::toString).orElse(null);
                product.setActualPrice(Double.parseDouble(value2));

            }

            if (keyFromMap.equals("discountPrice")) {

                Optional<Integer> oa = hm.getValue();
                Integer value = Integer.valueOf(oa.get());
                product.setDiscountPrice(value);

            }


        }

        return product_dao.save(product);
    }




}

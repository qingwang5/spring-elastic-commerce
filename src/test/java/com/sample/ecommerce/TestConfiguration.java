package com.sample.ecommerce;

import com.sample.ecommerce.service.CategoryService;
import com.sample.ecommerce.service.ProductService;
import com.sample.ecommerce.service.TermService;
import javax.annotation.PostConstruct;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zols.datatore.exception.DataStoreException;

@SpringBootApplication
@EnableBatchProcessing
public class TestConfiguration {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TermService termService;

    @PostConstruct
    private void setup() throws DataStoreException {
        categoryService.deleteAll();
//        productService.deleteAll();
//        
//        termService.deleteAll();
    }

}

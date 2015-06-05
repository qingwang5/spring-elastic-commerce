/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ecommerce.service;

import com.sample.ecommerce.Application;
import com.sample.ecommerce.domain.ProductsList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ProductServiceTest {
    
    @Autowired
    private ProductService productService;
    
    @Test
    public void test_listProducts() {
        ProductsList aggregations = productService.list();
        Assert.assertNotNull(aggregations);
    }
}

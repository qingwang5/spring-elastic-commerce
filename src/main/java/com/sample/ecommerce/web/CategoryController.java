/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.ecommerce.web;

import com.sample.ecommerce.service.CategoryService;
import static com.sample.ecommerce.util.HttpUtil.getPageUrl;
import static com.sample.ecommerce.util.HttpUtil.getQuery;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zols.datastore.query.Query;
import org.zols.datatore.exception.DataStoreException;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{categoryId}")
    public String browseByCategory(Model model,
            @PathVariable("categoryId") String categoryId,
            HttpServletRequest request,
            Pageable pageable) throws DataStoreException {        
        return browseByCategoryWithKeyword(model, categoryId, null, request, pageable);
    }

    @RequestMapping(value = "/{categoryId}/{keyword}")
    public String browseByCategoryWithKeyword(Model model,
            @PathVariable("categoryId") String categoryId,
            @PathVariable("keyword") String keyword,
            HttpServletRequest request,
            Pageable pageable) throws DataStoreException {
        Query query = getQuery(request);
        model.addAttribute("query", query);        
        model.addAttribute("category", categoryService.findOne(categoryId,true));
        model.addAttribute("parents", categoryService.getParents(categoryId));
        model.addAttribute("aggregations", categoryService.findByCategory(categoryId, keyword, query,pageable));
        String pageUrl = getPageUrl(request);
        model.addAttribute("pageurl", pageUrl);
        int indexOfQuestionMark = pageUrl.indexOf("?");
        model.addAttribute("cleanpageurl", (indexOfQuestionMark == -1) ? pageUrl : pageUrl.substring(0, indexOfQuestionMark));
        return "shop";
    }

}

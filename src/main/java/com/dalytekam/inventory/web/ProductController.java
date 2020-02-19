package com.dalytekam.inventory.web;

import com.dalytekam.inventory.Dao.ProductRepository;
import com.dalytekam.inventory.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
@RequestMapping("/index")
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "5")int size,
                        @RequestParam(name ="keyword",defaultValue = "")String kw){
    Page<Product> productsPage = productRepository.search("%"+kw+"%",PageRequest.of(page, size, Sort.unsorted()));
    int[] pagesCount = new int[productsPage.getTotalPages()];
    model.addAttribute("allTheProducts",productsPage.getContent());
    model.addAttribute("pages",pagesCount);
    model.addAttribute("size", size);
    model.addAttribute("currentPage",page);
    model.addAttribute("keyword",kw);
        return "products";
    }
@RequestMapping("/delete")
    public String delete(Long id, String kw, int page, int size){
     productRepository.deleteById(id);
    return "redirect:/index?page="+page+"&keyword="+kw+"&size="+size;
    }
    @RequestMapping(value = "/addProduct",method = RequestMethod.GET)
    public String addProduct(Model model){
    model.addAttribute("product",new Product());
    return "addAProductForm";
    }
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(Model model,Long id){
    Product pr = productRepository.findById(id).orElse(null);
        model.addAttribute("product",pr);
        return "editAProductForm";
    }


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(Model model, @Valid Product product, BindingResult bindingResult){
    if(bindingResult.hasErrors()){
        return "addAProductForm";
    }
    model.addAttribute("savedProduct",product);
        productRepository.save(product);
        return "saveConfirmation";
    }
    @RequestMapping(value = "/")
    public String home()
    {
        return "redirect:/index";
    }

    @RequestMapping(value = "/403")
    public String accessDenied()
    {
        return "denied";
    }
}

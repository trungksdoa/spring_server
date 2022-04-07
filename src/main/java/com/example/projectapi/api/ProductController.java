package com.example.projectapi.api;


import com.example.projectapi.Model.Catagory;
import com.example.projectapi.Model.Product;
import com.example.projectapi.Model.Product2;
import com.example.projectapi.Service.FIle.FileDBService;
import com.example.projectapi.Service.Product.ProductService;
import com.example.projectapi.Service.catagory.CatagoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import com.
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/product/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class ProductController {

    private final FileDBService fileDBService;
    private final ProductService productService;
    private final CatagoryService catagoryService;

    @GetMapping
    public ResponseEntity<List<Product2>> getProducts() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @GetMapping("catagorys")
    public ResponseEntity<List<Catagory>> getCatagorys() {
        return ResponseEntity.ok().body(catagoryService.getAllCatagory());
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteProduct(@RequestParam String id) {
        productService.deleteProduct(Long.parseLong(id));
        return ResponseEntity.ok().body("OK");
    }

    @DeleteMapping("catagory/delete")
    public ResponseEntity<String> deleteCatagory(@RequestParam String id) {
        catagoryService.deleteCatagory(Long.parseLong(id));
        return ResponseEntity.ok().body("OK");
    }

    @RequestMapping(value = "", method = RequestMethod.POST, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product2>> saveProduct(@RequestBody List<Product2> products) {
        for (Product2 items : products) {
            productService.saveProduct(new Product2(
                    null,
                    items.getName(),
                    items.getBarrel_price(),
                    items.getWhirlwind_price(),
                    items.getSingle_price(),
                    items.getGift(),
                    items.getType(),
                    items.getCategory()
            ));
        }
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product2> updateProduct(@RequestBody Product2 products) {

        Product2 product_ = productService.getProduct(products.getId());

        product_.setName(products.getName());
        product_.setBarrel_price(products.getBarrel_price());
        product_.setWhirlwind_price(products.getWhirlwind_price());
        product_.setSingle_price(products.getSingle_price());
        product_.setGift(products.getGift());
        product_.setType(products.getType());
        product_.setCategory(products.getCategory());

        productService.saveProduct(product_);
        return ResponseEntity.ok().body(products);
    }

    @RequestMapping(value = "catagory", method = RequestMethod.POST, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Catagory>> saveCatagorys(@RequestBody List<Catagory> catagory) {
        for (Catagory items : catagory) {
            productService.saveCatagory(new Catagory(
                    null,
                    items.getName(),
                    Long.parseLong(0 + "")
            ));
        }
        return ResponseEntity.ok().body(catagory);
    }


    @RequestMapping(value = "catagory", method = RequestMethod.PUT, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Catagory> UpdateCatagorys(@RequestBody Catagory catagory) {
        Catagory catagory_ = catagoryService.getCatagory(catagory.getId());


        catagory_.setName(catagory.getName());
        catagory_.setParent(catagory.getId());
        productService.saveCatagory(catagory_);
        return ResponseEntity.ok().body(catagory);
    }
}

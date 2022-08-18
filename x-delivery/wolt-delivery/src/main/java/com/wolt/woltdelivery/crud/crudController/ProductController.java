package com.wolt.woltdelivery.crud.crudController;

import com.wolt.woltdelivery.crud.crudService.ProductService;
import com.wolt.woltdelivery.dto.DtoProduct;
import com.wolt.woltdelivery.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody DtoProduct dtoProduct) {
        return productService.save(dtoProduct);
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<String> delete(@PathVariable long idProduct) {
        return productService.softDelete(idProduct);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return productService.updateProduct(product);
    }


}

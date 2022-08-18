package com.wolt.woltdelivery.crud.crudService;

import com.wolt.woltdelivery.dto.DtoProduct;
import com.wolt.woltdelivery.entity.Product;
import com.wolt.woltdelivery.repo.RepoProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
final RepoProduct repoProduct;


    public ResponseEntity<Product> save(DtoProduct dtoProduct){
    if (dtoProduct!=null){
        Product build = Product.builder().name(dtoProduct.getName()).
                                          price(dtoProduct.getPrice()).
                                          isDelete(dtoProduct.getIsDelete()).build();

        Product saveProduct = repoProduct.save(build);
        return new ResponseEntity<>(saveProduct, HttpStatus.OK);
    }
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> softDelete(long idProduct){
        Product byIdProduct = repoProduct.findByIdProduct(idProduct);
        if (byIdProduct!=null){
            repoProduct.deleteById(idProduct);
        return new ResponseEntity<>("Product delete success",HttpStatus.OK);
        }
        return new ResponseEntity<>("Product delete failed",HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<Product> updateProduct(Product product){
        Product byIdProduct = repoProduct.findByIdProduct(product.getIdProduct());
        if (byIdProduct!=null){
            Product updateProduct = repoProduct.save(product);
            return new ResponseEntity<>(updateProduct,HttpStatus.OK);
        }
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }




























}

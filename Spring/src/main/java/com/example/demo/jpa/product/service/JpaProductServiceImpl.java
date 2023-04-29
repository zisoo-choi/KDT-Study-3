package com.example.demo.jpa.product.service;

import com.example.demo.jpa.product.entity.JpaProduct;
import com.example.demo.jpa.product.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JpaProductServiceImpl implements JpaProductService {

    final private JpaProductRepository productRepository;

    @Override
    public List<JpaProduct> list() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "productId"));
    }

    @Override
    public JpaProduct read(Long productId) {
        Optional<JpaProduct> maybeJpaProduct = productRepository.findById(productId);

        if(maybeJpaProduct.equals("")) {
            log.info("정보가 없습니다!");
            return null;
        }
        return maybeJpaProduct.get();
    }

    @Override
    public JpaProduct register(JpaProduct jpaProduct) {
        return productRepository.save(jpaProduct);
    }
    @Override
    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }
}

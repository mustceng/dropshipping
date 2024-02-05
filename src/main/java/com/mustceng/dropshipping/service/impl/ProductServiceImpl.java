package com.mustceng.dropshipping.service.impl;

import com.mustceng.dropshipping.dto.ProductDTO;
import com.mustceng.dropshipping.entity.category.CategoryMapper;
import com.mustceng.dropshipping.entity.product.Product;
import com.mustceng.dropshipping.entity.product.ProductMapper;
import com.mustceng.dropshipping.entity.productdiscount.ProductDiscount;
import com.mustceng.dropshipping.exception.ApiException;
import com.mustceng.dropshipping.exception.ResponseCode;
import com.mustceng.dropshipping.exception.Severity;
import com.mustceng.dropshipping.repository.ProductDiscountRepository;
import com.mustceng.dropshipping.repository.ProductRepository;
import com.mustceng.dropshipping.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductDiscountRepository productDiscountRepository;

	public ProductServiceImpl(ProductRepository productRepository, ProductDiscountRepository productDiscountRepository) {
		this.productRepository = productRepository;
		this.productDiscountRepository = productDiscountRepository;
	}

	@Override
	public ProductDTO getProductById(Long id){
		return ProductMapper.INSTANCE.to(productRepository.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND, Severity.ERROR, "Product")));
	}
	@Override
	public Page<ProductDTO> getProductByCategoryId(Long id,Pageable pageable){
		Page<Product> productPage = productRepository.findByCategoryId(id,pageable);
		List<ProductDTO> productDTOList = productPage.stream().map(ProductMapper.INSTANCE::to).collect(Collectors.toList());
		PageImpl<ProductDTO> productDTOPage = new PageImpl<>(productDTOList, pageable, productPage.getTotalElements());
		return productDTOPage;
	}
	@Override
	public Page<ProductDTO> getAllProducts(Pageable pageable){
		Page<Product> productPage = productRepository.findAll(pageable);
		List<ProductDTO> productDTOList = productPage.stream().map(ProductMapper.INSTANCE::to).collect(Collectors.toList());
		PageImpl<ProductDTO> productDTOPage = new PageImpl<>(productDTOList, pageable, productPage.getTotalElements());
		return productDTOPage;
	}
	@Override
	@Transactional
	public ProductDTO save(ProductDTO productDTO){
		Product productSaved = productRepository.save(ProductMapper.INSTANCE.from(productDTO));
		return ProductMapper.INSTANCE.to(productSaved);
	}

	@Override
	@Transactional
	public ProductDTO applyDiscount(ProductDTO productDTO){
		ProductDiscount productDiscount = productDiscountRepository.findByProductIdAndDiscountNonExpiredIsTrue(productDTO.getId());
		Product product = productRepository.findById(productDTO.getId()).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND, Severity.ERROR, "Product"));
		if(productDiscount != null){
			BigDecimal discountPrice = product.getPrice().multiply(productDiscount.getDiscountRatio()).divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING);
			product.setNetPrice(product.getPrice().subtract(discountPrice));
			Product productSaved = productRepository.save(product);
			return ProductMapper.INSTANCE.to(productSaved);
		}else
			throw new ApiException(ResponseCode.DATA_NOT_FOUND, "Discounted product");

	}

	@Override
	@Transactional
	public BigDecimal calculateTotalValueAllProducts(){
		return productRepository.totalValueProducts();
	}


	@Override
	@Transactional
	public ProductDTO update(Long id,ProductDTO productDTO){
		Product updateProduct = productRepository.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND, Severity.ERROR, "Product"));

		updateProduct.setBrand(productDTO.getBrand());
		updateProduct.setCategory(CategoryMapper.INSTANCE.from(productDTO.getCategory()));
		updateProduct.setPrice(productDTO.getPrice());
		updateProduct.setRating(productDTO.getRating());
		updateProduct.setTitle(productDTO.getTitle());
		updateProduct.setImageUrl(productDTO.getImageUrl());

		Product productUpdated = productRepository.save(updateProduct);
		return ProductMapper.INSTANCE.to(productUpdated);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}

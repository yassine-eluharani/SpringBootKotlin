package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.ProductRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import org.springframework.stereotype.Service
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        val productEntity = productRepository.findBySku(sku)
        return productEntity?.toProductResponse()
    }

    fun addProduct(productRequest: ProductRequest): ProductResponse {
        val currentDateTime = ZonedDateTime.now(ZoneId.of("UTC"))
        val productEntity = ProductEntity(
            sku = productRequest.sku,
            name = productRequest.name,
            description = productRequest.description,
            price = productRequest.price,
            createdAt = currentDateTime,
            updatedAt = currentDateTime,
            stock = productRequest.stock
        )
        val savedProduct = productRepository.save(productEntity)
        return savedProduct.toProductResponse()
    }

}


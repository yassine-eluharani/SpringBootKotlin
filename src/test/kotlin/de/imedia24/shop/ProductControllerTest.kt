import de.imedia24.shop.controller.ProductController
import de.imedia24.shop.domain.product.ProductRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.math.BigDecimal

class ProductControllerTest {
    private val productService = mock(ProductService::class.java)
    private val productController = ProductController(productService)

    @Test
    fun `test findProductsBySku`() {
        val sku = "123"
        val decimalValue = BigDecimal("123.45")
        val productResponse = ProductResponse("123", "Product 1", "Description", decimalValue , 199)
        `when`(productService.findProductBySku(sku)).thenReturn(productResponse)
        val responseEntity: ResponseEntity<ProductResponse> = productController.findProductsBySku(sku)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == productResponse)
    }

    @Test
    fun `test findProductsBySkus`() {
        val skus = listOf("123", "456")
        val decimalValue = BigDecimal("123.45")
        val product1 = ProductResponse("123", "Product 1", "Description", decimalValue , 199)
        val product2 = ProductResponse("456", "Product 2", "Description 2", decimalValue , 2)
        val productResponses = listOf(product1, product2)

        `when`(productService.findProductBySku(anyString())).thenReturn(null)
        skus.forEachIndexed { index, sku ->
            `when`(productService.findProductBySku(sku)).thenReturn(productResponses[index])
        }
        val responseEntity: ResponseEntity<List<ProductResponse>> = productController.findProductsBySkus(skus)

        // Assert the response
        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == productResponses)
        assert(responseEntity.body?.size == 2)
    }


    @Test
    fun `test addProduct`() {
        val productRequest = ProductRequest( "123456", "Product 2", "This is a sample product.", BigDecimal(9.99), 2 )
        val addedProduct = ProductResponse( "123456", "Product 2", "This is a sample product.", BigDecimal(9.99), 2 )

        `when`(productService.addProduct(productRequest)).thenReturn(addedProduct)

        val responseEntity: ResponseEntity<ProductResponse> = productController.addProduct(productRequest)

        assert(responseEntity.statusCode == HttpStatus.CREATED)
        assert(responseEntity.body == addedProduct)
    }
}
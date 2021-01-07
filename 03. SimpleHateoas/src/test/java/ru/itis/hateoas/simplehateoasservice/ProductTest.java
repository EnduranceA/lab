package ru.itis.hateoas.simplehateoasservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.simplehateoasservice.models.Product;
import ru.itis.hateoas.simplehateoasservice.models.Seller;
import ru.itis.hateoas.simplehateoasservice.services.BookingService;
import ru.itis.hateoas.simplehateoasservice.services.ProductService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        when(productService.updatePrice(1L, 20)).thenReturn(getProduct());
    }

    @Test
    public void updatePriceOfProductTest() {
        try {
            mockMvc.perform(put("/products/1/updatePrice?price=20"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value(getProduct().getTitle()))
                    .andExpect(jsonPath("$.price").value(getProduct().getPrice()))
                    .andExpect(jsonPath("$.count").value(getProduct().getCount()))
                    .andDo(document("update_price", responseFields(
                            fieldWithPath("id").description("Идентификатор товара"),
                            fieldWithPath("title").description("Название товара"),
                            fieldWithPath("price").description("Цена товара"),
                            fieldWithPath("count").description("Количество товара")
                    )));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Product getProduct() {
        Seller seller = Seller.builder()
                .id(1L)
                .email("vdv@inbox.ru")
                .password("uhghgorokg5")
                .build();
        return Product.builder()
                .id(1L)
                .title("product1")
                .seller(seller)
                .price(20)
                .count(10)
                .build();
    }
}

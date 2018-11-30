package com.spring.cloud.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.common.Json;
import com.spring.cloud.service.consumer.controller.BookConsumerController;
import com.spring.cloud.service.consumer.model.Book;
import lombok.AllArgsConstructor;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.cloud.contract.wiremock.WireMockRestServiceServer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ServiceConsumerApplication.class)
@AutoConfigureWireMock(port = 0)
public class ServiceConsumerApplicationTests {
    @Autowired
    private BookConsumerController bookConsumerController;
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {
        Book book = new Book();
        book.setId(1);
        book.setAuthor("shark");
        //book.setPublishDate(LocalDate.now());
        book.setTitle("King");
        book.setPublisher("Tinghua university");
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(book);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        stubFor(get(urlEqualTo("http://localhost/service-provider/book/1")).willReturn(aResponse()
//                .withHeader("Content-Type", "text/plain").withBody(json)));


        MockRestServiceServer server = WireMockRestServiceServer.with(this.restTemplate)
                .baseUrl("http://localhost/service-provider/book/1").stubs("classpath:/t2/book.json")
                .build();
        assertThat(this.bookConsumerController.getBook(1)).isEqualTo(book);
        server.verify();
    }

}

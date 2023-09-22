package br.com.erudio.integrationtests.contenttype;

import br.com.erudio.configs.TestConfigs;
import br.com.erudio.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.erudio.vo.PersonVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerJsonTest extends AbstractIntegrationTest {
    
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static PersonVO personVO;
    
    @BeforeAll
    public static void setUp() {
	objectMapper = new ObjectMapper();
	objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	
	personVO = new PersonVO();
    }
    
    @Test
    @Order(1)
    void testCreate() throws JsonProcessingException {
	mockPerson();
	specification = new RequestSpecBuilder()
	.addHeader(TestConfigs.HEADER_PARAM_ORIGIN, "http://localhost:3000")
	.setBasePath("/api/person/v1/create")
	.setPort(TestConfigs.SERVER_PORT)
	.addFilter(new RequestLoggingFilter(LogDetail.ALL))
	.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
	.build();
	var content = given()
	    .spec(specification)
		.contentType(TestConfigs.CONTENT_TYPE_JSON)
		.body(personVO)
		.when()
		.post()
	    .then()
		.statusCode(200)
	    .extract()
		.body()
		    .asString();
	PersonVO vo = objectMapper.readValue(content, PersonVO.class);
	personVO = vo;
	
	assertTrue(vo.getId() > 0);
	assertNotNull(vo.getId());
	assertNotNull(vo.getLastName());
	assertNotNull(vo.getFirstName());
	assertNotNull(vo.getAddress());
	assertNotNull(vo.getGender());
	assertEquals("Richard",vo.getFirstName());
	assertEquals("Stallman",vo.getLastName());
	assertEquals("New York City, New York, US",vo.getAddress());
	assertEquals("male",vo.getGender());
    }
    
    private void mockPerson() {
	personVO.setFirstName("Richard");
	personVO.setLastName("Stallman");
	personVO.setAddress("New York City, New York, US");
	personVO.setGender("male");
    }
    
}

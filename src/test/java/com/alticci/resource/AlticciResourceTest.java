package com.alticci.resource;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@QuarkusTest
@TestHTTPEndpoint(AlticciResource.class)
class AlticciResourceTest {

  @ParameterizedTest(name = "[Test-{index}] - /alticci/{0}")
  @DisplayName("Test HTTP GET element by index")
  @CsvSource({
      "0, 0",
      "1, 1",
      "2, 1",
      "3, 1",
      "4, 2",
  })
  void getElementIndexTest(int index, int value) {
    var path = "/" +index;

    given()
        .accept("application/json")
        .when()
        .get(path)
        .then()
        .statusCode(200)
        .body("index",is(index))
        .body("value",is(value));
  }

  @ParameterizedTest(name = "[Test-{index}] - /alticci/{0} ")
  @DisplayName("Test HTTP GET element using negative index.")
  @ValueSource(ints = {-1, -2, -3})
  void getElementNegativeIndexTest(int index) {
    var path = "/" +index;
    given()
        .accept("application/json")
        .when()
        .get(path)
        .then()
        .statusCode(500);
  }

  @ParameterizedTest(name = "[Test-{index}] - /alticci/{0} ")
  @DisplayName("Test - HTTP GET element using String")
  @ValueSource(strings = {"a","b","c"})
  void getElementInvalidValueTest(String index) {
    var path = "/" +index;
    given()
        .accept("application/json")
        .when()
        .get(path)
        .then()
        .statusCode(404);
  }

}
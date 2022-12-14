package com.alticci.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@QuarkusTest
class AlticciServiceTest {

  @Inject
  AlticciService alticciService;
  @ParameterizedTest(name = "[Test-{index}] Index: {0} : Value: {1}")
  @DisplayName("Test get element by index")
  @CsvSource({
      "0, 0",
      "1, 1",
      "2, 1",
      "3, 1",
      "4, 2",
  })
  void getElementIndexTest(int index, int value) {
    var result = alticciService.getElement(index);
    assertEquals(value,result);
  }
  @ParameterizedTest(name = "[Test-{index}] Index: {0}")
  @DisplayName("Test get element by index negative")
  @ValueSource(ints = {-1, -2, -3})
  void getElementIndexNegativeTest(int index) {
    assertThrows(IndexOutOfBoundsException.class ,()->alticciService.getElement(index));
  }
}
package com.alticci.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
public record ElementDTO(@JsonProperty("index")int index, @JsonProperty("value")int value) {
}

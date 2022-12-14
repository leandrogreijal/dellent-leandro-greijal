package com.alticci.service;

import io.quarkus.cache.CacheKey;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.enterprise.context.ApplicationScoped;
import io.quarkus.cache.CacheResult;

@ApplicationScoped
public class AlticciService {

  @CacheResult(cacheName = "alticci-cache")
  public Integer getElement(@CacheKey int index) {
    return IntStream
        .range(0,index+1)
        .boxed()
        .map(this::calculate)
        .collect(Collectors.toList())
        .get(index);
  }

  private int calculate(Integer element){
    return  (element < 2) ? (element==0) ?0 : 1 : calculate(element - 3) + calculate(element- 2);
  }

}

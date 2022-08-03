package com.ivanduri.repository.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanduri.utils.commons.TestData;
import lombok.NonNull;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Repository<T> {

  private Class<T> aClass;

  private TestData testData;

  protected Repository() {
    ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
    this.aClass = (Class) type.getActualTypeArguments()[0];
    this.testData = new TestData();
  }

  @NonNull
  public T mapper(Class<T> aClass, Map<String, String> map) {
    return new ObjectMapper().convertValue(map, aClass);
  }

  @NonNull
  public T mapperByKey(Map<String, String> map) {
    final ObjectMapper mapper = new ObjectMapper();
    return mapper.convertValue(
        this.filterByKey(
            map,
            x -> x.startsWith(aClass.getSimpleName().replace("[blank]", "")),
            aClass.getSimpleName()),
        aClass);
  }

  @NonNull
  public <K, V> T mapperByPredicate(Class<T> aClass, Map<K, V> map, Predicate<K> predicate) {
    return new ObjectMapper()
        .convertValue(this.filterByKey(map, predicate, aClass.getSimpleName()), aClass);
  }

  @NonNull
  public <K, V> Map<K, V> filterByKey(Map<K, V> map, Predicate<K> predicate) {
    return map.entrySet().stream()
        .filter(entry -> predicate.test(entry.getKey()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  @NonNull
  public <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
    return map.entrySet().stream()
        .filter(entry -> predicate.test(entry.getValue()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  @NonNull
  protected T getEntity(String path, Predicate<T> predicate) {
    Optional<T> optionalProject =
        this.testData.getObjects(aClass, path).stream().filter(predicate).findAny();
    return optionalProject.orElseGet(
        () -> optionalProject.orElseThrow(() -> new RuntimeException("Could not get entity")));
  }

  @NonNull
  private <K, V> Map<String, String> filterByKey(
      Map<K, V> map, Predicate<K> predicate, String token) {
    return map.entrySet().stream()
        .filter(entry -> predicate.test(entry.getKey()))
        .collect(
            Collectors.toMap(
                entry -> entry.getKey().toString().replace(String.format("%s ", token), ""),
                entry -> entry.getValue().toString().replace("[blank]", "")));
  }
}

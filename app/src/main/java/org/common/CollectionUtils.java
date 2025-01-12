package org.common;

import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.IntStream;

public class CollectionUtils {
  public static <T> List<List<T>> chunk(List<T> list, int size) {
    return IntStream.range(0, (int) Math.ceil((double) list.size() / (double) size))
        .boxed()
        .map(i -> list.stream().skip((long) i * size).limit(size).collect(Collectors.toList()))
        .collect(Collectors.toList());
  }
}

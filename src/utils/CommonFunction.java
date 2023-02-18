package utils;

import java.util.List;
import java.util.stream.Collectors;

public class CommonFunction {

    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        return list1.stream().filter(list2::contains).collect(Collectors.toList());
    }
    public static <T> List<T> intersectionlll(List<T> list1, List<T> list2) {
        return list1.stream().filter(list2::contains).collect(Collectors.toList());
    }
}

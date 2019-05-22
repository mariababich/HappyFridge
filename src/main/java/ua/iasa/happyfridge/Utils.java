package ua.iasa.happyfridge;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.apache.commons.lang3.StringUtils.substringAfterLast;

@UtilityClass
public class Utils {

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public static String getFileExtension(String fullFileName){
        return substringAfterLast(fullFileName, ".");
    }
}

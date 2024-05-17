package onlinestore.service.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {
    public static boolean isAnyBlank(String... strs) {
        for(String str: strs){
            if(str == null || str.trim().isEmpty()) return true;
        }
        return false;
    }
}

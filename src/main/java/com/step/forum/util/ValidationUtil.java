package com.step.forum.util;

public class ValidationUtil {
    public static boolean validate(String... fields){
        for (String s : fields){
            if (s != null || s.trim().isEmpty()){
                return true;
            }
        }
        return false;
    }
}

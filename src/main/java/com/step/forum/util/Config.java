package com.step.forum.util;

import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("conf.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private Config(){}

    private static final String IMAGE_UPLOADS_PATH = "image.uploads.path";

    public static String getImageUploadsPath() {
        return properties.getProperty(IMAGE_UPLOADS_PATH);
    }
}





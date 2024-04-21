package ru.ibs.pages;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Класс для работы с Properties,
 * помогает получить ссылку на стенд из файла
 */
public class ConfProp {
    public static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;

    static {
        try {
            fileInputStream = new FileInputStream("target/classes/conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}

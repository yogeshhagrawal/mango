package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentReader {

    private static final Properties prop = new Properties();

    static {
        String env = System.getProperty("env");
        if (env == null) {
            env = "qa";
        }
        loadProperties(env + ".properties");
    }

    public static void loadProperties(String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = EnvironmentReader.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new RuntimeException("Failed to load " + fileName + " file!");
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Failed to load " + fileName + " file!", ioException);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getBaseUri() {
        return prop.getProperty("baseUri");
    }

    public static String getBrowser() {
        return prop.getProperty("browser");
    }
}

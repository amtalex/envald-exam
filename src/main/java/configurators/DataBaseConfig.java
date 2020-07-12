package configurators;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataBaseConfig {
    private static DataBaseConfig instance;

    private String dbUrl;

    private DataBaseConfig() {

        try {
            Properties props = new Properties();
            props.load(new FileReader(new File("src/main/resources/db.properties")));

            dbUrl = props.getProperty("sqliteUrl");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static DataBaseConfig getInstance() {
        if (instance == null) {
            instance = new DataBaseConfig();
        }
        return instance;
    }

    public String getDbUrl() {
        return dbUrl;
    }
}

package mcorp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.stream.Collectors;

public class UtilTest {

    public static String getTestObjectFromResources(String fileName) {
        ClassLoader classLoader = UtilTest.class.getClassLoader();
        URL resource = classLoader.getResource("tests/" + fileName);
        try {
            return new BufferedReader(new FileReader(resource.getFile())).lines().collect(Collectors.joining());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

package utilities;

import com.github.javafaker.Faker;

public class Constants {
    static Faker faker = new Faker();

    public static String configFolderPath = "src/main/resources/config/";
    public static String url = ConfigManager.getProperty("url");
    public static String fName = faker.name().firstName(); //ConfigManager.getProperty("fName");
    public static String lName = faker.name().firstName(); //ConfigManager.getProperty("lName");
    public static String loginName = fName + lName;
    public static String password = ConfigManager.getProperty("password");
    public static String age = ConfigManager.getProperty("age");
    public static String gender = ConfigManager.getProperty("gender");
    public static String address = faker.address().fullAddress();
    public static String phone = faker.phoneNumber().cellPhone();

    public enum TestResult {
        TEST_PASSED,
        TEST_FAILED,
    }

//    public void fakeData {
//        Faker faker = new Faker();
//        fName = faker.name().firstName();
//        lName = faker.name().lastName();
//    }
}

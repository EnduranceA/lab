import java.io.File;

public class TestMain {
    public static void main(String[] args) {
        File file = new File("src/main/resources/templates/mail.ftl");
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
    }
}


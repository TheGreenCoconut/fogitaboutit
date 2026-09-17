import java.io.File;
import java.io.IOException;

public class Git {
    public static void main(String[] args) {
        init();
    }

    public static void init() {
        File git = new File("./git/");
        if (!git.mkdir()) {
            System.out.println("Git Repository Already Exists");
            return;
        }

        File objects = new File("./git/objects/");
        File head = new File("./git/HEAD");
        File index = new File("./git/index");

        try {
            if (!objects.mkdir() || !head.createNewFile() || !index.createNewFile()) {
                System.out.println("Git Repository Already Exists");
                return;
            }
        } catch (IOException e) {
            System.out.println("Failed to create Git Repository");
        }

        System.out.println("Git Repository Created at " + git.getParentFile().getAbsolutePath());
    }
}

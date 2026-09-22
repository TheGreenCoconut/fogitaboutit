import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HexFormat;

public class Git {
    public static void main(String[] args) {
        init();

        save("text.md");
    }

    /*
     * Initializes the repository's necessary files. Creates the git directory with children
     * objects/, HEAD and index. Returns true if the repository could be created, returns false if
     * not or if the current repo already exists
     */
    public static boolean init() {
        File git = new File("./git/");
        if (!git.mkdir()) {
            System.out.println("Git Repository Already Exists");
            return false;
        }

        File objects = new File("./git/objects/");
        File head = new File("./git/HEAD");
        File index = new File("./git/index");

        try {
            if (!objects.mkdir() || !head.createNewFile() || !index.createNewFile()) {
                System.out.println("Git Repository Already Exists");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Failed to create Git Repository");
            return false;
        }

        System.out.println("Git Repository Created at " + git.getParentFile().getAbsolutePath());
        return true;
    }

    /*
     * Hashes a file based on its contents. Returns a SHA-1 hash unique to this file's contents.
     */
    public static String hashFile(String filePath) {
        try {
            StringBuilder contents = new StringBuilder();
            FileReader reader = new FileReader(filePath);
            int c;
            while ((c = reader.read()) != -1) {
                contents.append((char) (c));
            }
            reader.close();

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(contents.toString().getBytes());
            return HexFormat.of().formatHex(md.digest());
        } catch (Exception e) {
            System.out.println("File could not be hashed");
            return null;
        }

    }

    /*
     * Creates a blob file for the given filepath and stores it inside the objects folder. Returns
     * true if the file could be saved to objects, returns false otherwise.
     */
    public static boolean save(String filePath) {
        try {
            File blob = new File("./git/objects/" + hashFile(filePath));
            FileReader reader = new FileReader(filePath);
            FileWriter writer = new FileWriter(blob);
            int c;
            while ((c = reader.read()) != -1) {
                writer.write((char) (c));
            }
            reader.close();
            writer.close();

            return true;

        } catch (Exception e) {
            System.out.println("Failed to save changes to file at " + filePath);
            return false;
        }
    }
}

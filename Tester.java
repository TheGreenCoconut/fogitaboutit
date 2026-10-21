import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        GitWrapper git = new GitWrapper();
        // git.init();
        // git.add("Hello.txt");
        // git.add("test/test.txt");

        // git.commit("Quinn", "theres no way they messed this up");

        // git.add("test/goodbye.txt");
        // git.commit("Quinn", "thi is the second one");
        
        git.checkout("839880f330f1b50ec9f31468aeeea283e1a9a54c");

    }
}

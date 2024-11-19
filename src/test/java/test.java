import com.polaris.minilzo.MiniLzoUtils;

public class test {
    public static void main(String[] args) {
        byte[] compress = MiniLzoUtils.compress("sdfasdfsaf".getBytes());
        System.out.println(compress);
    }
}

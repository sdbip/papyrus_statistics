import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class CollectionTest {
    @Test
    public void canReadSampleCSV() {
        final ClassLoader classLoader = getClass().getClassLoader();
        final URL resource = classLoader.getResource("sample.csv");
        assert resource != null;
        final File file = new File(resource.getFile());

        assertTrue(file.exists());
    }
}

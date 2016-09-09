package no.rmz.gtp.gtpsim;

import java.io.File;
import org.junit.Test;


public class GtpParserTest {
    private String GTP_SAMPLE_1_PATH = "";

    public GtpParserTest() {
    }

    @Test
    public void testSomeMethod() {
        final GtpParser p = new GtpParser();
        final File f = new File(GTP_SAMPLE_1_PATH = "");
        p.parseFile(f);
    }

}

package no.rmz.gtp.gtpsim;

import java.io.File;
import org.junit.Ignore;
import org.junit.Test;


public class GtpParserTest {
    private String GTP_SAMPLE_1_PATH = "";

  
    @Ignore
    @Test
    public void testParsefile() {
        final GtpParser p = new GtpParser();
        final File f = new File(GTP_SAMPLE_1_PATH = "");
        p.parseFile(f);
    }
}

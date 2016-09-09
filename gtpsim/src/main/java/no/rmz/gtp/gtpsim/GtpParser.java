package no.rmz.gtp.gtpsim;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import java.io.File;

/**
 * Will eventually parse GTP packets
 * (https://en.wikipedia.org/wiki/GPRS_Tunnelling_Protocol).
 *
 * A free GTP implementation can be found here:
 *
 * https://sourceforge.net/projects/nwgtpv2/
 */
public class GtpParser {

    final void parseFile(final File f) {
        checkNotNull(f);
        checkArgument(f.exists());
    }
}

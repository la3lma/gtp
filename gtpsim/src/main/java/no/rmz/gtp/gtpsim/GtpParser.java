package no.rmz.gtp.gtpsim;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import java.io.File;

/**
 * Will eventually parse GTP packets into frames that can be sent through a
 * simulated GGSN or similar (PGW).
 *
 * (https://en.wikipedia.org/wiki/GPRS_Tunnelling_Protocol).
 *
 * A free GTP implementation can be found here:
 *
 * https://sourceforge.net/projects/nwgtpv2/
 *
 * reading pcap files.
 *
 * http://jnetpcap.com/?q=examples/offline h
 *
 *
 * TODO: o Split the sample pcap files into multiple sequentially numbered raw
 * gtp packets (possibly with Ether/TCP/UDP headers) o Make a simple parser for
 * byte arrays to interpret them as GTP packets. o Make simple packet assembler
 * to generate new packets. o Simulate the setup of a GTP-U session between to
 * parties that tunnels IP packets between them. o
 *
 */
public class GtpParser {



    final void parseFile(final File f) {
        checkNotNull(f);
        checkArgument(f.exists());

        // Parse file as a stream of GTPV1 packets.
    }
}

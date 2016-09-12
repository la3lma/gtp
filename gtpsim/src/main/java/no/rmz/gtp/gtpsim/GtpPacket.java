package no.rmz.gtp.gtpsim;

import com.google.common.base.Preconditions;

/**
 *
 * Bit position in packet: 0-2 Version 3 Protocol Type 4 Reserved 5 Extension
 * header flag 6 Sequence number flag 7 N-PDU number flag 8-15 Message type
 * 16-31 Message length 32-63 TEID 16 bit optional sequence number 8 bit
 * Optional N-PDU field 8 bit Optional next extension header type
 */
public final class GtpPacket {

    final OctetPacket pkt;

    public GtpPacket(final OctetPacket bytePacket) {
        this.pkt = Preconditions.checkNotNull(bytePacket);
    }

    public GtpPacket(final byte[] bytes) {
        this(new OctetPacket(bytes));
    }

    /**
     * The first header field in a GTP' packet is the 3-bit version field. For
     * GTP' v2, this has a value of 2 (hence the name GTP' v2).
     *
     * @return a nonegative integer.
     */
    public int getVersion() {
        return pkt.getUnsignedInt(0, 2);
    }

    public void setVersion(final int version) {
        pkt.setUnsignedInt(0, 2, version);
    }

    /**
     * a 1-bit value that differentiates GTP' (value 0) from GTP (value 1).
     *
     * @return a nonegative integer.
     */
    public int getProtocolType() {
        return pkt.getUnsignedInt(3, 1);
    }

    public void setProtocolType(int value) {
        pkt.setUnsignedInt(3, 1, value);
    }

    /**
     * A 3-bit reserved field (must be 1's).
     *
     * @return a nonegative integer.
     */
    public int getReserved() {
        return pkt.getUnsignedInt(4, 1);
    }

    public void setReserved() {
        pkt.setUnsignedInt(4, 1, 0b1);
    }

    /**
     * Extension header flag.
     * @return
     */
    public int getExtensionHeaderFlag() {
        return pkt.getUnsignedInt(5, 1);
    }

    public void setExtensionHeaderFlag(int value) {
        pkt.setUnsignedInt(5, 1, value);
    }

    /**
     * N-PDU flag number
     *
     * @return
     */
    public int getSeqNoFlag() {
        return pkt.getUnsignedInt(6, 1);
    }

    public void setSeqNoFlag(int value) {
        pkt.setUnsignedInt(6, 1, value);
    }

    /**
     * N-PDU flag number
     *
     * @return
     */
    public int getNpduFlag() {
        return pkt.getUnsignedInt(7, 1);
    }

    public void setNpduFlag(int value) {
        pkt.setUnsignedInt(7, 1, value);
    }
}

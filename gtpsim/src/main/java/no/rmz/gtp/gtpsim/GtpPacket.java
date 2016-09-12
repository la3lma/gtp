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
        return pkt.getUnsignedInt(5, 3);
    }

    public void setVersion(final int version) {
        pkt.setUnsignedInt(5, 3, version);
    }

    /**
     * a 1-bit value that differentiates GTP' (value 0) from GTP (value 1).
     *
     * @return a nonegative integer.
     */
    public int getProtocolType() {
        return pkt.getUnsignedInt(4, 1);
    }

    public void setProtocolType(int value) {
        pkt.setUnsignedInt(4, 1, value);
    }

    /**
     * A 3-bit reserved field (must be 1's).
     *
     * @return a nonegative integer.
     */
    public int getReserved() {
        return pkt.getUnsignedInt(1, 3);
    }

    public void setReserved() {
        pkt.setUnsignedInt(4, 3, 0b111);
    }

    /**
     * A 1-bit value that for GTP' version 0 indicates if using a 20 byte header
     * (value 0) (as per GTP) or this 6 byte header. This bit must be unset
     * (value 0) for subsequent GTP' versions and in these does not indicate the
     * header length as this must always be 6 bytes.
     *
     * @return a nonegative integer.
     */
    public int getHdrLen() {
        return pkt.getUnsignedInt(0, 1);
    }

    public void setHdrLen(int value) {
        pkt.setUnsignedInt(0, 1, value);
    }
}

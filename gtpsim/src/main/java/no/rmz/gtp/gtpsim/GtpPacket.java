package no.rmz.gtp.gtpsim;

import com.google.common.base.Preconditions;

/**
 * https://en.wikipedia.org/wiki/GPRS_Tunnelling_Protocol
 *
 * // XXX At present this is GTPv1
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

    /**
     * Message type
     *
     * @return
     */
    public int getMessageType() {
        return pkt.getUnsignedInt(8, 8);
    }

    public void setMessageType(int value) {
        pkt.setUnsignedInt(8, 8, value);
    }

    /**
     * Message length
     *
     * @return
     */
    public int getMessageLength() {
        return pkt.getUnsignedInt(16, 16);
    }

    public void setMessageLength(int value) {
        pkt.setUnsignedInt(16, 16, value);
    }

    /**
     * Tunnel Endpoint Identifier
     *
     * @return
     */
    public int getTunnelEndpointIdentifier() {
        return pkt.getUnsignedInt(32, 32);
    }

    public void setTunnelEndpointIdentifier(int value) {
        pkt.setUnsignedInt(32, 32, value);
    }

    /**
     * Tunnel Endpoint Identifier
     *
     * @return
     */
    public int getNPDUNumber() {
        return pkt.getUnsignedInt(80, 8);
    }

    public void setNPDUNumber(int value) {
        pkt.setUnsignedInt(80, 8, value);
    }

    /**
     * Tunnel Endpoint Identifier
     *
     * @return
     */
    public int getSequenceNumber() {
        return pkt.getUnsignedInt(64, 16);
    }

    public void setSequenceNumber(int value) {
        pkt.setUnsignedInt(64, 16, value);
    }

    // XXX Extension headers missing

}

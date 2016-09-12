package no.rmz.gtp.gtpsim;

import com.google.common.base.Preconditions;

public final class BytePacket {

    final byte[] packet;

    public BytePacket(final byte[] packet) {
        this.packet = Preconditions.checkNotNull(packet);
    }

    public int getBits(final int offset, final int mask) {
        return getBits(offset, (byte) mask);
    }

    public int getBits(final int offset, final byte mask) {
        final byte unmasked = packet[0];
        final byte masked = (byte) (unmasked & mask);
        final byte signedResult = (byte) (masked >> offset);
        final byte unsignedResult = (byte) ((byte) 0xff & signedResult);
        return unsignedResult;
    }

    public void setBits(final int value, final int offset, final byte mask) {
        final byte bv = (byte) value;
        final byte storedBits = (byte) (bv << offset);
        packet[0] = (byte) (storedBits | (byte) (packet[0] & mask));
    }
}

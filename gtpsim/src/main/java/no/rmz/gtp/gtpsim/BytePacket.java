package no.rmz.gtp.gtpsim;

import com.google.common.base.Preconditions;
import static com.google.common.base.Preconditions.checkArgument;

public final class BytePacket {

    final byte[] packet;

    public BytePacket(final byte[] packet) {
        this.packet = Preconditions.checkNotNull(packet);
    }

    private byte getMask(final int offset, final int length) {
        checkLengthAndOffset(length, offset);

        byte result = 0b1;
        for (int i = 0; i < length - 1; i++) {
            result <<= 1;
            result |= 0b1;
        }
        result <<= offset;
        return result;
    }

    private void checkLengthAndOffset(final int length, final int offset) {
        checkArgument(length > 0);
        checkArgument(length <= 7);
        checkArgument(offset >= 0);
        checkArgument(offset <= 7);
    }

    /**
     * Getting bits off the first byte of the array, interpreting them as an
     * unsigned binary number.
     *
     * @param offset
     * @param length
     * @return
     */
    public int getBits(
            final int offset,
            final int length) {
        final byte mask = getMask(offset, length);
        final byte unmasked = packet[0];
        byte masked = (byte) (unmasked & mask);
        final byte rawShifted = (byte) (masked >> offset);
        final byte shifted = (byte) ((byte) 0xff & rawShifted);
        return shifted;
    }

    /**
     * Setting bits in the first byte of the array.
     *
     * @param offset
     * @param length
     * @param value
     */
    public void setBits(
            final int offset,
            final int length,
            final int value) {
        checkLengthAndOffset(length, offset);

        final byte mask = getMask(offset, length);
        final byte bv = packet[0];
        final byte storedBits = (byte) ((value << offset) | (bv & ~mask));

        packet[0] = storedBits;
    }
}

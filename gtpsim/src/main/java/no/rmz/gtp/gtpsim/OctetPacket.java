package no.rmz.gtp.gtpsim;

import com.google.common.base.Preconditions;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * Implement a packet, consisting of a sequence of octets, that can be
 * interpreted as a sequence of nonnegative binary encoded integers of varying
 * positive bit-lengths.
 *
 * The intent of the class is to facilitate experimentation with network
 * protocol packets, where it is necessary to both parse and construct such
 * packets on a per-field level.
 *
 * Efficiency is not a primary concern for this implementation, but clarity and
 * correctness is.
 *
 * XXX NOTE: The set/get methods will eventually be able to set/get bit fields
 * anywhere in the packet, but for now, and until proven able to do so
 * correctly, we will only look at bit-fields within the first octet.
 *
 * XXX NOTE: I am pretty confident this thing screws up whenever the eighth bit
 * of the first octet is set (sign). I'd really like to know how to get around
 * that issue.
 *
 * Take a peek at this one, just to see how it can be done:
 *
 * https://github.com/biasedbit/efflux/blob/master/src/main/java/com/biasedbit/efflux/packet/DataPacket.java
 */
public final class OctetPacket {

    final byte[] packet;

    public OctetPacket(final byte[] packet) {
        this.packet = Preconditions.checkNotNull(packet);
    }

    private void checkLengthAndOffset(final int length, final int offset) {
        checkArgument(length > 0);
        checkArgument(length <= 7);
        checkArgument(offset >= 0);
        checkArgument(offset <= 7);
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

    /**
     * Getting bits off the first byte of the array, interpreting them as an
     * unsigned binary number.
     *
     * @param offset
     * @param length
     * @return
     */
    public int getUnsignedInt(
            final int offset,
            final int length) {
        checkLengthAndOffset(length, offset);

        final byte mask = getMask(offset, length);
        final byte unmasked = packet[0];
        final byte masked = (byte) (unmasked & mask);
        final byte rawShifted = (byte) (masked >> offset);
        final byte result = (byte) ((byte) 0xff & rawShifted);

        if (result < 0) {
            throw new RuntimeException("Detected negative number where unsigned is required: " + result);
        } else {
            return result;
        }
    }

    /**
     * Setting bits in the first byte of the array.
     *
     * @param offset
     * @param length
     * @param value
     */
    public void setUnsignedInt(
            final int offset,
            final int length,
            final int value) {
        checkLengthAndOffset(length, offset);
        checkArgument(value >= 0);

        final byte mask = getMask(offset, length);
        final byte bv = packet[0];
        final byte storedBits = (byte) ((value << offset) | (bv & ~mask));

        packet[0] = storedBits;
    }

    @Override
    public String toString() {
        return "BytePacket{" + "packet=" + packet[0] + '}';
    }
}

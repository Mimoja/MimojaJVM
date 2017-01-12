package mimojajvm.ClassFormat.Attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;
import mimojajvm.ClassFormat.Constants.ConstantPool;
import mimojajvm.ClassFormat.Constants.ConstantPoolInfo;

public class AttributeInfo {

    public AttributeTag type;
    public int attribute_name_index;
    public long attribute_length;

    protected short[] info;

    public AttributeInfo(AttributeTag type, DataInputStream dis, ConstantPool cp, int nameIndex) throws IOException {
        this.type = type;
        attribute_name_index = nameIndex;
        attribute_length = dis.readUnsignedShort() << 16 | dis.readUnsignedShort();
        parse(dis, cp, attribute_length);
    }

    protected void parse(DataInputStream dis, ConstantPool cp, long length) throws IOException {
        info = new short[(int) length];
        System.out.println("------INFOLENGTH: " + length);
        for (int i = 0; i < length; i++) {
            info[i] = (short) dis.readUnsignedByte();
        }
    }

    public String toString(ConstantPool cp) {
        StringBuilder builder = new StringBuilder();
        Formatter print = new Formatter(builder, Locale.getDefault());
        ConstantPoolInfo info = cp.get(attribute_name_index);
        print.format("attribute_name_index: 0x%02X, (%s) ", attribute_name_index, info != null ? info.toString() : "UNKOWN");
        print.format("attribute_length: %d ", attribute_length);

        return builder.toString();
    }
}

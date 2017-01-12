package mimojajvm.ClassFormat.Fields;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;
import mimojajvm.ClassFormat.AccessFlags;
import mimojajvm.ClassFormat.Attribute.AttributePool;
import mimojajvm.ClassFormat.Constants.ConstantPool;
import mimojajvm.ClassFormat.Constants.ConstantPrimitive;

public class FieldInfo {

    AccessFlags access_flags;
    int name_index;
    int descriptor_index;
    int attributes_count;
    AttributePool attribute_pool;

    FieldInfo(DataInputStream dis, ConstantPool cp) throws IOException {
        access_flags = new AccessFlags(dis);
        name_index = dis.readUnsignedShort();
        descriptor_index = dis.readUnsignedShort();
        attributes_count = dis.readUnsignedShort();
        attribute_pool = new AttributePool(dis, cp, attributes_count);
    }

    public String toString(ConstantPool cp) {
        StringBuilder builder = new StringBuilder();
        Formatter print = new Formatter(builder, Locale.getDefault());

        print.format("%-25s      ", access_flags.toString());
        print.format("0x%02X %-20s ", name_index, ((ConstantPrimitive<String>) cp.get(name_index)).getValue());
        
        print.format("        0x%02X ", descriptor_index);
        print.format("           %d ", attributes_count);
        print.format("%s \n", attribute_pool.toString(cp));

        return builder.toString();
    }
}

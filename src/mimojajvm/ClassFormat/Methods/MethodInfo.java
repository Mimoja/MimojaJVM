package mimojajvm.ClassFormat.Methods;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;
import mimojajvm.ClassFormat.AccessFlags;
import mimojajvm.ClassFormat.Attribute.AttributePool;
import mimojajvm.ClassFormat.Constants.ConstantClassInfo;
import mimojajvm.ClassFormat.Constants.ConstantPool;
import mimojajvm.ClassFormat.Constants.ConstantPoolInfo;
import mimojajvm.ClassFormat.Constants.ConstantPrimitive;
import mimojajvm.ClassFormat.Constants.ConstantReference;

public class MethodInfo {

    public AccessFlags access_flags;
    public int name_index;
    public int descriptor_index;
    public int attributes_count;
    public AttributePool attribute_pool;

    MethodInfo(DataInputStream dis, ConstantPool cp) throws IOException {
        access_flags = new AccessFlags(dis);
        name_index = dis.readUnsignedShort();
        descriptor_index = dis.readUnsignedShort();
        attributes_count = dis.readUnsignedShort();
        attribute_pool = new AttributePool(dis, cp, attributes_count);
    }

    public String toString(ConstantPool cp) {

        StringBuilder builder = new StringBuilder();
        Formatter print = new Formatter(builder, Locale.getDefault());

        print.format("%-30s      ", access_flags.toString());
        String name = "";
        ConstantPoolInfo info = cp.get(name_index);
        if (info instanceof ConstantClassInfo) {
        } else {
            name = info.toString();
        }
        print.format("0x%02X %-50s ", name_index, name);

        print.format("0x%02X ", descriptor_index);
        print.format("           %d ", attributes_count);
        print.format("%s \n", attribute_pool.toString(cp));

        return builder.toString();
    }
}

package mimojajvm.ClassFormat;

import mimojajvm.ClassFormat.Methods.MethodPool;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;
import mimojajvm.ClassFormat.Attribute.AttributePool;
import mimojajvm.ClassFormat.Constants.ConstantPool;
import mimojajvm.ClassFormat.Constants.ConstantPrimitive;
import mimojajvm.ClassFormat.Fields.FieldPool;
import mimojajvm.ClassFormat.Interface.InterfacePool;
import mimojajvm.ClassFormat.Methods.MethodInfo;

public class ClassFile {

    public long magic;
    public int minor_version;
    public int major_version;
    public int constant_pool_count;
    public ConstantPool constant_pool;
    public AccessFlags access_flags;
    public int this_class;
    public int super_class;
    public int interface_count;
    public InterfacePool interface_pool;
    public int fields_count;
    public FieldPool field_pool;
    public int method_count;
    public MethodPool method_pool;
    public int attributes_count;
    public AttributePool attribute_pool;

    public ClassFile(String filename) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(filename));

        magic = (long) dis.readUnsignedShort() << 16 | dis.readUnsignedShort();
        minor_version = dis.readUnsignedShort();
        major_version = dis.readUnsignedShort();
        constant_pool_count = dis.readUnsignedShort();
        constant_pool = new ConstantPool(dis, constant_pool_count);
        access_flags = new AccessFlags(dis);
        this_class = dis.readUnsignedShort();
        super_class = dis.readUnsignedShort();
        interface_count = dis.readUnsignedShort();
        interface_pool = new InterfacePool(dis, constant_pool, interface_count);
        fields_count = dis.readUnsignedShort();
        field_pool = new FieldPool(dis, constant_pool, fields_count);
        method_count = dis.readUnsignedShort();
        method_pool = new MethodPool(dis, constant_pool, method_count);
        attributes_count = dis.readUnsignedShort();
        attribute_pool = new AttributePool(dis, constant_pool, attributes_count);
    }

    public MethodInfo findMethod(String methodName) {
        for (int i = 0; i < method_count; i++) {
            int nameIndex = method_pool.get(i).name_index;
            String name = ((ConstantPrimitive<String>) constant_pool.get(nameIndex)).getValue();
            if (name.equals(methodName)) {
                return method_pool.get(i);
            }
        }
        return null;
    }

    private static String getStringFromVersion(int version) {
        String versionString = "UNKOWN";
        switch (version) {
            case 0x35:
                versionString = "JAVA SE 9";
                break;
            case 0x34:
                versionString = "JAVA SE 8";
                break;
            case 0x33:
                versionString = "JAVA SE 7";
                break;
            case 0x32:
                versionString = "JAVA SE 6.0";
                break;
            case 0x31:
                versionString = "JAVA SE 5.0";
                break;
            case 0x30:
                versionString = "JDK 1.4";
                break;
            case 0x2F:
                versionString = "JDK 1.3";
                break;
            case 0x2E:
                versionString = "JDK 1.2";
                break;
            case 0x2D:
                versionString = "JDK 1.1";
                break;
            default:
                break;
        }
        return versionString;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Formatter print = new Formatter(builder, Locale.getDefault());

        print.format("Classfile: Magic Number: 0x%02X \n", this.magic);
        print.format("Classfile: Major Number:  0x%02X (%s) \n", this.major_version, getStringFromVersion(major_version));
        print.format("Classfile: Minor Number: 0x%02X \n", minor_version);
        print.format("Classfile: Constant Pool Count: 0x%02X %d \n", this.constant_pool_count, this.constant_pool_count);
        print.format("%s", constant_pool.toString());
        print.format("Classfile: access flag: %s 0x%02X %d \n", this.access_flags.toString(), this.access_flags.getVal(), this.access_flags.getVal());
        print.format("Classfile: this class: %s \n", constant_pool.getClassName(this_class));
        print.format("Classfile: super class: %s \n", constant_pool.getClassName(super_class));
        print.format("Classfile: interface count: 0x%02X %d \n", this.interface_count, this.interface_count);
        print.format("%s", interface_pool.toString());
        print.format("Classfile: field count: 0x%02X %d \n", this.fields_count, this.fields_count);
        print.format("%s", field_pool.toString(constant_pool));
        print.format("Classfile: method count: 0x%02X %d \n", this.method_count, this.method_count);
        print.format("%s", method_pool.toString(constant_pool));
        print.format("Classfile: attributes_count: 0x%02X %d \n", this.attributes_count, this.attributes_count);
        print.format("%s", attribute_pool.toString(constant_pool));
        return builder.toString();
    }

}

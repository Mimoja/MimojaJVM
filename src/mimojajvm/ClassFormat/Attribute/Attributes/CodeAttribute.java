package mimojajvm.ClassFormat.Attribute.Attributes;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import mimojajvm.ClassFormat.Attribute.AttributeInfo;
import mimojajvm.ClassFormat.Attribute.AttributePool;
import mimojajvm.ClassFormat.Attribute.AttributeTag;
import mimojajvm.ClassFormat.Constants.ConstantPool;
import mimojajvm.ClassFormat.Constants.ConstantPoolInfo;

public class CodeAttribute extends AttributeInfo {

    public int max_stack;
    public int max_locals;
    public int code_length;
    public short[] code;
    public int exception_table_length;
    public CodeException[] exception_table;
    public int attributes_count;
    public AttributePool attributes;

    public CodeAttribute(AttributeTag type, DataInputStream dis, ConstantPool cp, int nameIndex) throws IOException {
        super(type, dis, cp, nameIndex);
    }

    public class CodeException {

        public int startPc;
        public int endPc;
        public int handlerPc;
        public int catchType;

        public CodeException(DataInputStream dis) throws IOException {
            this.startPc = dis.readUnsignedShort();
            this.endPc = dis.readUnsignedShort();
            this.handlerPc = dis.readUnsignedShort();
            this.catchType = dis.readUnsignedShort();
        }
    }

    @Override
    protected void parse(DataInputStream dis, ConstantPool cp, long length) throws IOException {
        max_stack = dis.readUnsignedShort();
        max_locals = dis.readUnsignedShort();

        code_length = dis.readUnsignedShort() << 16 | dis.readUnsignedShort();;
        code = new short[code_length];
        for (int i = 0; i < code_length; i++) {
            code[i] = (short) dis.readUnsignedByte();
        }

        exception_table_length = dis.readUnsignedShort();
        exception_table = new CodeException[exception_table_length];
        for (int i = 0; i < exception_table_length; i++) {
            exception_table[i] = new CodeException(dis);
        }
        attributes_count = dis.readUnsignedShort();
        attributes = new AttributePool(dis, cp, attributes_count);
    }

    public short[] getCode() {
        return code;
    }

    public int getMaxLocals() {
        return max_locals;
    }

    public CodeException[] getCodeExceptions() {
        return exception_table;
    }

    public int getMaxstack() {
        return max_stack;
    }

    @Override
    public String toString(ConstantPool cp) {

        StringBuilder builder = new StringBuilder();
        Formatter print = new Formatter(builder, Locale.getDefault());

        print.format("max_stack: %d ", max_stack);
        print.format("max_locals: %d ", max_locals);
        print.format("code_length: %d ", code_length);
        print.format("exception_table_length: %d ", exception_table_length);
        print.format("attributes_count: %d ", attributes_count);
        print.format("|| %s ", attributes.toString(cp));

        return super.toString(cp) + builder.toString();
    }
}

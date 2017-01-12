package mimojajvm.ClassFormat.Attribute.Attributes;

import java.io.DataInputStream;
import java.io.IOException;
import mimojajvm.ClassFormat.Attribute.AttributeInfo;
import mimojajvm.ClassFormat.Attribute.AttributeTag;
import mimojajvm.ClassFormat.Constants.ConstantPool;

public class ConstantValueAttribute extends AttributeInfo {

    public int valueIndex;

    public ConstantValueAttribute(AttributeTag type, DataInputStream dis, ConstantPool cp, int nameIndex) throws IOException {
        super(type, dis, cp, nameIndex);
    }

    @Override
    protected void parse(DataInputStream dis, ConstantPool cp, long length) throws IOException {
        valueIndex = dis.readUnsignedShort();

    }

    @Override
    public String toString(ConstantPool cp) {
        return String.format("valueIndex: 0x%02X, (%s)", valueIndex, cp.get(valueIndex).toString());
    }
}

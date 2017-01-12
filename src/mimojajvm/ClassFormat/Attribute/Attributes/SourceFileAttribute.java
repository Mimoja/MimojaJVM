package mimojajvm.ClassFormat.Attribute.Attributes;

import java.io.DataInputStream;
import java.io.IOException;
import mimojajvm.ClassFormat.Attribute.AttributeInfo;
import mimojajvm.ClassFormat.Attribute.AttributeTag;
import mimojajvm.ClassFormat.Constants.ConstantPool;

public class SourceFileAttribute extends AttributeInfo {

    public int sourcefileIndex;

    public SourceFileAttribute(AttributeTag type, DataInputStream dis, ConstantPool cp, int nameIndex) throws IOException {
        super(type, dis, cp, nameIndex);
    }

    @Override
    public void parse(DataInputStream dis, ConstantPool cp, long length) throws IOException {
        this.sourcefileIndex = dis.readUnsignedShort();
    }

    @Override
    public String toString(ConstantPool cp) {
        return String.format("%s sourcefileIndex 0x%02X (%s)", super.toString(cp), sourcefileIndex, cp.get(sourcefileIndex).toString());

    }
}

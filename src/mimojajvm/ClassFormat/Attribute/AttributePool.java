package mimojajvm.ClassFormat.Attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mimojajvm.ClassFormat.Attribute.Attributes.CodeAttribute;
import mimojajvm.ClassFormat.Attribute.Attributes.ConstantValueAttribute;
import mimojajvm.ClassFormat.Attribute.Attributes.LineNumberTableAttribute;
import mimojajvm.ClassFormat.Attribute.Attributes.LocalVariableTableAttribute;
import mimojajvm.ClassFormat.Attribute.Attributes.SourceFileAttribute;
import mimojajvm.ClassFormat.Constants.ConstantPool;
import mimojajvm.ClassFormat.Constants.ConstantPrimitive;

public class AttributePool {

    public AttributeInfo[] mEntries;
    public int mSize;

    public AttributePool(DataInputStream dis, ConstantPool cp, int size) throws IOException {
        mEntries = new AttributeInfo[size];
        this.mSize = size;

        for (int i = 0; i < size; i++) {
            mEntries[i] = parse(dis, cp);
        }
    }

    private AttributeInfo parse(DataInputStream dis, ConstantPool cp) throws IOException {
        int nameIndex = dis.readUnsignedShort();
        String type = ((ConstantPrimitive<String>) cp.get(nameIndex)).getValue();
        AttributeTag tag = AttributeTag.getTag(type);
        switch (tag) {
            case CODE:
                return new CodeAttribute(tag, dis, cp, nameIndex);
            case SOURCE_FILE:
                return new SourceFileAttribute(tag, dis, cp, nameIndex);
            case LOCAL_VARIABLE_TABLE:
                return new LocalVariableTableAttribute(tag, dis, cp, nameIndex);
            case LINE_NUMBER_TABLE:
                return new LineNumberTableAttribute(tag, dis, cp, nameIndex);
            case CONSTANT_VALUE:
                return new ConstantValueAttribute(tag, dis, cp, nameIndex);
            default:
                System.out.println("Attribute Pool: Unknown Attribute Type " + tag.name());
                return new AttributeInfo(tag, dis, cp, nameIndex);
        }
    }

    public String toString(ConstantPool cp) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mSize; i++) {
            builder.append(mEntries[i].toString(cp));
        }
        return builder.toString();
    }

    public List<AttributeInfo> getAll(AttributeTag attributeTag) {
        ArrayList<AttributeInfo> attributes = new ArrayList<>();
        for (AttributeInfo ai : mEntries) {
            if (ai.type.equals(attributeTag)) {
                attributes.add(ai);
            }
        }
        return attributes;
    }

}

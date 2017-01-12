package mimojajvm.ClassFormat.Constants;

import java.io.*;

public class ConstantPool {

    private ConstantPoolInfo[] mEntries;
    private final int mSize;

    public ConstantPool(DataInputStream dis, int size) throws IOException {
        mEntries = new ConstantPoolInfo[size];
        this.mSize = size - 1;
        int i = 0;

        while (i < size - 1) {
            mEntries[i + 1] = parse(dis);
            i += mEntries[i + 1].getEntryCount();
        }

    }

    private ConstantPoolInfo parse(DataInputStream dis) throws IOException {
        byte tag = dis.readByte();
        ConstantTag cTag = ConstantTag.getTag(tag);
        switch (cTag) {
            case CONSTANT_STRING:
                return new ConstantString(dis.readUnsignedShort());
            case CONSTANT_INT:
                return new ConstantPrimitive<Integer>(cTag, dis.readInt());
            case CONSTANT_FLOAT:
                return new ConstantPrimitive<Float>(cTag, dis.readFloat());
            case CONSTANT_LONG:
                return new ConstantPrimitive<Long>(cTag, dis.readLong());
            case CONSTANT_DOUBLE:
                return new ConstantPrimitive<Double>(cTag, dis.readDouble());
            case CONSTANT_UTF8:
                String stringVal = dis.readUTF();
                return new ConstantPrimitive<String>(cTag, stringVal);
            case CONSTANT_NAME_AND_TYPE:
                return new ConstantNameAndType(dis.readUnsignedShort(),
                        dis.readUnsignedShort());
            case CONSTANT_CLASS_INFO:
                return new ConstantClassInfo(dis.readUnsignedShort());
            case CONSTANT_FIELD_REF:
            case CONSTANT_METHOD_REF:
            case CONSTANT_INTERFACE_METHOD_REF:
                return new ConstantReference(cTag,
                        dis.readUnsignedShort(),
                        dis.readUnsignedShort());
            default:
                System.out.println("Unknown constant: " + tag);
                System.exit(-1);
        }
        return null;
    }

    public ConstantPoolInfo get(int index) {
        if (index < 0 || index > mEntries.length) {
            System.out.format("Invalid constant pool index: %d (not in range [0, %d])", index, mEntries.length);
            return null;
        } else if (mEntries[index] == null) {
            System.out.format("Invalid constant pool index: %d (entry undefined)", index);
            return null;
        }
        return mEntries[index];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(" ________________________________________________________________________________________________\n");
        builder.append("|Index  Entry type                 Entry values                                                 |\n");
        builder.append("|-----------------------------------------------------------------------------------------------|\n");
        for (int i = 0; i < mEntries.length; i++) {
            if (mEntries[i] != null) {
                if (mEntries[i] instanceof ConstantClassInfo) {
                    ConstantClassInfo entry = (ConstantClassInfo) mEntries[i];
                    builder.append(String.format("|0x%02X   %-25s  %s %-46s |\n",
                            i, entry.getTag(), entry.toString(), mEntries[entry.getNameIndex()].toString()));
                } else if (mEntries[i] instanceof ConstantString) {
                    ConstantString entry = (ConstantString) mEntries[i];
                    builder.append(String.format("|0x%02X   %-25s  %s (%-46s |\n",
                            i, entry.getTag(), entry.toString(), mEntries[entry.string_index].toString() + ")"));
                } else if (mEntries[i] instanceof ConstantNameAndType) {
                    ConstantNameAndType entry = (ConstantNameAndType) mEntries[i];
                    builder.append(String.format("|0x%02X   %-25s  %s (%-18s |\n",
                            i, entry.getTag(), entry.toString()
                            , mEntries[entry.name_index].toString()+ ")"));
                } else {
                    builder.append(String.format("|0x%02X   %-25s  %-60s |\n",
                            i, mEntries[i].getTag(), mEntries[i].toString()));
                }
            }
        }
        return builder.toString();

    }

    public String getClassName(int classPoolInfoIndex) {
        ConstantPoolInfo cpi = get(classPoolInfoIndex);
        if (cpi instanceof ConstantClassInfo) {
            int nameIndex = ((ConstantClassInfo) cpi).getNameIndex();
            return ((ConstantPrimitive<String>) get(nameIndex)).getValue();
        }

        return null;
    }

    public int getSize() {
        return this.mSize;
    }

    public ConstantPoolInfo getByIndexVal(String index) {
        for (int i = 0; i < mEntries.length; i++) {

            if (index.equalsIgnoreCase(String.format("0x%02x", i))) {
                return mEntries[i];
            }
        }
        return null;
    }

}

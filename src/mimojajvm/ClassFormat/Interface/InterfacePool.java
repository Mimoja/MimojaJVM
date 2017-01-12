package mimojajvm.ClassFormat.Interface;

import mimojajvm.ClassFormat.Constants.ConstantPool;
import java.io.DataInputStream;
import java.io.IOException;

public class InterfacePool {

    private int[] mEntries;
    private int mSize;
    private ConstantPool mCP;

    public InterfacePool(DataInputStream dis, ConstantPool cp, int size) throws IOException {
        this.mEntries = new int[size];
        this.mSize = size;
        this.mCP = cp;

        for (int i = 0; i < size; i++) {
            mEntries[i] = dis.readUnsignedShort();

        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Index  InterfaceRef      Name\n");
        builder.append("-------------------------------------------\n");
        for (int i = 0; i < mSize; i++) {
            builder.append(String.format("0x%02X   0x%-15X %s\n", i, mEntries[i], mCP.getClassName(mEntries[i])));

        }
        return builder.toString();
    }
}

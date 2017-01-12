package mimojajvm.ClassFormat.Methods;

import mimojajvm.ClassFormat.Methods.MethodInfo;
import java.io.DataInputStream;
import java.io.IOException;
import mimojajvm.ClassFormat.Constants.ConstantPool;

public class MethodPool {

    private MethodInfo[] mEntries;
    private int mSize;
    private ConstantPool mCP;

    public MethodPool(DataInputStream dis, ConstantPool cp, int size) throws IOException {
        this.mEntries = new MethodInfo[size];
        this.mSize = size;

        for (int i = 0; i < size; i++) {
            mEntries[i] = new MethodInfo(dis, cp);
        }

    }
    
    public MethodInfo get(int index) {
        if (index < 0 || index > mEntries.length) {
            System.out.format("Invalid constant pool index: %d (not in range [0, %d])", index, mEntries.length);
            return null;
        } else if (mEntries[index] == null) {
            System.out.format("Invalid constant pool index: %d (entry undefined)", index);
            return null;
        }
        return mEntries[index];
    }

    public int getSize() {
        return this.mSize;
    }

    public String toString(ConstantPool cp) {
        StringBuilder builder = new StringBuilder();
        builder.append("AcessFlag                                Name"
                + "                                               Descpriptor     Attribute Pool\n");
        builder.append("-------------------------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < mSize; i++) {
            builder.append(get(i).toString(cp));
        }

        return builder.toString();
    }
}

package mimojajvm.ClassFormat.Fields;

import java.io.DataInputStream;
import java.io.IOException;
import mimojajvm.ClassFormat.Constants.ConstantPool;

public class FieldPool {

    private FieldInfo[] mEntries;
    private int mSize;

    public FieldPool(DataInputStream dis, ConstantPool cp, int size) throws IOException {
        mEntries = new FieldInfo[size];
        this.mSize = size;

        for (int i = 0; i < size; i++) {
            mEntries[i] = new FieldInfo(dis, cp);
        }

    }

    public FieldInfo get(int index) {
        if (index < 0 || index > mEntries.length) {
            System.out.format("Invalid pool index: %d (not in range [0, %d])", index, mEntries.length);
            return null;
        } else if (mEntries[index] == null) {
            System.out.format("Invalid pool index: %d (entry undefined)", index);
            return null;
        }
        return mEntries[index];
    }

    public String toString(ConstantPool cp) {
        StringBuilder builder = new StringBuilder();
        builder.append("AcessFlag                      Name                              Descpriptor     Attribute Pool\n");
        builder.append("-----------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < mSize; i++) {
            builder.append(get(i).toString(cp));
        }

        return builder.toString();
    }
    
}

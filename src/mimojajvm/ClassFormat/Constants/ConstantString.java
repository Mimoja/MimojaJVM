package mimojajvm.ClassFormat.Constants;

import mimojajvm.ClassFormat.Constants.ConstantPoolInfo;

public class ConstantString extends ConstantPoolInfo {
    public int string_index;

    public ConstantString(int stringIndex) {
        super(ConstantTag.CONSTANT_STRING);
        this.string_index = stringIndex;
    }

    @Override
    public String toString() {
        return "String: 0x" + Integer.toHexString(string_index);
    }
}

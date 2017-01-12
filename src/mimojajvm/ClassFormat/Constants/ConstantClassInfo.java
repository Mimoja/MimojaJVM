package mimojajvm.ClassFormat.Constants;

public class ConstantClassInfo extends ConstantPoolInfo {

    public int name_index;

    public ConstantClassInfo(int nameIndex) {
        super(ConstantTag.CONSTANT_CLASS_INFO);
        this.name_index = nameIndex;
    }

    public int getNameIndex() {
        return name_index;
    }

    @Override
    public String toString() {
        return "ClassInfo: " + Integer.toHexString(name_index);
    }
}

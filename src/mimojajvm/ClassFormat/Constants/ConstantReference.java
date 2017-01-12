package mimojajvm.ClassFormat.Constants;

public class ConstantReference extends ConstantPoolInfo {

    public int classIndex;
    public int nameAndTypeIndex;

    public ConstantReference(ConstantTag tag, int classIndex, int nameAndTypeIndex) {
        super(tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append("(ClassIndex: ")
                .append(String.format("0x%02X", classIndex))
                .append(", NameAndTypeIndex: ")
                .append(String.format("0x%02X", nameAndTypeIndex))
                .append(")");
        return sb.toString();
    }
}

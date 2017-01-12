package mimojajvm.ClassFormat.Constants;

public class ConstantNameAndType extends ConstantPoolInfo {

    public int name_index;
    public int descriptorIndex;

    public ConstantNameAndType(int nameIndex, int descriptorIndex) {
        super(ConstantTag.CONSTANT_NAME_AND_TYPE);
        this.name_index = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append("(NameIndex: 0x")
                .append(Integer.toHexString(name_index))
                .append(", DescriptorIndex: 0x")
                .append(Integer.toHexString(descriptorIndex))
                .append(")");
        return sb.toString();
    }
}

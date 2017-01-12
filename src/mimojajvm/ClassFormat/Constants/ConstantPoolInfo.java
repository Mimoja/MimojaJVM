package mimojajvm.ClassFormat.Constants;

public class ConstantPoolInfo {

    protected ConstantTag mTag;

    public ConstantPoolInfo(ConstantTag tag) {
        this.mTag = tag;
    }

    public ConstantTag getTag() {
        return mTag;
    }

    public int getEntryCount() {
        if (mTag == ConstantTag.CONSTANT_DOUBLE || mTag == ConstantTag.CONSTANT_LONG) {
            return 2;
        }
        return 1;
    }

}

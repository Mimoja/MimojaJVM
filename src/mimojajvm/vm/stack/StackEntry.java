package mimojajvm.vm.stack;

public class StackEntry {

    private final EntryType mType;
    private final int mValue;

    public StackEntry(EntryType type, int val) {
        this.mType = type;
        this.mValue = val;
    }

    public EntryType getType() {
        return mType;
    }

    public int getValue() {
        return mValue;
    }

}

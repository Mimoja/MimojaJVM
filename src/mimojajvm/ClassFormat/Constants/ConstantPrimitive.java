package mimojajvm.ClassFormat.Constants;

public class ConstantPrimitive<T> extends ConstantPoolInfo {
    public  T value;

    public ConstantPrimitive(ConstantTag tag, T value) {
        super(tag);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

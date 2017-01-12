package mimojajvm.vm.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class StackFrame {
    // actual Stack
    private final Deque<StackEntry> mStack;

    public StackFrame(int size) {
        mStack = new ArrayDeque<>(size);
    }

    public void pushEntry(StackEntry entry) {
        mStack.push(entry);
    }

    public void pushInt(int value) {
        mStack.push(new StackEntry(EntryType.INT, value));
    }

    public void pushRef(int ref) {
        mStack.push(new StackEntry(EntryType.REF, ref));
    }

    private int pop(EntryType targetType) {
        StackEntry entry = mStack.pop();
        if (entry.getType() != targetType) {
            System.err.println("WARNING: Poping" + entry.getType().name()
                    + " as " + targetType.name());
        }
        return entry.getValue();
    }

    public StackEntry popEntry() {
        return mStack.pop();
    }

    public int popInt() {
        return (int) pop(EntryType.INT);
    }

    public int popRef() {
        return (int) pop(EntryType.REF);
    }
}

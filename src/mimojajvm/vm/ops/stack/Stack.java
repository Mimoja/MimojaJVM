package mimojajvm.vm.ops.stack;

import mimojajvm.vm.stack.StackEntry;
import mimojajvm.vm.stack.StackFrame;

public class Stack {

    public static int bipush(StackFrame stack, int nextByte, boolean verbose) {
        int value = nextByte;
        stack.pushInt(value);

        if (verbose) {
            System.out.format("push a byte %d onto the stack \n", value);
        }

        return 2;
    }

    public static int dup(StackFrame stack, boolean verbose) {
        StackEntry entry = stack.popEntry();
        stack.pushEntry(entry);
        stack.pushEntry(entry);

        if (verbose) {
            System.out.println("dup: duplicating stack entry with type " + entry.getType().name());
        }

        return 1;
    }

    public static int ldc(StackFrame stack, int nextByte, boolean verbose) {
        int value = nextByte;
        stack.pushRef(value);
        if (verbose) {
            System.out.format("ldc: push a constant index %d onto the stack \n", value);
        }

        return 2;
    }

    public static int op_return(boolean verbose) {
        if (verbose) {
            System.out.format("return \n");
        }

        return -1;
    }
}

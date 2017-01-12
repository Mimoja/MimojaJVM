package mimojajvm.vm.ops.stack;

import mimojajvm.vm.stack.StackFrame;

public class Const {

    public static int aload_0(StackFrame stack, boolean verbose) {
        stack.pushInt(0);

        if (verbose) {
            System.out.format("aload_0: push 0 to stack\n");
        }

        return 1;
    }
    
    public static int iconst_0(StackFrame stack, boolean verbose) {
        stack.pushInt(0);
        if (verbose) {
            System.out.format("iconst_0: push 0 to stack\n");
        }

        return 1;

    }

    public static int iconst_1(StackFrame stack, boolean verbose) {
        stack.pushInt(1);
        if (verbose) {
            System.out.format("iconst_1: push 1 to stack\n");
        }

        return 1;

    }

    public static int iconst_2(StackFrame stack, boolean verbose) {
        stack.pushInt(2);
        if (verbose) {
            System.out.format("iconst_2: push 2 to stack\n");
        }

        return 1;

    }

    public static int iconst_3(StackFrame stack, boolean verbose) {
        stack.pushInt(3);
        if (verbose) {
            System.out.format("iconst_3: push 3 to stack\n");
        }

        return 1;

    }

    public static int iconst_4(StackFrame stack, boolean verbose) {
        stack.pushInt(4);
        if (verbose) {
            System.out.format("iconst_4: push 4 to stack\n");
        }

        return 1;

    }

    public static int iconst_5(StackFrame stack, boolean verbose) {
        stack.pushInt(5);
        if (verbose) {
            System.out.format("iconst_5: push 5 to stack\n");
        }

        return 1;
    }
}

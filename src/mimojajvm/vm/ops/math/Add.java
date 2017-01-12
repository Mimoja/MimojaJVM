package mimojajvm.vm.ops.math;

import mimojajvm.vm.stack.StackFrame;

public class Add {

    public static int iadd(StackFrame stack, boolean verbose) {
        int value1 = stack.popInt();
        int value2 = stack.popInt();
        int result = value1 + value2;

        if (verbose) {
            System.out.format("iadd: %d + %d = %d\n", value1, value2, result);
        }

        stack.pushInt(result);
        return 1;
    }
}

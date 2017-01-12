package mimojajvm.vm.ops.math;

import mimojajvm.vm.stack.StackFrame;

public class Mul {

    public static int imul(StackFrame stack, boolean verbose) {
        int value1 = stack.popInt();
        int value2 = stack.popInt();
        int result = 0;
        result = value1 * value2;
        stack.pushInt(result);
        if (verbose) {
            System.out.format("imul : %d * %d = %d\n", value1, value2, result);
        }

        return 1;

    }
}

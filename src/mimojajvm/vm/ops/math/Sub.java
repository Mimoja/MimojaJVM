package mimojajvm.vm.ops.math;

import mimojajvm.vm.stack.StackFrame;

public class Sub {

    public static int isub(StackFrame stack, boolean verbose) {
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        int result = 0;
        result = value1 - value2;
        if (verbose) {
            System.out.format("isub : %d - %d = %d\n", value1, value2, result);
        }

        stack.pushInt(result);
        return 1;

    }
}

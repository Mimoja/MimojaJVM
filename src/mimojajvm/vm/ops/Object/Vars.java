package mimojajvm.vm.ops.Object;

import mimojajvm.LocalVariables;
import mimojajvm.vm.stack.StackFrame;

public class Vars {

    public static int iload(StackFrame stack, LocalVariables localVariables, int nextByte, boolean verbose) {
        int index = nextByte;
        int value = localVariables.integerVars[index];
        if (verbose) {
            System.out.format("iload: load value from local variable %d(%d)\n", index, localVariables.integerVars[index]);
        }

        stack.pushInt(value);

        return 2;
    }

    public static int iload_1(StackFrame stack, LocalVariables localVariables, boolean verbose) {
        int value = localVariables.integerVars[1];
        if (verbose) {
            System.out.format("iload_1: load value from local variable 1(%d)\n", localVariables.integerVars[1]);
        }

        stack.pushInt(value);
        return 1;

    }

    public static int iload_2(StackFrame stack, LocalVariables localVariables, boolean verbose) {
        int value = localVariables.integerVars[2];
        if (verbose) {
            System.out.format("iload_2: load value from local variable 2(%d)\n", localVariables.integerVars[2]);
        }

        stack.pushInt(value);
        return 1;

    }

    public static int iload_3(StackFrame stack, LocalVariables localVariables, boolean verbose) {
        int value = localVariables.integerVars[3];
        if (verbose) {
            System.out.format("iload_3: load value from local variable 3(%d)\n", localVariables.integerVars[3]);
        }

        stack.pushInt(value);
        return 1;

    }

    public static int istore(StackFrame stack, LocalVariables localVariables, int nextByte, boolean verbose) {
        int value = stack.popInt();
        int index = nextByte;
        if (verbose) {
            System.out.format("istore: store value into local variable %d(%d)\n", index, value);
        }

        localVariables.integerVars[index] = value;
        return 2;

    }

    public static int istore_1(StackFrame stack, LocalVariables localVariables, boolean verbose) {
        int value = stack.popInt();
        if (verbose) {
            System.out.format("istore_1: store value into local variable 1(%d)\n", value);
        }

        localVariables.integerVars[1] = value;
        return 1;

    }

    public static int istore_2(StackFrame stack, LocalVariables localVariables, boolean verbose) {
        int value = stack.popInt();
        if (verbose) {
            System.out.format("istore_2: store value into local variable 2(%d)\n", value);
        }

        localVariables.integerVars[2] = value;
        return 1;

    }

    public static int istore_3(StackFrame stack, LocalVariables localVariables, boolean verbose) {
        int value = stack.popInt();
        if (verbose) {
            System.out.format("istore_3: store value into local variable 3(%d)\n", value);
        }

        localVariables.integerVars[3] = value;
        return 1;

    }
}

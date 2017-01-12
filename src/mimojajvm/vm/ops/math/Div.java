/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mimojajvm.vm.ops.math;

import mimojajvm.vm.stack.StackFrame;

/**
 *
 * @author Mimoja
 */
public class Div {

    public static int idiv(StackFrame stack, boolean verbose) {
        int value2 = stack.popInt();
        int value1 = stack.popInt();
        int result = value1 / value2;
        stack.pushInt(result);

        if (verbose) {
            System.out.format("idiv: %d / %d = %d\n", value1, value2, result);
        }

        return 1;
    }
}

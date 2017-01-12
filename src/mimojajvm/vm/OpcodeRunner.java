package mimojajvm.vm;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPSize;
import mimojajvm.ClassFormat.Attribute.AttributeInfo;
import mimojajvm.ClassFormat.Methods.MethodInfo;
import mimojajvm.ClassFormat.Attribute.AttributeTag;
import mimojajvm.ClassFormat.Attribute.Attributes.CodeAttribute;
import mimojajvm.ClassFormat.ClassFile;
import mimojajvm.LocalVariables;
import mimojajvm.vm.ops.ByteCode;
import mimojajvm.vm.ops.Object.Objects;
import mimojajvm.vm.ops.Object.Vars;
import mimojajvm.vm.ops.math.Add;
import mimojajvm.vm.ops.math.Div;
import mimojajvm.vm.ops.math.Mul;
import mimojajvm.vm.ops.math.Sub;
import mimojajvm.vm.ops.stack.Const;
import mimojajvm.vm.ops.stack.Stack;
import mimojajvm.vm.stack.StackFrame;

public class OpcodeRunner {

    private static int executeOp(short[] code, int pc, StackFrame stack, ClassFile c, LocalVariables l, boolean verbose) {
        short op = code[pc];
        if (verbose) {
            System.out.format("%d: 0x%02X: ", pc, op);
        }
        switch (op) {
            case ByteCode.aload_0:
                return Const.aload_0(stack, verbose);
            case ByteCode.bipush:
                return Stack.bipush(stack, code[pc + 1], verbose);
            case ByteCode.dup:
                return Stack.dup(stack, verbose);
            case ByteCode.getstatic:
                return Objects.getstatic(stack, code[pc + 1], code[pc + 2], verbose);
            case ByteCode.iadd:
                return Add.iadd(stack, verbose);
            case ByteCode.iconst_0:
                return Const.iconst_0(stack, verbose);
            case ByteCode.iconst_1:
                return Const.iconst_1(stack, verbose);
            case ByteCode.iconst_2:
                return Const.iconst_2(stack, verbose);
            case ByteCode.iconst_3:
                return Const.iconst_3(stack, verbose);
            case ByteCode.iconst_4:
                return Const.iconst_4(stack, verbose);
            case ByteCode.iconst_5:
                return Const.iconst_5(stack, verbose);
            case ByteCode.idiv:
                return Div.idiv(stack, verbose);
            case ByteCode.iload:
                return Vars.iload(stack, l, code[pc + 1], verbose);
            case ByteCode.iload_1:
                return Vars.iload_1(stack, l, verbose);
            case ByteCode.iload_2:
                return Vars.iload_2(stack, l, verbose);
            case ByteCode.iload_3:
                return Vars.iload_3(stack, l, verbose);
            case ByteCode.imul:
                return Mul.imul(stack,verbose);
            case ByteCode.isub:
                return Sub.isub(stack,verbose);
            case ByteCode.istore:
                return Vars.istore(stack, l, code[pc + 1], verbose);
            case ByteCode.istore_1:
                return Vars.istore_1(stack, l, verbose);
            case ByteCode.istore_2:
                return Vars.istore_2(stack, l, verbose);
            case ByteCode.istore_3:
                return Vars.istore_3(stack, l, verbose);
            case ByteCode.invokespecial:
                return Objects.invokespecial(stack, c, code[pc + 1], code[pc + 2], verbose);
            case ByteCode.invokevirtual:
                return Objects.invokevirtual(stack, c, code[pc + 1], code[pc + 2], verbose);
            case ByteCode.ldc:
                return Stack.ldc(stack, code[pc + 1], verbose);
            case ByteCode.op_new:
                return Objects.op_new(stack, code[pc + 1], code[pc + 2], verbose);
            case ByteCode.return_op:
                return Stack.op_return(verbose);
            default:
                System.out.format("Unknown OpCode 0x%02X \n", op);
        }

        return 1;
    }

    public static int executeMethod(MethodInfo startup, StackFrame stack, ClassFile c, boolean verbose) {

        for (AttributeInfo ai : startup.attribute_pool.getAll(AttributeTag.CODE)) {
            LocalVariables vars = new LocalVariables();
            CodeAttribute ca = (CodeAttribute) ai;
            int pc = 0;
            do {
                // execute the Opcode
                int offset = executeOp(ca.code, pc, stack, c, vars, verbose);

                if (offset < 0) {
                    break;
                }

                pc += offset;

                if (pc >= ca.code.length) {
                    break;
                }

            } while (true);
        }
        return 0;
    }

}

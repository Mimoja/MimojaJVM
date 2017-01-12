package mimojajvm.vm.ops.Object;

import mimojajvm.ClassFormat.ClassFile;
import mimojajvm.ClassFormat.Constants.ConstantClassInfo;
import mimojajvm.ClassFormat.Constants.ConstantNameAndType;
import mimojajvm.ClassFormat.Constants.ConstantPoolInfo;
import mimojajvm.ClassFormat.Constants.ConstantPrimitive;
import mimojajvm.ClassFormat.Constants.ConstantReference;
import mimojajvm.ClassFormat.Constants.ConstantString;
import mimojajvm.ClassFormat.Methods.MethodInfo;
import mimojajvm.vm.OpcodeRunner;
import mimojajvm.vm.stack.EntryType;
import mimojajvm.vm.stack.StackEntry;
import mimojajvm.vm.stack.StackFrame;

public class Objects {

    public static int op_new(StackFrame stack, short nextByte1, short nextByte2, boolean verbose) {
        short object_ref = (short) (nextByte1 << 8 | nextByte2);
        if (verbose) {
            System.out.format("new: new object_ref %d\n", object_ref);
        }

        return 3;
    }

    public static int getstatic(StackFrame stack, short nextByte1, short nextByte2, boolean verbose) {
        int field_index = (nextByte1 << 8 | nextByte2);

        if (verbose) {
            System.out.format("getstatic %d \n", field_index);
        }

        stack.pushRef(field_index);

        return 3;
    }

    public static int invokespecial(StackFrame stack, ClassFile c, short nextByte1, short nextByte2, boolean verbose) {
        short method_index = (short) (nextByte1 << 8 | nextByte2);

        if (method_index >= c.method_pool.getSize()) {
            if (verbose) {
                System.out.format("\nunknown method %d \n", method_index);
            }
            return 3;
        }
        MethodInfo method = c.method_pool.get(method_index);

        if (verbose) {
            System.out.format("calling method %d \n", method_index);
        }

        OpcodeRunner.executeMethod(method, stack, c, verbose);

        return 3;

    }

    static final String clzNamePrint = "java/io/PrintStream";
    static final String clzNameStrBuilder = "java/lang/StringBuilder";
    static String stringBuilderBuffer = "";

    public static int invokevirtual(StackFrame stack, ClassFile c, short nextByte1, short nextByte2, boolean verbose) {

        int object_ref = nextByte1 << 8 | nextByte2;

        if (verbose) {
            System.out.format("calling object_ref %d,", object_ref);
        }

        ConstantReference methodRef = (ConstantReference) c.constant_pool.get(object_ref);

        if (methodRef == null) {
            return -1;
        }
        ConstantClassInfo classInfo = (ConstantClassInfo) c.constant_pool.get(methodRef.classIndex);
        ConstantNameAndType nat = (ConstantNameAndType) c.constant_pool.get(methodRef.nameAndTypeIndex);

        if (classInfo == null || nat == null) {
            return -1;
        }
        String className = ((ConstantPrimitive<String>) c.constant_pool.get(classInfo.name_index)).getValue();
        if (verbose) {
            System.out.format("calling method on class %s, ", className);
        }

        //TODO remove Hacky workaround!
        if (className.equals(clzNamePrint)) {

            StackEntry entry = stack.popEntry();
            int index = entry.getValue();

            if (verbose) {
                System.out.format("calling Println with index = %d\n", index);
            }

            if (entry.getType().equals(EntryType.REF)) {
                ConstantPoolInfo info = c.constant_pool.get(index);
                if (info instanceof ConstantString) {
                    ConstantString str = (ConstantString) info;

                    String string = ((ConstantPrimitive<String>) c.constant_pool.get(str.string_index)).getValue();
                    System.out.println(string);
                } else {
                }

            } else if (entry.getType().equals(EntryType.INT)) {
                System.out.println(index);
            }

        } else if (className.equals(clzNameStrBuilder)) {
            StackEntry entry = stack.popEntry();
            int index = entry.getValue();

            if (verbose) {
                System.out.format("call StringBuilder with index = %d\n", index);
            }

            if (entry.getType().equals(EntryType.REF)) {
                ConstantPoolInfo info = c.constant_pool.get(index);
                if (info instanceof ConstantString) {
                    ConstantString str = (ConstantString) info;
                    String string = ((ConstantPrimitive<String>) c.constant_pool.get(str.string_index)).getValue();
                    System.out.print(string);
                } else {
                    System.out.println();
                }

            } else if (className.equals(clzNameStrBuilder)) {
                System.out.print(index);
            }

        }

        return 3;
    }

}

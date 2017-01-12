package mimojajvm;

import mimojajvm.ClassFormat.ClassFile;
import java.io.IOException;
import mimojajvm.vm.OpcodeRunner;
import mimojajvm.vm.stack.StackFrame;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassFile c = new ClassFile("build/classes/testPackage/Foo.class");
        OpcodeRunner.executeMethod(c.findMethod("<init>"), new StackFrame(500), c, false);
    }

}

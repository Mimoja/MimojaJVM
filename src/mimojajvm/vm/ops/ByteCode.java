package mimojajvm.vm.ops;

public interface ByteCode {

    public static final int aload_0 = 0x2A;
    public static final int bipush = 0x10;
    public static final int dup = 0x59;
    public static final int getstatic = 0xB2;
    public static final int iadd = 0x60;
    public static final int iconst_0 = 0x03;
    public static final int iconst_1 = 0x04;
    public static final int iconst_2 = 0x05;
    public static final int iconst_3 = 0x06;
    public static final int iconst_4 = 0x07;
    public static final int iconst_5 = 0x08;
    public static final int idiv = 0x6C;
    public static final int imul = 0x68;
    public static final int invokespecial = 0xB7;
    public static final int invokevirtual = 0xB6;
    public static final int iload = 0x15;
    public static final int iload_1 = 0x1B;
    public static final int iload_2 = 0x1C;
    public static final int iload_3 = 0x1D;
    public static final int istore = 0x36;
    public static final int istore_1 = 0x3C;
    public static final int istore_2 = 0x3D;
    public static final int istore_3 = 0x3E;
    public static final int isub = 0x64;
    public static final int ldc = 0x12;
    public static final int op_new = 0xBB;
    public static final int return_op = 0xB1;

    public int execute();

}

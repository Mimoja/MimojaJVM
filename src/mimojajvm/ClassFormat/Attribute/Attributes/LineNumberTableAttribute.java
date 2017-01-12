package mimojajvm.ClassFormat.Attribute.Attributes;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;
import mimojajvm.ClassFormat.Attribute.AttributeInfo;
import mimojajvm.ClassFormat.Attribute.AttributeTag;
import mimojajvm.ClassFormat.Constants.ConstantPool;

public class LineNumberTableAttribute extends AttributeInfo {

    public int line_number_table_length;
    public LineNumber[] line_number_table;

    public LineNumberTableAttribute(AttributeTag type, DataInputStream dis, ConstantPool cp, int nameIndex) throws IOException {
        super(type, dis, cp, nameIndex);
    }

    public class LineNumber {

        public int startPc;
        public int lineNumber;

        public LineNumber(DataInputStream dis) throws IOException {
            this.startPc = dis.readUnsignedShort();
            this.lineNumber = dis.readUnsignedShort();
        }

        public String toString(ConstantPool cp) {
            StringBuilder builder = new StringBuilder();
            Formatter print = new Formatter(builder, Locale.getDefault());
            print.format("!! startPc: 0x%02X ", startPc);
            print.format("lineNumber: %d ", lineNumber);
            return builder.toString();
        }
    }

    @Override
    protected void parse(DataInputStream dis, ConstantPool cp, long length) throws IOException {
        line_number_table_length = dis.readUnsignedShort();
        line_number_table = new LineNumber[line_number_table_length];
        for (int i = 0; i < line_number_table_length; i++) {
            line_number_table[i] = new LineNumber(dis);
        }
    }

    @Override
    public String toString(ConstantPool cp) {

        StringBuilder builder = new StringBuilder();
        Formatter print = new Formatter(builder, Locale.getDefault());

        print.format("local_variable_table_length: %d ", line_number_table_length);
        for (int i = 0; i < line_number_table_length; i++) {
            print.format("%s ", line_number_table[i].toString(cp));
        }
        return super.toString(cp) + builder.toString();
    }

}

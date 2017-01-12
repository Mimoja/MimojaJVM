package mimojajvm.ClassFormat.Attribute.Attributes;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.Locale;
import mimojajvm.ClassFormat.Attribute.AttributeInfo;
import mimojajvm.ClassFormat.Attribute.AttributePool;
import mimojajvm.ClassFormat.Attribute.AttributeTag;
import mimojajvm.ClassFormat.Constants.ConstantPool;

public class LocalVariableTableAttribute extends AttributeInfo {

    public int local_variable_table_length;
    public LocalVariableTable[] local_variable_table;

    public LocalVariableTableAttribute(AttributeTag type, DataInputStream dis, ConstantPool cp, int nameIndex) throws IOException {
        super(type, dis, cp, nameIndex);
    }


    public class LocalVariableTable {

        public int startPc;
        public int length;
        public int descriptor_index;
        public int name_index;
        public int index;

        public LocalVariableTable(DataInputStream dis) throws IOException {
            this.startPc = dis.readUnsignedShort();
            this.length = dis.readUnsignedShort();
            this.name_index = dis.readUnsignedShort();
            this.descriptor_index = dis.readUnsignedShort();
            this.index = dis.readUnsignedShort();
        }

        public String toString(ConstantPool cp) {
            StringBuilder builder = new StringBuilder();
            Formatter print = new Formatter(builder, Locale.getDefault());
            print.format("!! startPc: 0x%02X ", startPc);
            print.format("length: %d ", length);

            print.format("name_index: 0x%02X (%s) ", name_index, cp.get(name_index).toString());
            print.format("descriptor_index: 0x%02X (%s) ", descriptor_index, cp.get(descriptor_index).toString());
            print.format("index: %d ", index);

            return builder.toString();
        }
    }

    @Override
    protected void parse(DataInputStream dis, ConstantPool cp, long length) throws IOException {
        local_variable_table_length = dis.readUnsignedShort();
        local_variable_table = new LocalVariableTable[local_variable_table_length];
        for (int i = 0; i < local_variable_table_length; i++) {
            local_variable_table[i] = new LocalVariableTable(dis);
        }
    }

    @Override
    public String toString(ConstantPool cp) {

        StringBuilder builder = new StringBuilder();
        Formatter print = new Formatter(builder, Locale.getDefault());

        print.format("local_variable_table_length: %d ", local_variable_table_length);
        for (int i = 0; i < local_variable_table_length; i++) {
            print.format("%s ", local_variable_table[i].toString(cp));
        }
        return super.toString(cp) + builder.toString();
    }
}

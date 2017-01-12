package mimojajvm.ClassFormat.Constants;

import java.io.IOException;

public enum ConstantTag {

    CONSTANT_UTF8,
    CONSTANT_INT,
    CONSTANT_FLOAT,
    CONSTANT_LONG,
    CONSTANT_DOUBLE,
    CONSTANT_CLASS_INFO,
    CONSTANT_STRING,
    CONSTANT_FIELD_REF,
    CONSTANT_METHOD_REF,
    CONSTANT_INTERFACE_METHOD_REF,
    CONSTANT_NAME_AND_TYPE,
    UNKNOWN;

    public static ConstantTag getTag(byte x) {
        switch (x) {
            case 1:
                return CONSTANT_UTF8;
            case 3:
                return CONSTANT_INT;
            case 4:
                return CONSTANT_FLOAT;
            case 5:
                return CONSTANT_LONG;
            case 6:
                return CONSTANT_DOUBLE;
            case 7:
                return CONSTANT_CLASS_INFO;
            case 8:
                return CONSTANT_STRING;
            case 9:
                return CONSTANT_FIELD_REF;
            case 10:
                return CONSTANT_METHOD_REF;
            case 11:
                return CONSTANT_INTERFACE_METHOD_REF;
            case 12:
                return CONSTANT_NAME_AND_TYPE;
        }
        return UNKNOWN;
    }
}

package mimojajvm.ClassFormat.Attribute;

public enum AttributeTag {
    CONSTANT_VALUE("ConstantValue"),
    CODE("Code"),
    EXCEPTIONS("Exceptions"),
    ENCLOSING_METHOD("EnclosingMethod"),
    INNER_CLASSES("InnerClasses"),
    SYNTHETIC("Synthetic"),
    SOURCE_FILE("SourceFile"),
    LINE_NUMBER_TABLE("LineNumberTable"),
    LOCAL_VARIABLE_TABLE("LocalVariableTable"),
    DEPRECATED("Deprecated"),
    RUNTIME_VISIBLE_ANNOTATIONS("RuntimeVisibleAnnotations"),
    RUNTIME_INVISIBLE_ANNOTATIONS("RuntimeInvisibleAnnotations"),
    SIGNATURE("Signature");

    private final String tag;

    private AttributeTag(String tag) {
        this.tag = tag;
    }

    public static AttributeTag getTag(String str) {
        for (AttributeTag at : values()) {
            if (at.tag.equals(str)) {
                return at;
            }
        }
        return null;
    }
}

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * Created by Administrator on 2017/8/28.
 */
public class CustomCommentGenerator extends DefaultCommentGenerator {
    private boolean suppressChineseAllComments;

    public CustomCommentGenerator() {
    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if(!this.suppressChineseAllComments && introspectedColumn.getRemarks() != null) {
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
            field.addJavaDocLine(" */");
        }
    }
}
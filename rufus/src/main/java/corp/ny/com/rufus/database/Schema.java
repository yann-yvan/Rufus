package corp.ny.com.rufus.database;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import corp.ny.com.rufus.database.annotation.Table;
import corp.ny.com.rufus.database.exceptions.TableException;

/**
 * Created by Yann Yvan CEO of N.Y. Corp. on 04/05/18.
 */
public class Schema {
    private String tableName;
    private List<Column> columns = new ArrayList<>();
    private List<Constraint> constraints = new ArrayList<>();

    private Schema(String tableName) {
        this.tableName = tableName;
    }

    public static Schema instantiate(String tableName) {
        return new Schema(tableName);
    }

    public static Schema instantiate(String tableName, Model model) throws TableException {
        Schema schema = new Schema(tableName);
        if (model == null) {
            throw new TableException("The object is null");
        }

        Class<?> clazz = model.getClass();

        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new TableException("The class "
                    + clazz.getSimpleName()
                    + " is not annotated with Table");
        }

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(corp.ny.com.rufus.database.annotation.Constraint.class))
                schema.constraints.add(
                        Constraint.instantiate(field.getName())
                                .on(field.getAnnotation(corp.ny.com.rufus.database.annotation.Constraint.class).onTable())
                                .references(field.getAnnotation(corp.ny.com.rufus.database.annotation.Constraint.class).references())
                                .onUpdate(field.getAnnotation(corp.ny.com.rufus.database.annotation.Constraint.class).onUpdate())
                                .onDelete(field.getAnnotation(corp.ny.com.rufus.database.annotation.Constraint.class).onDelete())
                );

            if (field.isAnnotationPresent(corp.ny.com.rufus.database.annotation.Column.class)) {
                if (field.getType() == String.class && field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).check().length > 0)
                    schema.columns.add(new Column(
                            String.format("`%s` VARCHAR", field.getName()),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).nullable(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).unique(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).primary(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).increment(),
                            false,
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).varCharSize(),
                            getDefaultValue(field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).defaultString()),
                            String.format("['%s']",TextUtils.join("','",field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).check()))
                    ));
                else if (field.getType() == String.class)
                    schema.columns.add(new Column(
                            String.format("`%s` TEXT", field.getName()),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).nullable(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).unique(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).primary(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).increment(),
                            false,
                            0,
                            getDefaultValue(field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).defaultString()),
                            null

                    ));
                else if (field.getType() == boolean.class)
                    schema.columns.add(new Column(
                            String.format("`%s` INTEGER", field.getName()),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).nullable(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).unique(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).primary(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).increment(),
                            false,
                            1,
                           getDefaultValue( String.valueOf(field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).defaultInt())),
                            null

                    ));
                else if (field.getType() == double.class)
                    schema.columns.add(new Column(
                            String.format("`%s` DOUBLE", field.getName()),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).nullable(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).unique(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).primary(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).increment(),
                            false,
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).intSize(),
                            getDefaultValue(String.valueOf(field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).defaultInt())),
                            null

                    ));
                else if (field.getType() == float.class)
                    schema.columns.add(new Column(
                            String.format("`%s` FLOAT", field.getName()),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).nullable(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).unique(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).primary(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).increment(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).signed(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).intSize(),
                            getDefaultValue(String.valueOf(field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).defaultInt())),
                            null

                    ));
                else if (field.getType() == int.class || field.getType() == long.class || field.getType() == byte.class || field.getType() == short.class)
                    schema.columns.add(new Column(
                            String.format("`%s` INTEGER", field.getName()),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).nullable(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).unique(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).primary(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).increment(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).signed(),
                            field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).intSize(),
                            getDefaultValue(field.getAnnotation(corp.ny.com.rufus.database.annotation.Column.class).defaultInt()),
                            null

                    ));
            }
        }
        return schema;
    }

    private static String getDefaultValue(String value){
        return value.length()==0?null:value;
    }

    private static String getDefaultValue(int value) {
        return value == 0 ? null : String.valueOf(value);
    }

    /**
     * Create a new auto-incrementing integer (4-byte) column on the table
     *
     * @param column
     */
    public void increments(String column) {
        columns.add(Column.instantiate(String.format("`%s` INTEGER", column)).primary().incremental());
    }

    /**
     * Specify the primary key(s) for the table.
     *
     * @param column
     */
    public void primary(String... column) {
        for (int i = 0; i < column.length; i++) {
            column[i] = String.format("`%s`", column[i]);
        }
        constraints.add(Constraint.primary(String.format("PRIMARY KEY (%s)", TextUtils.join(",", column))));
    }

    /**
     * Create a new text column on the table.
     *
     * @param column
     * @return
     */
    public Column text(String column) {
        columns.add(Column.instantiate(String.format("`%s` TEXT", column)));
        return columns.get(columns.size() - 1);
    }

    /**
     * Create a new string column on the table.
     *
     * @param column
     * @return
     */
    public Column string(String column) {
        columns.add(Column.instantiate(String.format("`%s` VARCHAR", column), 45));
        return columns.get(columns.size() - 1);
    }

    /**
     * Create a new string column on the table.
     *
     * @param column
     * @param size
     * @return
     */
    public Column string(String column, int size) {
        columns.add(Column.instantiate(String.format("`%s` VARCHAR", column), size));
        return columns.get(columns.size() - 1);
    }


    /**
     * Create a new integer (4-byte) column on the table.
     *
     * @param column
     * @return
     */
    public Column integer(String column) {
        columns.add(Column.instantiate(String.format("`%s` INTEGER", column), 10));
        return columns.get(columns.size() - 1);
    }

    /**
     * Create a new integer (4-byte) column on the table.
     *
     * @param column
     * @param size
     * @return
     */
    public Column integer(String column, int size) {
        columns.add(Column.instantiate(String.format("`%s` INTEGER", column), size));
        return columns.get(columns.size() - 1);
    }

    /**
     * Create a new double column on the table
     *
     * @param column
     * @return
     */
    public Column doubles(String column) {
        columns.add(Column.instantiate(String.format("`%s` DOUBLE", column)));
        return columns.get(columns.size() - 1);
    }

    /**
     * Create a new float column on the table.
     *
     * @param column
     * @return
     */
    public Column floats(String column) {
        columns.add(Column.instantiate(String.format("`%s` FLOAT", column)));
        return columns.get(columns.size() - 1);
    }

    public Constraint foreign(String column) {
        constraints.add(Constraint.instantiate(column));
        return constraints.get(constraints.size() - 1);
    }

    @Override
    public String toString() {
        String script =
                String.format("CREATE TABLE IF NOT EXISTS %s ( %s%s );"
                , tableName, TextUtils.join(",", columns.toArray())
                , (constraints.isEmpty() ? ""
                        : String.format(",\n%s"
                        , TextUtils.join(",", constraints.toArray()))));
            System.out.println(script);
        return script;
    }
}

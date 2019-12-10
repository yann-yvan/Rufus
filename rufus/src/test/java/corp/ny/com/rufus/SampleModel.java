package corp.ny.com.rufus;

import corp.ny.com.rufus.database.Model;
import corp.ny.com.rufus.database.annotation.Column;
import corp.ny.com.rufus.database.annotation.Table;

@Table
public class SampleModel extends Model<SampleModel> {
    @Column(primary = true, increment = true)
    private int anInt;
    @Column
    private double aDouble;
    @Column
    private String string;
    @Column
    private String message;
    @Column
    private float aFloat;
    @Column
    private boolean aBoolean;
    @Column
    private long aLong;
    @Column
    private short aShort;
    @Column
    private byte aByte;
    @Column
    @corp.ny.com.rufus.database.annotation.Constraint(references = "id", onTable = "user")
    private int receiverId;
    @Column(defaultInt = 10)
    @corp.ny.com.rufus.database.annotation.Constraint(references = "id", onTable = "user")
    private int senderId;

    @Override
    public String getIdValue() {
        return null;
    }
}

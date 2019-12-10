package corp.ny.com.rufus;

import corp.ny.com.rufus.database.annotation.Column;
import corp.ny.com.rufus.database.annotation.Table;

@Table
public class User {
    @Column(increment = true, primary = true)
    private int id;
}

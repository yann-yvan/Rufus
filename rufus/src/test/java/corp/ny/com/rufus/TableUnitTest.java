package corp.ny.com.rufus;

import org.junit.Test;

import corp.ny.com.rufus.database.exceptions.TableException;

public class TableUnitTest {

    @Test
    public void sqlite() {
        try {
            System.out.println(new SampleModel().genTable());
        } catch (TableException e) {
            e.printStackTrace();
        }
    }
}

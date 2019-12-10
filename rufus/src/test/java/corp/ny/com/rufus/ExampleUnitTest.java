package corp.ny.com.rufus;

import org.junit.Test;

import java.io.Console;

import corp.ny.com.rufus.database.exceptions.TableException;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        try {
            System.out.println(new SampleModel().genTable());
        } catch (TableException e) {
            e.printStackTrace();
        }
    }
}
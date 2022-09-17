import java.sql.SQLException;
import java.text.ParseException;

public class PoisedMain {
    public static void main(String[] args) throws ParseException, SQLException {
        ProjectLogic newLogic = new ProjectLogic();
        newLogic.getLogic();
    }
}

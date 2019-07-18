package gl_Test;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {
    public String Inputer(String a,String b,String c,String d){
        return a+c+b+d;
    }

    @Test
    public void registerInput() {
        String checker = Inputer("1","2","3","4");
        assert checker.equals("1324");
    }
}
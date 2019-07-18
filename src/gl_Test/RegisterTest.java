package gl_Test;

import org.junit.Test;

public class RegisterTest {
    public String[] registerInputTest(){
        String a,b,c,d;
        a = "1";
        b = "2";
        c = "3";
        d = "4";
        return new String[]{a,b,c,d};
    }
    @Test
    public void registerInput() {
        String[] account = registerInputTest();
        assert account[0].equals("1");
        assert account[1].equals("2");
        assert account[2].equals("3");
        assert account[3].equals("4");
    }
}
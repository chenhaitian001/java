package test1;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Test {

    public static void main(String[] args) throws IOException {


        String ipport=MessageFormat.format("{0}_{1}","192.168.1.1",123123+"");

        System.out.println(ipport);

    }
}

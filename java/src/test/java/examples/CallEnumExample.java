package examples;

import examples.EnumExample;
import org.testng.annotations.Test;


public class CallEnumExample {

    @Test
    public void printTheBootCampDays(){
        //Can only use the predefined enums
        System.out.println(EnumExample.checkBootCampDaysInfo(EnumExample.BootCampDays.DAYONE));

        System.out.println(EnumExample.checkBootCampDaysInfo(EnumExample.BootCampDays.DAYFIVE));
    }
}

package examples;

public class EnumExample {

    public enum BootCampDays{
        DAYONE,
        DAYTWO,
        DAYTHREE,
        DAYFOUR,
        DAYFIVE;
    }

    public static String checkBootCampDaysInfo(BootCampDays bootCampDays){
        switch(bootCampDays){
            case DAYONE:
                return "Zin an!";
            case DAYTWO:
                return "Nog wel leuk, maar terig rijden was wel pittig";
            case DAYTHREE:
                return "Bijna weekend! Ohnee, yes dag drie!";
            case DAYFOUR:
                return "waar gingen het vorige week ook alweer over?";
            case DAYFIVE:
                return "man man man";
            default:
                return "cursis is maar 5 dagen ";
        }
    }
}

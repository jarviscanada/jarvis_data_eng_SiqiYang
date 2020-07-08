package ca.jrvs.apps.practice;

public class RegexImplement implements RegexExc{

    /**
     * return true is the filename is the ipg or jpeg (case insensitive)
     * @param filename
     * @return boolean
     */
    public boolean matchJpeg(String filename) {
        String regex = "(.+)(\\.)([jJ][pP][eE][gG]$)";
        return filename.matches(regex);
    }

    /**
     * return true if the ip is valid, Ip range is from 0.0.0.0 to 999.999.999.999
     * @param filename
     * @return
     */
    public boolean matchI(String filename){
        String regex = "(\\d{1,3}\\.){3}(\\d){1,3}";
        return filename.matches(regex);
    }

    /**
     * return true if the line is empty line.
     * @param line
     * @return
     */
    public boolean isEmptyLine(String line){
        String regex = "\\s{0,1}";
        return line.matches(regex);
    }

    public static void main (String [] args) {
        RegexImplement a = new RegexImplement();
        System.out.println(a.matchJpeg("adasd.JpEg"));
        System.out.println(a.matchI("3.3.0.0.0.0"));
        System.out.println(a.isEmptyLine(" 333"));
    }


}

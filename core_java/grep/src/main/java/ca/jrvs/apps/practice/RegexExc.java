package ca.jrvs.apps.practice;

public interface RegexExc {
    /**
     * return true is the filename is the ipg or jpeg (case insensitive)
     * @param filename
     * @return boolean
     */
    public boolean matchJpeg(String filename);

    /**
     * return true if the ip is valid, Ip range is from 0.0.0.0 to 999.999.999.999
     * @param filename
     * @return
     */
    public boolean matchI(String filename);

    /**
     * return true if the line is empty line.
     * @param line
     * @return
     */
    public boolean isEmptyLine(String line);
}

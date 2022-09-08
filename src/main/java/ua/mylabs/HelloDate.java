package ua.mylabs;

import java.util.Date;

/** The first Thinking in Java example program.
 * Displays a string and today’s date.
 * @author Denys Shkolenko
 */
public class HelloDate {

    /** Entry point to class & application.
     * @param args array of string arguments
     */
    public static void main(String[] args) {
        System.out.println("Hey, today is ");
        System.out.println(new Date());
    }
}

package project1;

import java.util.*;
import java.io.*;

/**********************************************************************
 * Stores and analyzes calendar dates.
 * Valid dates are from 1/1/1753 and onward
 *
 * @author Andrew Kruse
 * @version 1/30/2019
 *********************************************************************/
public class SimpleDate {

    /** month represents the current month */
    private int month;

    /** day represents the current day */
    private int day;

     /** year represents the current year */
    private int year;

    /** counts number of SimpleDate objects created */
    private static int simpleDateCount = 0;


    private static final int[] DAYS_IN_MONTH =
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] MONTHS =
            {"", "January", "February", "March", "April", "May",
                    "June", "July", "August", "September",
                    "October", "November", "December"};
    private static final int NUM_MONTHS = 12;
    private static final int DAYS_YEAR = 365;
    private static final int DAYS_LEAP_YEAR = 366;
    public static final int MIN_YEAR = 1753;
    private static int counter = 0;


    /******************************************************************
     * A default constructor that sets all all SimpleDate member fields
     * to zero
     ****************************************************************/
    public SimpleDate() {
        this.month = 0;
        this.day = 0;
        this.year = 0;
        this.simpleDateCount++;
    }


    /******************************************************************
     * A constructor that accepts a string that represents
     * a date and breaks into integer dates based on / deliminator
     * Checks to make sure date is valid
     *
     * @param date A string that represents a date
     * @throws IllegalArgumentException if parameter doesn't
     * represent a valid date.
     ****************************************************************/
    public SimpleDate(String date) {
        if (date != null && !date.equals("")) {
            String[] parts = date.split("/");
            if (parts.length == 3) {
                month = Integer.parseInt(parts[0]);
                day = Integer.parseInt(parts[1]);
                year = Integer.parseInt(parts[2]);
                if (checkValidDate(this))
                    this.simpleDateCount++;
                else
                    throw new IllegalArgumentException();
            } else
                throw new IllegalArgumentException();
        } else
            throw new IllegalArgumentException();
    }


    /******************************************************************
     * Constructor taking month, day, and year as integers. Checks to
     * see if date is valid
     *
     * @param month the month
     * @param day   the day
     * @param year  the year
     * @throws IllegalArgumentException if input
     * doesn't represent a valid date.
     ******************************************************************/
    public SimpleDate(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
        if (checkValidDate(this))
            this.simpleDateCount++;
        else
            throw new IllegalArgumentException();
    }


    /******************************************************************
     * Constructor coping member fields of SimpleDate parameter
     * Checks to make sure new date is valid.
     *
     * @param other SimpleDate object being copied
     * @throws IllegalArgumentException if input is not a valid date
     *****************************************************************/
    public SimpleDate(SimpleDate other) {
        if (checkValidDate(other)) {
            this.day = other.day;
            this.year = other.year;
            this.month = other.month;
            this.simpleDateCount++;
        } else
            throw new IllegalArgumentException();
    }


    /******************************************************************
     * Checks to see if parameter is a valid date, later than 1,1,1753
     *
     * @param date SimpleDate object
     * @return boolean value depending on if parameter is a valid date
     * @throws IllegalArgumentException if para is not valid date
     *****************************************************************/
    public boolean checkValidDate(SimpleDate date) {
        try {

            //check if months is not between 1 and 12
            if (date.getMonth() > NUM_MONTHS || date.getMonth() < 1)
                throw new IllegalArgumentException();

            //check to see if day is valid for the month
            //this will also check year validity
            else if (date.getDay() < 1 || date.getDay() >
                    SimpleDate.daysInMonth(date.getMonth(),
                            date.getYear()))
                throw new IllegalArgumentException();
            //if no exceptions were thrown, date is valid
            else
                return true;
        }

        //if exception was thrown, dateisInvalid
        catch (IllegalArgumentException e) {
            return false;
        }
    }


    /******************************************************************
     * Return value of month field
     *
     * @return month integer value of current month
     *****************************************************************/
    public int getMonth() {
        return month;
    }


    /******************************************************************
     * Return value of day field
     *
     * @return day integer value of current day
     *****************************************************************/
    public int getDay() {
        return day;
    }


    /******************************************************************
     * Return value of year field
     *
     * @return year integer value of current year
     *****************************************************************/
    public int getYear() {
        return year;
    }


    /******************************************************************
     * Return number of simple date objects created
     *
     * @return SimpleDateCount integer amount of simple date objects
     *****************************************************************/
    public static int getSimpleDateCount() {
        return simpleDateCount;
    }


    /******************************************************************
     * Returns the number of days in month
     * Leap year is taken into consideration
     *
     * @return DAYS_IN_MONTH[i] integer value of days in current month
     * @throws IllegalArgumentException if para not valid data members
     *****************************************************************/
    public static int daysInMonth(int month, int year) {

        //check for valid parameter input
        if (month > NUM_MONTHS || month < 1 || year < 1753) {
            throw new IllegalArgumentException();
        }

        //check if month is February and leap year, return 29 if true
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }

        //return days in month from array if not leap year and Feb
        return DAYS_IN_MONTH[month];
    }


    /******************************************************************
     * Determines if the parameter was a leap year, if the year is a
     * valid date's year
     *
     * @return boolean value dependent upon status of leap year
     * @throws IllegalArgumentException if year is invalid
     *****************************************************************/
    public static boolean isLeapYear(int year) {

        //ensure valid date year
        if (year < 1753)
            throw new IllegalArgumentException();

        //return leap year if parameter qualifies as leap year
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }


    /******************************************************************
     * Calls static method to see if is leap year
     *
     * @return boolean value dependent on if this date is leap year
     *****************************************************************/
    public boolean isLeapYear() {
        return isLeapYear(this.year);
    }


    /******************************************************************
     * to string object that returns full word Month Day, Year
     *
     * @return Formatted String of this objects date
     *****************************************************************/
    public String toString() {
        return String.format("%s %02d, %4d",
                MONTHS[this.month], this.day, this.year);
    }


    /******************************************************************
     * Override equals method, checks if date member fields match
     * parameters member fields, checks to make sure other is of
     * SimpleDate object type
     *
     * @param other
     * @return boolean value dependent upon if member fields match
     * @throws IllegalArgumentException if other is not a SimpleDate
     *****************************************************************/
    public boolean equals(Object other) {
        if (other instanceof SimpleDate) {
            SimpleDate temp = (SimpleDate) other;
            if (this.day == temp.day && this.month == temp.month
                    && this.year == temp.year)
                return true;
            else
                return false;
        }
        throw new IllegalArgumentException();
    }


    /******************************************************************
     * Static equals override method, calls parameter date1 member
     * function equals, passing date2.
     *
     * @param date1 SimpleDate used to compare member fields
     * @param date2 SimpleDate member field being compared too
     * @return boolean value dependent up if member fields are equal
     *****************************************************************/
    public static boolean equals(SimpleDate date1, SimpleDate date2) {
        return date1.equals(date2);
    }


    /******************************************************************
     * Compares date object to input parameter to give reference of
     * which date occured chronologically first. Returns 1 if this
     * object occured second, returns -1 if this object occured first,
     * returns zero if dates are chronologically the same
     *
     * @param other SimpleDate to determine chronological precedence
     * @return integer value depending up which date came first
     * @throws IllegalArgumentException if other is not a valid date
     *****************************************************************/
    public int compareTo(SimpleDate other) {
        if (checkValidDate(other)) {

            //check determination based up year
            if (this.year > other.year)
                return 1;
            else if (this.year < other.year)
                return -1;

            //if same year, check for determination based upon month
            else if (this.year == other.year)
                if (this.month > other.month)
                    return 1;
                else if (this.month < other.month)
                    return -1;

                //if same month check for determination based upon day
                else if (this.month == other.month)
                    if (this.day > other.day)
                        return 1;
                    else if (this.day < other.day)
                        return -1;

                    //if same day, return 0
                    else if (this.day == other.day)
                        return 0;
        }
        throw new IllegalArgumentException();
    }


    /******************************************************************
     * Returns number of days in this current year
     *
     * @return integer number of day in year or Leap Year
     *****************************************************************/
    public int daysInYear() {
        if (this.isLeapYear())
            return DAYS_LEAP_YEAR;
        else
            return DAYS_YEAR;
    }


    /******************************************************************
     *A method that returns the number of days at the beginning of the
     * “this” year.  For example: “2/10/2013” returns 41.
     *
     * @return daySince integer number of days since beginning of year
     *****************************************************************/
    public int ordinalDate() {

        int daySince = 0;
        int i;

        //check if leap year
        if (this.isLeapYear()) {

            //add all previous month's days to daySince
            for (i = 1; i < this.month; i++) {
                daySince += DAYS_IN_MONTH[i];
            }

            //add current month's days to daySince
            daySince += this.day;

            //if current month is past february add one for leap year
            //make sure the current day is not the 29th of February
            if (i > 2)
                daySince += 1;
        }

        //if not leap year
        else {

            //add all previous months to daySince count
            for (i = 1; i < this.month; i++) {
                daySince += DAYS_IN_MONTH[i];
            }

            //add current month's day count
            daySince += this.day;
        }
        return daySince;
    }


    /******************************************************************
     *returns a new SimpleDate object representing the date input para
     * days from now
     *
     * @param n number of days from this date
     * @return newDate SimpleDate n days later than this
     * @throws IllegalArgumentException if this is not valid date
     *****************************************************************/
    public SimpleDate daysFromNow(int n)
            throws IllegalArgumentException {
        SimpleDate newDate;

        //"this" is not a SimpleDate created with default constructor
        if (checkValidDate(this))
            newDate = new SimpleDate(this);
        else
            throw new IllegalArgumentException();

        //increment newDate until the count is satisfied
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                newDate.increment();
            }
        }

        //decrement newDate until the count is satisfied
        else if (n < 0) {
            for (int i = 0; i > n; i--) {
                newDate.decrement();
            }
        }
        return newDate;
    }


    /******************************************************************
     * Increments this day by one day. At end of month or year,
     * rolls over correct category
     *****************************************************************/
    public void increment() {

        //if end of year, set date to January First, year ++
        if (this.endOfYear()) {
            this.month = 1;
            this.day = 1;
            this.year++;
        }

        //if end of month, increment month, reset day to 1st
        else if (this.endOfMonth()) {
            this.month++;
            this.day = 1;
        }

        //if day in middle of month, add one to day
        else {
            this.day++;
        }
    }


    /******************************************************************
     * Decrements this day by one day. At beginning of month or year,
     * rolls over corresponding year
     *
     * @throws IllegalArgumentException if date becomes invalid
     *****************************************************************/
    public void decrement() throws IllegalArgumentException {

        //if start over year, set month to December 31st, year--
        if (this.startOfYear()) {
            this.month = 12;
            this.day = SimpleDate.DAYS_IN_MONTH[12];
            this.year--;
        }

        //if start of month
        else if (this.startOfMonth()) {

            //if start of month leap year
            if (this.month == 3 && SimpleDate.isLeapYear(this.year)) {

                //set date to 29th instead of 28th
                this.month--;
                this.day = SimpleDate.DAYS_IN_MONTH[this.month] + 1;
            }

            //decrease month, set to last day in month
            else {
                this.month--;
                this.day = SimpleDate.DAYS_IN_MONTH[this.month];
            }
        }

        //if in middle of month, decrease day by 1
        else {
            this.day--;
        }

        //check to make sure date is still valid
        if (!checkValidDate(this)) {
            throw new IllegalArgumentException();
        }
    }


    /******************************************************************
     * Helper method, checks to see if SimpleDate is at start of month
     *
     * @return boolean value depending upon if start of month or not
     *****************************************************************/
    private boolean startOfMonth() {
        if (this.day == 1)
            return true;
        else
            return false;
    }


    /******************************************************************
     * Helper method, checks to see if SimpleDate is at start of year
     *
     * @return boolean value depending upon if start of year or not
     *****************************************************************/
    private boolean startOfYear() {
        if (this.month == 1 && this.day == 1)
            return true;
        else
            return false;
    }


    /******************************************************************
     * Helper method, checks to see if SimpleDate is at end of month
     * Takes into account leap years
     *
     * @return boolean value depending upon if end of month or not
     *****************************************************************/
    private boolean endOfMonth() {

        //if this year is a leap year, February is a special case
        if (this.isLeapYear()) {

            //if February, check for the 29th instead of 28th
            if (this.month == 2) {
                if (this.day == 29)
                    return true;
                else
                    return false;
            }

            //if still leap year  and ! Feb, check for end of month
            else if (this.day == DAYS_IN_MONTH[this.month])
                return true;
            else
                return false;
        }

        //for all other years, check if last day of month
        else {
            if (this.day == DAYS_IN_MONTH[this.month])
                return true;
            else
                return false;
        }
    }


    /******************************************************************
     * Helper method, checks to see if SimpleDate is at end of year
     *
     * @return boolean value depending upon if end of year or not
     *****************************************************************/
    private boolean endOfYear() {
        if (this.month == 12 && this.endOfMonth())
            return true;
        else
            return false;
    }


    /******************************************************************
     * Calculates the difference in number of days between the dates
     * if other precedes this, returns a positive integer, if this
     * precedes other, returns a negative integer, same day will
     * return zero
     *
     * @param other  SimpleDate object used to calculate difference
     * @return days number of days difference between two SimpleDates
     * @throws IllegalArgumentException if either input parameter is
     * not a valid date
     *****************************************************************/
    public int daysSince(SimpleDate other)
            throws IllegalArgumentException {
        int days = 0;
        SimpleDate temp;

        //check for validity of dates
        if (!checkValidDate(this) || !checkValidDate(other)) {
            throw new IllegalArgumentException();
        }

        //if dates are valid, copy this date for comparision
        temp = new SimpleDate(this);

        //see which date comes first chronologically
        int direction = temp.compareTo(other);

        //if this is greater than other, calculate number of days since
        if (direction > 0) {
            while (temp.compareTo(other) == 1) {
                temp.decrement();
                days++;
            }
        }

        //if other is greater than this, calculate days since negative
        else if (direction < 0) {
            while (temp.compareTo(other) == -1) {
                temp.increment();
                days--;
            }
        }

        //same day will return zero
        return days;
    }


    /******************************************************************
     * Save this date to a parameter file
     *
     * @param fileName string name of file
     * @throws IllegalArgumentException if file failed to write or
     * fileName was invalid
     *****************************************************************/
    public void save(String fileName) {

        //check for valid file name
        if (fileName.equals("") || fileName.equals(null)) {
            throw new IllegalArgumentException();
        }

        PrintWriter out = null;

        //if valid file Name, open/create file
        try {
            out = new PrintWriter(new BufferedWriter(
                    new FileWriter(fileName)));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        //print out SimpleDate object to file in X/X/XXXX format
        out.println(
                "" + this.month + "/" + this.day + "/" + this.year);

        //close file
        out.close();
    }


    /******************************************************************
     *Loads SimpleDate object from a txt file specified form the input
     * parameter
     *
     * @param fileName String containing the name of the file
     * @throws IllegalArgumentException if file name is invalid or
     * if date being loaded is invalid, or fileRead failed
     *****************************************************************/
    public void load(String fileName) {
        StringBuffer text;

        //if file exists name is valid and exists, read in file line
        try {
            if (fileName.equals("") || fileName.equals(null)) {
                throw new IllegalArgumentException();
            }
            Scanner fileReader = new Scanner(new File(fileName));
            text = new StringBuffer(fileReader.next());

            //with text file information create a new date object
            //constructor will error test the date
            SimpleDate s1 = new SimpleDate(text.toString());

            //after confirming date, save date to "this" object
            this.month = s1.month;
            this.day = s1.day;
            this.year = s1.year;
        } catch (Exception e) {

            throw new IllegalArgumentException();
        }
    }


} // end SimpleDate
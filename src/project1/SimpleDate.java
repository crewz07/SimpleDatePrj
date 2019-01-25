package project1;
import java.util.*;
import java.io.*;
/**
 * Stores and analyzes calendar dates.  THIS IS A HELPER CLASS, PLEASE
 * CREATE A SEPARATE CLASS NAMED: SimpleDate.java
 *
 * @author Andrew Kruse
 * @version 1/30/2019
 */

public class SimpleDate{

	/** month represents the current month */
	private int month;

	/** day represents the current day */
	private int day;

	/** year represents the current year */
	private int year;

    public static int getSimpleDateCount() {
        return simpleDateCount;
    }

    /**counts number of SimpleDate objects created */
	private static int simpleDateCount = 0;

    // Days in each month assuming months are numbered beginning with 1
    private static final int[] DAYS_IN_MONTH =
			{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    //Names of month, assuming months are numbered beginning with a 1
    private static final String[] MONTHS =
            {"","January","February","March","April","May","June",
            "July","August","September","October","November",
            "December"};

    private static final int NUM_MONTHS = 12;
    private static final int DAYS_YEAR = 365;
    public static final int MIN_YEAR = 1753;
    private static int counter = 0;


	/******************************************************************
	 * A default constructor that sets all all SimpleDate member fields
	 * to zero
	 ****************************************************************/
	public SimpleDate(){
		this.month = 0;
		this.day = 0;
		this.year = 0;
		this.simpleDateCount++;
	}
	/******************************************************************
	 * A constructor that accepts a string that represents
	 * a date
     *
	 * @param date A string that represents a date
	 * @throws IllegalArgumentException if parameter doesn't
	 * represent a valid date.
	 ****************************************************************/
	public SimpleDate (String date) {
	    if(date != null && !date.equals("")) {
            String[] parts = date.split("/");
            if(parts.length == 3) {
                month = Integer.parseInt(parts[0]);
                day = Integer.parseInt(parts[1]);
                year = Integer.parseInt(parts[2]);
                if(checkValidDate(this))
                    this.simpleDateCount++;
                else
                    throw new IllegalArgumentException();
            }
            else
                throw new IllegalArgumentException();
        }
        else
            throw new IllegalArgumentException();
		}

	/******************************************************************
	 * Constructor taking month, day, and year as integers.
	 *
	 * @param month the month
	 * @param day   the day
	 * @param year  the year
	 * @throws IllegalArgumentException if input
	 * doesn't represent a valid date.
	 ******************************************************************/
	public SimpleDate (int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
		if(checkValidDate(this))
		    this.simpleDateCount++;
		else
		    throw new IllegalArgumentException();
	}

	public SimpleDate (SimpleDate other) {
		if(checkValidDate(other)){
            this.day = other.day;
            this.year = other.year;
            this.month = other.month;
            this.simpleDateCount++;
        }
        else
            throw new IllegalArgumentException();



	}

	public boolean checkValidDate(SimpleDate date){
	    try {
            if (date.getMonth() > 12 || date.getMonth() < 1)
                throw new IllegalArgumentException();
            else if (date.getDay() < 1 || date.getDay() > SimpleDate.daysInMonth(date.getMonth(), date.getYear()))
                throw new IllegalArgumentException();
            else if (date.getYear() < 1753)
                throw new IllegalArgumentException();
            else
                return true;
        }
	    catch(IllegalArgumentException e){
	        return false;
        }
    }

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getYear() {
		return year;
	}

	public static int daysInMonth(int month, int year) {
	    if(month > 12 || month < 1 || year < 1700)
        {
            throw new IllegalArgumentException();
        }
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        return DAYS_IN_MONTH[month];
	}

	public static boolean isLeapYear(int year) {
	    if(year < 1700)
	        throw new IllegalArgumentException();
		return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
	}

	public boolean isLeapYear() {
		return isLeapYear(this.year);
	}

	public String toString() {
	    return String.format("%s %02d, %4d",
                MONTHS[this.month],this.day,this.year);
	}

	public boolean equals(Object other) {
		if(other instanceof SimpleDate) {
			SimpleDate temp = (SimpleDate) other;
			if (this.day == temp.day && this.month == temp.month
                    && this.year == temp.year)
				return true;
			else
				return false;
		}
		throw new IllegalArgumentException();
	}

	public static boolean equals(SimpleDate s1, SimpleDate s2)
    {
       return s1.equals(s2);
    }

	public int compareTo(SimpleDate other){
        if(checkValidDate(other)) {
            if (this.year > other.year)
                return 1;
            else if (this.year < other.year)
                return -1;
            else if (this.year == other.year)
                if (this.month > other.month)
                    return 1;
                else if (this.month < other.month)
                    return -1;
                else if (this.month == other.month)
                    if (this.day > other.day)
                        return 1;
                    else if (this.day < other.day)
                        return -1;
                    else if (this.day == other.day)
                        return 0;
        }
        throw new IllegalArgumentException();
	}

	public int daysInYear(){
	    if(this.isLeapYear())
	        return 366;
        else
            return 365;
    }

    public int ordinalDate(){

	    int daySince = 0;
	    int i;

	    //check if leap year
        if(this.isLeapYear()) {

            //add all previous month's days to daySince
            for (i = 1; i < this.month; i++) {
                daySince += DAYS_IN_MONTH[i];
            }

            //add current month's days to daySince
            daySince += this.day;

            //if current month is past february add one for leap year
            //make sure the current day is not the 29th of February
            if(i > 2)
                daySince += 1;
        }
        else{

            //add all previous months to daySince count
            for(i = 1; i< this.month;i++){
                daySince += DAYS_IN_MONTH[i];
            }
            //add current month's day count
            daySince += this.day;
        }
        return daySince;
    }

    public SimpleDate daysFromNow(int n)
			throws IllegalArgumentException{
		SimpleDate newDate;

		//"this" is not a SimpleDate created with default constructor
		if(checkValidDate(this))
			newDate = new SimpleDate(this);
		else
			throw new IllegalArgumentException();

		//increment newDate until the count is satisfied
		if(n > 0){
			for(int i = 0; i < n; i++){
				newDate.increment();
			}
		}
		//decrement newDate until the count is satisfied
		else if(n < 0){
			for(int i = 0; i > n; i--){
				newDate.decrement();
			}
		}
		return newDate;
	}

    public void increment(){
	    if(this.endOfYear()){
	        this.month = 1;
	        this.day = 1;
	        this.year++;
        }
        else if(this.endOfMonth()){
            this.month++;
            this.day = 1;
        }
        else{
            this.day++;
        }
    }

    public void decrement() throws IllegalArgumentException{
		if(this.startOfYear()){
			this.month = 12;
			this.day = SimpleDate.DAYS_IN_MONTH[12];
			this.year--;
		}
		else if(this.startOfMonth()){
			if(this.month == 3 && SimpleDate.isLeapYear(this.year)){
				this.month = 2;
				this.day = 29;
			}
			else if(this.month == 3 && !SimpleDate.isLeapYear(this.year)){
				this.month--;
				this.day = 28;
			}
			else{
				this.month--;
				this.day = SimpleDate.DAYS_IN_MONTH[this.month];
			}
		}
		else{
			this.day--;
		}

		//check to make sure date is still valid
		if(!checkValidDate(this)){
			throw new IllegalArgumentException();
		}
	}

    private boolean startOfMonth(){
		if(this.day == 1)
			return true;
		else
			return false;
	}

	private boolean startOfYear(){
		if(this.month ==1 && this.day == 1)
			return true;
		else
			return false;
	}

    private boolean endOfMonth(){

	    //if this year is a leap year, February is a special case
	    if(this.isLeapYear()){

	        //if February, check for the 29th instead of 28th
	        if(this.month == 2){
	            if(this.day == 29)
	                return true;
	            else
	                return false;
            }

            //if still leap year  and ! Feb, check for end of month
            else
                if(this.day == DAYS_IN_MONTH[this.month])
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

    private boolean endOfYear(){
	    if(this.month == 12 && this.endOfMonth())
	        return true;
        else
            return false;
    }

	public void save (String fileName) {
        if(fileName.equals("")|| fileName.equals(null)){
            throw new IllegalArgumentException();
        }
		PrintWriter out = null;
		try{
			out = new PrintWriter(new BufferedWriter(
					new FileWriter(fileName)));
		}
		catch (IOException e){
			throw new IllegalArgumentException();
		}

		out.println(
		        "" + this.month + "/" + this.day + "/" + this.year);
		out.close();
	}

	public void load (String fileName) {
		StringBuffer text;

		try{
		    if(fileName.equals("")|| fileName.equals(null)){
		        throw new IllegalArgumentException();
            }
			Scanner fileReader  = new Scanner(new File (fileName));
			text = new StringBuffer(fileReader.next());

			//with text file information create a new date object
			//constructor will error test the date
			SimpleDate s1 = new SimpleDate(text.toString());

			//after confirming date, save date to "this" object
			this.month = s1.month;
			this.day = s1.day;
			this.year = s1.year;
		}

		catch(Exception e)
		{
			throw new IllegalArgumentException();
		}
	}


} // end SimpleDate
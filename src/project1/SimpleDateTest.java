package project1;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *   This is just a small sample of JUnits, you are to write
 *   many many more to have thorough coverage of your code; beyond 100%
 *   statement coverage
 *
 * @author yourname
 * 
 */

public class SimpleDateTest {

    @Test
    public void testGetters(){
        SimpleDate s1 = new SimpleDate("1/24/2019");
        Assert.assertEquals("Month should be 1",
                1,s1.getMonth());
        Assert.assertEquals("Day should be 24",
                24,s1.getDay());
        Assert.assertEquals("Year should be 2019",
                2019,s1.getYear());
    }

    @Test
    public void testIsLeapYear() {
        SimpleDate d = new SimpleDate("3/1/2013");
        assertFalse(d.isLeapYear());
        d = new SimpleDate("3/1/2000");
        assertTrue(d.isLeapYear());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIsLeapYearBadArgument(){
        SimpleDate.isLeapYear(1699);
    }

    @Test
    public void testCompareTo1() {
        SimpleDate d1 = new SimpleDate("3/1/2013");
        SimpleDate d2 = new SimpleDate("8/21/2000");
        assertTrue(d1.compareTo(d2) > 0);
    }

    @Test
    public void testCompareTo2() {
        SimpleDate d1 = new SimpleDate("3/1/2013");
        SimpleDate d2 = new SimpleDate("8/21/2000");
        assertTrue(d1.compareTo(d2) > 0);
    }

    @Test
    public void testCompareTo3() {
        SimpleDate d1 = new SimpleDate("3/1/2013");
        SimpleDate d2 = new SimpleDate("8/21/2000");
        assertTrue(d1.compareTo(d2) > 0);
    }


    // must use separate test cases for each error
    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectYear() {
        SimpleDate d1 = new SimpleDate("3/1/1700");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringConsturctorBadString(){
        SimpleDate d1 = new SimpleDate("2//1//2023");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringConstructorNullString(){
        SimpleDate d1 = new SimpleDate("");
    }

    // must use separate test cases for each error
    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectMonth() {
        SimpleDate d1 = new SimpleDate("-3/1/1700");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectDayLow(){
        SimpleDate d1 = new SimpleDate(3,-2,2012);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectDayHigh(){
        SimpleDate d1  = new SimpleDate(2,30,2016);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectYearLow(){
        SimpleDate d1 = new SimpleDate(2,2,1700);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDefaultObjectConstructor(){
        SimpleDate s1 = new SimpleDate();
        SimpleDate s2 = new SimpleDate(s1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void equalsInstanceOfObject() {

        SimpleDate d1 = new SimpleDate("3/1/2013");
        String death = "This will cause problems";
        d1.equals(death);
    }

    @Test
    public void equalsDifferentDates() {

        SimpleDate d1 = new SimpleDate("3/1/2013");
        SimpleDate d2 = new SimpleDate("4/1/2013");
        Assert.assertFalse("equalsDifferentDates return false",
                d1.equals(d2));
    }

    @Test
    public void equalsSameDates() {
        SimpleDate d1 = new SimpleDate("4/1/2013");
        SimpleDate d2 = new SimpleDate("4/1/2013");
        Assert.assertTrue("equalsSameDate return true",
                d1.equals(d2));
    }

    @Test
    public void staticEqualsDifferentDates() {

        SimpleDate d1 = new SimpleDate("3/1/2013");
        SimpleDate d2 = new SimpleDate("4/1/2013");
        Assert.assertFalse("equalsDifferentDates return false",
                SimpleDate.equals(d1, d2));
    }

    @Test
    public void staticEqualsSameDates() {
        SimpleDate d1 = new SimpleDate("4/1/2013");
        SimpleDate d2 = new SimpleDate("4/1/2013");
        Assert.assertTrue("equalsSameDate return true",
                SimpleDate.equals(d1, d2));
    }

    @Test
    public void daysInYearLeapYear() {
        SimpleDate d1 = new SimpleDate("4/1/2020");
        Assert.assertEquals("Should return 366 for leap year 2020",
                d1.daysInYear(), 366);
    }

    @Test
    public void daysInRegularyear() {
        SimpleDate d1 = new SimpleDate("4/1/2013");
        Assert.assertEquals("Should return 365 for regular year 2013",
                d1.daysInYear(), 365);
    }

    @Test
    public void ordinalDateRegular() {
        SimpleDate d1 = new SimpleDate("2/10/2013");
        Assert.assertEquals("Should return 41 days since year",
                d1.ordinalDate(), 41);
    }

    @Test
    public void ordinalDateOnExtraDate() {
        SimpleDate d1 = new SimpleDate("2/29/2020");
        Assert.assertEquals("Should return 60 days since" +
                        "beginning of year, should count extra day",
                d1.ordinalDate(), 60);
    }

    @Test
    public void ordinalDateLeapYear() {
        SimpleDate d1 = new SimpleDate("4/1/2020");
        Assert.assertEquals("Should return 92 days since new year on " +
                "leap year", d1.ordinalDate(), 92);
    }

    @Test
    public void incrementNormalDay() {
        SimpleDate d1 = new SimpleDate("4/1/2013");
        d1.increment();
        Assert.assertEquals("Should have incremented day by 1",
                2, d1.getDay());
        Assert.assertEquals("Month should be unchanged from 4",
                4, d1.getMonth());
        Assert.assertEquals("Year should be unchaged from 2013",
                2013, d1.getYear());
    }

    @Test
    public void incrementEndOfMonthNotLeadYear() {
        SimpleDate d1 = new SimpleDate("2/28/2013");
        d1.increment();
        Assert.assertEquals("Should have reset to 1",
                1, d1.getDay());
        Assert.assertEquals("Month should have incremented to next month",
                3, d1.getMonth());
        Assert.assertEquals("Year should be unchanged from 2013",
                2013, d1.getYear());
    }

    @Test
    public void incrementNotEndOfFebLeapYear() {
        SimpleDate d1 = new SimpleDate("2/28/2020");
        d1.increment();
        Assert.assertEquals("Should have incremented date to 29",
                29, d1.getDay());
        Assert.assertEquals("Should not have changed Month",
                2, d1.getMonth());
        Assert.assertEquals("Should not have changed year",
                2020, d1.getYear());
    }

    @Test
    public void incrementEndOfFebLeapYear() {
        SimpleDate d1 = new SimpleDate("2/29/2020");
        d1.increment();
        Assert.assertEquals("Should have incremented date to 1",
                1, d1.getDay());
        Assert.assertEquals("month should be march",
                3, d1.getMonth());
        Assert.assertEquals("Year should not change",
                2020, d1.getYear());
    }

    @Test
    public void incrementEndOfMonthLeapYear(){
        SimpleDate d1 = new SimpleDate(1,4,2016);
        d1.increment();
        Assert.assertEquals("Should have incremented day to 5",
                5,d1.getDay());
        Assert.assertEquals("Should have left month unchanged",
                1,d1.getMonth());
        Assert.assertEquals("Should have left year unchanged",
                2016,d1.getYear());
    }

    @Test
    public void incrementEndOfOtherRegularMonthLeapYear() {
        SimpleDate d1 = new SimpleDate("3/31/2020");
        d1.increment();
        Assert.assertEquals("Should be first of April 2020",
                1, d1.getDay());
        Assert.assertEquals("Should be first of April 2020",
                4, d1.getMonth());
        Assert.assertEquals("Should not have changed year",
                2020, d1.getYear());
    }

    @Test
    public void incrementEndOfYearLeapYear() {
        SimpleDate d1 = new SimpleDate("12/31/2020");
        d1.increment();
        Assert.assertEquals("Should be first of January 2021",
                1, d1.getDay());
        Assert.assertEquals("Should be first of January 2021",
                1, d1.getMonth());
        Assert.assertEquals("The year should be 2021",
                2021, d1.getYear());
    }

    @Test
    public void incrementNormalMonthNormalYear() {
        SimpleDate d1 = new SimpleDate("3/31/2019");
        d1.increment();
        Assert.assertEquals("Should be first of April 2020",
                1, d1.getDay());
        Assert.assertEquals("Should be first of April 2020",
                4, d1.getMonth());
        Assert.assertEquals("Should not have changed year",
                2019, d1.getYear());
    }

    @Test
    public void incrementNormalEndOfYear() {
        SimpleDate d1 = new SimpleDate("12/31/2019");
        d1.increment();
        Assert.assertEquals("Should be first of January 2020",
                1, d1.getDay());
        Assert.assertEquals("Should be first of January 2020",
                1, d1.getMonth());
        Assert.assertEquals("The year should be 2020",
                2020, d1.getYear());
    }

    @Test
    public void toStringTest() {
        SimpleDate d1 = new SimpleDate("12/31/2020");
        SimpleDate d2 = new SimpleDate("1/1/2019");
        Assert.assertEquals("Should say December 31, 2020",
                "December 31, 2020", d1.toString());
        Assert.assertEquals("Should say January 01, 2019",
                "January 01, 2019", d2.toString());

    }

    @Test
    public void getNumberOfSimpleDates() {
        int n = SimpleDate.getSimpleDateCount();
        SimpleDate[] s1 = new SimpleDate[3];
        for (int i = 1; i < 4; i++) {
            s1[i - 1] = new SimpleDate(i, i, 2000 + i);
        }
        for (int i = 0; i < s1.length; i++) {
            System.out.println(s1[i].toString());
        }
        Assert.assertEquals("SimpleDateCount should be three large than N",
                n + 3, SimpleDate.getSimpleDateCount());
    }

    @Test
    public void getNumberOfSimpleDatesStringConstructor() {
        int n = SimpleDate.getSimpleDateCount();
        SimpleDate s1 = new SimpleDate("1/2/2002");
        System.out.println(s1.toString());
        Assert.assertEquals("String Constructor increment count 1",
                n + 1, SimpleDate.getSimpleDateCount());
    }

    @Test
    public void getNumberOfSimpleDatesDefaultcontructor() {
        int n = SimpleDate.getSimpleDateCount();
        SimpleDate s1 = new SimpleDate();
        Assert.assertEquals("Default Constructor increment date count",
                n + 1, SimpleDate.getSimpleDateCount());
    }

    @Test
    public void getNumberOfSimpleDatesCopyConstructor() {
        SimpleDate s1 = new SimpleDate(3, 1, 2011);
        int n = SimpleDate.getSimpleDateCount();
        SimpleDate s2 = new SimpleDate(s1);
        Assert.assertEquals("Copy construcktor should incrment 1",
                n + 1, SimpleDate.getSimpleDateCount());
    }

    @Test
    public void compareToEqualNumber() {
        SimpleDate s1 = new SimpleDate(3, 4, 2012);
        SimpleDate s2 = new SimpleDate(3, 4, 2012);
        Assert.assertEquals("S1 should compare be 0 compared to s2",
                0, s1.compareTo(s2));
    }

    @Test
    public void compareToLessThanDay() {
        SimpleDate s1 = new SimpleDate(3, 4, 2012);
        SimpleDate s2 = new SimpleDate(3, 5, 2012);
        Assert.assertEquals("s1 is less than s2", -1, s1.compareTo(s2));
    }

    @Test
    public void compareToGreaterThanDay() {
        SimpleDate s1 = new SimpleDate(3, 4, 2012);
        SimpleDate s2 = new SimpleDate(3, 3, 2012);
        Assert.assertEquals("s1 is less than s2", 1, s1.compareTo(s2));
    }

    @Test
    public void compareToLessThanYear() {
        SimpleDate s1 = new SimpleDate(3, 4, 2011);
        SimpleDate s2 = new SimpleDate(3, 5, 2012);
        Assert.assertEquals("s1 is less than s2", -1, s1.compareTo(s2));
    }

    @Test
    public void compareToGreaterThanMonth() {
        SimpleDate s1 = new SimpleDate(4, 4, 2012);
        SimpleDate s2 = new SimpleDate(3, 5, 2012);
        Assert.assertEquals("s1 is less than s2", 1, s1.compareTo(s2));
    }

    @Test
    public void compareToLessThanMonth() {
        SimpleDate s1 = new SimpleDate(2, 4, 2012);
        SimpleDate s2 = new SimpleDate(3, 5, 2012);
        Assert.assertEquals("s1 is less than s2", -1, s1.compareTo(s2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void compareToThrowException() {
        SimpleDate s1 = new SimpleDate(3, 4, 2012);
        SimpleDate s2 = new SimpleDate(31,31,3112);
    }

    @Test
    public void saveAndLoad(){
        SimpleDate s1 = new SimpleDate(1,24,2019);
        SimpleDate s2 = new SimpleDate();
        s1.save("s1.txt");
        s2.load("s1.txt");
        Assert.assertTrue("s2 should now equal s1",
                s1.equals(s2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void saveBadFileName(){
        SimpleDate s1 = new SimpleDate(1,24,2019);
        SimpleDate s2 = new SimpleDate();
        s1.save("***");
        s2.load("***");
        Assert.assertTrue("s2 should now equal s1",
                s1.equals(s2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void saveNullFileName(){
        SimpleDate s1 = new SimpleDate(1,24,2019);
        SimpleDate s2 = new SimpleDate();
        s1.save("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void saveAndLoadBadFileName(){
        SimpleDate s1 = new SimpleDate(1,24,2019);
        SimpleDate s2 = new SimpleDate();
        s1.save("s1.txt");
        s2.load("***");
        Assert.assertTrue("s2 should now equal s1",
                s1.equals(s2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void saveAndLoadNullFileName(){
        SimpleDate s1 = new SimpleDate(1,24,2019);
        SimpleDate s2 = new SimpleDate();
        s1.save("s1.txt");
        s2.load("");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDaysInMonthException(){
        SimpleDate.daysInMonth(13,1699);
    }

    @Test
    public void testDaysInMonthLeadYear(){
        Assert.assertEquals("During Leap Year, 29 days in February",
                29,SimpleDate.daysInMonth(2,2016));
    }

    @Test
    public void testDaysInMonth(){
        Assert.assertEquals("During Leap Year, 29 days in February",
                28,SimpleDate.daysInMonth(2,2017));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompareToThrowException(){
        SimpleDate s1 = new SimpleDate(1,24,2012);
        SimpleDate s2 = new SimpleDate();
        s1.compareTo(s2);
    }

    @Test
    public void testDecrement(){
        SimpleDate s1 = new SimpleDate(1,23,2019);
        s1.decrement();
        Assert.assertEquals("Month should be unchanged",
                1,s1.getMonth());
        Assert.assertEquals("Day should be 22",
                22,s1.getDay());
        Assert.assertEquals("Year should be unchanged",
                2019,s1.getYear());
    }

    @Test
    public void testDecrementEndOfMonth(){
        SimpleDate s1 = new SimpleDate(2,1,2019);
        s1.decrement();
        Assert.assertEquals("Month should be 1",
                1,s1.getMonth());
        Assert.assertEquals("Day should be 31",
                31,s1.getDay());
        Assert.assertEquals("Year should be unchanged",
                2019,s1.getYear());
    }

    @Test
    public void testDecrementEndOfMonthMarchLeapYear(){
        SimpleDate s1 = new SimpleDate(3,1,2020);
        s1.decrement();
        Assert.assertEquals("Month should be 2",
                2,s1.getMonth());
        Assert.assertEquals("Day should be 29 for leap year",
                29,s1.getDay());
        Assert.assertEquals("Year should be unchanged",
                2020,s1.getYear());
    }

    @Test
    public void testDecrementEndOfMonthMarchNotLeapYear(){
        SimpleDate s1 = new SimpleDate(3,1,2019);
        s1.decrement();
        Assert.assertEquals("Month should be 2",
                2,s1.getMonth());
        Assert.assertEquals("Day should be 28 for not leap year",
                28,s1.getDay());
        Assert.assertEquals("Year should be unchanged",
                2019,s1.getYear());
    }

    @Test
    public void testDecrementEndOfYear(){
        SimpleDate s1 = new SimpleDate(1,1,1754);
        s1.decrement();
        Assert.assertEquals("Month should be 12",
                12,s1.getMonth());
        Assert.assertEquals("Day should be 31 for not leap year",
                31,s1.getDay());
        Assert.assertEquals("Year should be 1753",
                1753,s1.getYear());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDecrementDateToSmall(){
        SimpleDate s1 = new SimpleDate(1,1,1753);
        s1.decrement();
    }

    @Test
    public void testDaysFromNowIncrement(){
        SimpleDate d1 = new SimpleDate(2,28,2020);
        SimpleDate d2 = d1.daysFromNow(5);
        Assert.assertEquals("The Month should be 3",
                3,d2.getMonth());
        Assert.assertEquals("Day should be 4",
                4,d2.getDay());
        Assert.assertEquals("The year should be be unchanged",
                2020,d2.getYear());
    }

    @Test
    public void testDaysFromNowDecrement(){
        SimpleDate d1 = new SimpleDate(1,2,2020);
        SimpleDate d2 = d1.daysFromNow(-5);
        Assert.assertEquals("The Month should be 12",
                12,d2.getMonth());
        Assert.assertEquals("Day should be 28",
                28,d2.getDay());
        Assert.assertEquals("The year should be be 2019",
                2019,d2.getYear());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testDaysFromNowDefaultConstructor(){
        SimpleDate d1 = new SimpleDate();
        SimpleDate d2 = d1.daysFromNow(12);
    }
}

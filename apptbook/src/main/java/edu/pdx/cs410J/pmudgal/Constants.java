package edu.pdx.cs410J.pmudgal;

/**
 * Created by Vijay on 6/27/2016.
 */
public class Constants {
    public static final String PRINT="-print";
    public static final String TEXTFILE="-textFile";
    public static final String PRETTYPRINT="-pretty";
    public static final String README="-README";
    public static final String PROVIDE_ARG="Please provide the arguments in order: owner description beginDateTime endDateTime";

    public static final String README_DESC="This is PROJECT1 written by PRIYANKA MUDGAL.\n" +
            "It is now behaving as Project 1. If you want to read README of project 2, please add -textfile filename as the option. \n"+
            " The motive of this project is to prepare an appointment book containing the owner name, description, begintime and endTime.\n" +
            " The project provides an additional functionality to print the description or just print the README.\n";

    public static final String PROJECT2_README_DESC="This is PROJECT2 written by PRIYANKA MUDGAL.\n" +
            "The motive of this project is to prepare an appointment book containing the owner name, description, begintime and endTime.\n" +
            "The project provides an additional functionality to print the description or just print the README.\n" +
            "This projects also creates a text file with all/single appointments of the owner.\n"+
            "It also makes sure that a appointmentbook belongs to a single owner only.\n ";

    public static final String PROJECT3_README_DESC="This is PROJECT3 written by PRIYANKA MUDGAL.\n" +
            "The motive of this project is to prepare an appointment book containing the owner name, description, begintime and endTime.\n" +
            "The project provides an additional functionality to print the description or just print the README.\n" +
            "This projects also creates a text file with all/single appointments of the owner.\n"+
            "It also makes sure that a appointmentbook belongs to a single owner only.\n "+
            "This project has an additional functionality of pretty print either to standard out or to file depending on user preference."  ;

    public static final String usage=" java edu.pdx.cs410J.pmudgal.Project2 [options] <args>\n" +
            "args are (in this order):\n" +
            "owner: The person whose owns the appt book\n" +
            "description: A description of the appointment\n" +
            "beginTime: When the appt begins (24-hour time)\n" +
            "endTime: When the appt ends (24-hour time)\n" +
            "options are (options may appear in any order):\n" +
            "-print Prints a description of the new appointment\n" +
            "-README Prints a README for this project and exits\n" +
            "Date and time should be in the format: mm/dd/yyyy hh:mm\n";
}

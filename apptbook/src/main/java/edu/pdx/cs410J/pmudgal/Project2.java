package edu.pdx.cs410J.pmudgal;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Priyanka on 7/6/2016.
 */
public class Project2 {

    public static void main(String[] args){
        parseCommandLineArgs(args);

    }

    private static void parseCommandLineArgs(String[] args) {
        Project1 project1=new Project1();
        AppointmentBook appointmentBook = new AppointmentBook();

        try {
            ArrayList<String> arrayList= new ArrayList<String>(Arrays.asList(args));
            if(arrayList.contains(Constants.TEXTFILE)) {
                int index=arrayList.indexOf(Constants.TEXTFILE);
                String filename=arrayList.get(index+1);
                arrayList.remove(index+1);
                arrayList.remove(Constants.TEXTFILE);

                String[] stockArr = new String[arrayList.size()];
                stockArr = arrayList.toArray(new String[arrayList.size()]);
                System.out.println(stockArr.length);
                appointmentBook = dumpTheContentsToFile(filename,stockArr);
                TextDumper textDumper = new TextDumper(filename);
                textDumper.dump(appointmentBook);
            }else{
                project1.prepareAppointmentBook(args,appointmentBook);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /*public static void writeToFile(String filename, AppointmentBook appointmentBook) {
        try {
            //Whatever the file path is.
            File file = new File(filename);
            if(file.createNewFile()) {
                System.out.println("Created new file");
                FileOutputStream is = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                BufferedWriter w = new BufferedWriter(osw);
                for(Appointment appointment:appointmentBook.getAppointments()){
                    writeToFile(w, appointment);
                }
                w.close();
            }else{
                System.out.println("File already exists");
                FileOutputStream is = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                BufferedWriter w = new BufferedWriter(osw);
                for(Appointment appointment:appointmentBook.getAppointments()){
                    writeToFile(w, appointment);
                }
                w.close();

            }
        } catch (IOException e) {
            e.getMessage();
            System.err.println("Problem writing to the file :" + filename);
            e.printStackTrace();
        }
    }*/

   /* private static void writeToFile(BufferedWriter w, Appointment appointment) throws IOException {
        w.write(appointment.getOwner());
        w.write(",");
        w.write(appointment.getDescription());
        w.write(",");
        w.write(appointment.getBeginTimeString());
        w.write(",");
        w.write(appointment.getEndTimeString());
        w.newLine();

    }
*/
    public static AppointmentBook dumpTheContentsToFile(String filename, String[] args) throws Exception {
        Project1 project1 =new Project1();
        AbstractAppointmentBook appointmentBook = new AppointmentBook();
        TextParser textParser = new TextParser(filename, (AppointmentBook) appointmentBook);
        appointmentBook = textParser.parse();
        appointmentBook = project1.prepareAppointmentBook(args, (AppointmentBook) appointmentBook);
        return (AppointmentBook) appointmentBook;
    }

    /*public static AppointmentBook readFromFile(String filename, AppointmentBook appointmentBook) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        try {
            for(String line : Files.readAllLines(Paths.get(filename))){
                if(line!=null && !line.isEmpty())
                System.out.println(" inside for : "+ line);
                StringTokenizer stringTokenizer = new StringTokenizer(line,",");
                Appointment appointment =new Appointment();
                while(stringTokenizer.hasMoreTokens()){
                    appointment.setOwner(stringTokenizer.nextToken());
                    appointment.setDescription(stringTokenizer.nextToken());
                    appointment.setBeginTimeString(stringTokenizer.nextToken());
                    appointment.setEndTimeString(stringTokenizer.nextToken());
                    appointmentBook.addAppointment(appointment);
                }
            }
        } catch(Exception e){
            e.getMessage();
        }
        finally {
            br.close();
        }
        System.out.println(appointmentBook.getAppointments().size() + " Size");
        return appointmentBook;
    }*/

}


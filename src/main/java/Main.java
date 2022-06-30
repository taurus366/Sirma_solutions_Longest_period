import org.w3c.dom.Node;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

 static int tableSize;
    public static void main(String[] args) throws FileNotFoundException {


        Map<Integer,List<Employee>> map = new HashMap<>();
//        Map<Integer,List>

        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/employees.csv"));
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(", ");
                System.out.println(Arrays.toString(employee));
                mapPopulate(map,employee);
            }
        }
        catch(IOException | ParseException e) {
            e.printStackTrace();
        }

        int jobId = 0;
        long days = Long.MIN_VALUE;

        for (Map.Entry<Integer, List<Employee>> entry : map.entrySet()) {
            long currentDays = entry.getValue().stream().mapToLong(Employee::getWorkedDays).sum();
            if (currentDays > days) {
                jobId = entry.getKey();
                days = currentDays;
            }
        }

        System.out.printf("%s, %d , %d", map.get(jobId).stream().map(employee -> String.valueOf(employee.getEmpId())).collect(Collectors.joining(", ")),jobId,days);

//        System.out.println(map.size());
    }

    static void mapPopulate(Map<Integer,List<Employee>> map,String[] arr) throws ParseException {
        Calendar dateFrom = Calendar.getInstance();
        Calendar dateTo  = Calendar.getInstance();

        int jobId = Integer.parseInt(arr[1]);
        int empId = Integer.parseInt(arr[0]);
        dateFrom.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(arr[2]));
        try {
        dateTo.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(arr[3]));
        } catch (ParseException ex) {
            dateTo.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(Instant.now())));
        }


            long workedDays = calculateWorkedDays(dateFrom,dateTo);

        Employee employee = new Employee();
        employee
                .setEmpId(Integer.parseInt(arr[0]))
                .setJobId(jobId)
                .setDateTo(dateTo)
                .setDateFrom(dateFrom)
                .setWorkedDays(workedDays);



        if (!map.containsKey(jobId)) {
            map.put(jobId,new ArrayList<>());
        }

      map.get(jobId).add(employee);

    }

    static long calculateWorkedDays(Calendar dateFrom,Calendar dateTo) {

        return TimeUnit.MILLISECONDS.toDays((long) dateTo.getTimeInMillis() - dateFrom.getTimeInMillis());
    }
}












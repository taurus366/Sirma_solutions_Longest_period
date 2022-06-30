import java.util.Calendar;

public class Employee {

    private int EmpId;
    private int jobId;
    private Calendar dateFrom;
    private Calendar dateTo;
    private long workedDays;

    public Employee() {
    }

    public int getEmpId() {
        return EmpId;
    }

    public Employee setEmpId(int empId) {
        EmpId = empId;
        return this;
    }

    public int getJobId() {
        return jobId;
    }

    public Employee setJobId(int jobId) {
        this.jobId = jobId;
        return this;
    }

    public Calendar getDateFrom() {
        return dateFrom;
    }

    public Employee setDateFrom(Calendar dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public Calendar getDateTo() {
        return dateTo;
    }

    public Employee setDateTo(Calendar dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public long getWorkedDays() {
        return workedDays;
    }

    public Employee setWorkedDays(long workedDays) {
        this.workedDays = workedDays;
        return this;
    }
}

package assessment.juniorpost.employee;


import java.util.ArrayList;
import java.util.List;

public class EmployeeTaskExecutor implements Runnable {

    private EmployeInfoRepository employeInfoRepository;


    private List<String> lines;


    public EmployeeTaskExecutor() {
    }


    public EmployeeTaskExecutor(EmployeInfoRepository employeInfoRepository, List<String> lines) {
        this.employeInfoRepository = employeInfoRepository;
        this.lines = lines;
    }

    @Override
    public void run() {
        List<EmployeInfo> employeInfos = new ArrayList<>();
        for (String s : lines) {
            String[] values = s.split(",");
            EmployeInfo employeInfo = new EmployeInfo(values[0],
                    values[1],
                    values[2],
                    values[3]);

            employeInfos.add(employeInfo);
        }

        employeInfoRepository.saveAll(employeInfos);
    }


}

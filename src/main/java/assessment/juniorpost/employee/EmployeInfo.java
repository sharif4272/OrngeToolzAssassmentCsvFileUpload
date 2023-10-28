package assessment.juniorpost.employee;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "EMPLOYEE_INFORMATION")
public class EmployeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String empName;
    private String empAge;
    private String empDesignation;
    private String mobileNo;


    public EmployeInfo() {
    }

    public EmployeInfo(String empName, String empAge, String empDesignation, String mobileNo) {
        this.empName = empName;
        this.empAge = empAge;
        this.empDesignation = empDesignation;
        this.mobileNo = mobileNo;
    }

    public EmployeInfo(Long id, String empName, String empAge, String empDesignation, String mobileNo) {
        this.id = id;
        this.empName = empName;
        this.empAge = empAge;
        this.empDesignation = empDesignation;
        this.mobileNo = mobileNo;
    }
}

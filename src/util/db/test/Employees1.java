package util.db.test;


import java.util.Date;

public class Employees1 {

	private Date hire_date;
	private Float commission_pct;
	private Integer department_id;
	private Long employee_id;
	private Long manager_id;
	private Double salary;

	private String job_id;
	private String first_name;
	private String phone_number;
	private String email;
	private String last_name;

	public Employees1() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employees1(Date hire_date, Float commission_pct, Integer department_id, Long employee_id,
			Long manager_id, Double salary, String job_id, String first_name, String phone_number, String email,
			String last_name) {
		super();
		this.hire_date = hire_date;
		this.commission_pct = commission_pct;
		this.department_id = department_id;
		this.employee_id = employee_id;
		this.manager_id = manager_id;
		this.salary = salary;
		this.job_id = job_id;
		this.first_name = first_name;
		this.phone_number = phone_number;
		this.email = email;
		this.last_name = last_name;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public Float getCommission_pct() {
		return commission_pct;
	}

	public void setCommission_pct(Float commission_pct) {
		this.commission_pct = commission_pct;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Long getManager_id() {
		return manager_id;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Override
	public String toString() {
		return "{hire_date:" + hire_date + ",commission_pct:" + commission_pct + ",department_id:" + department_id
				+ ",employee_id:" + employee_id + ",manager_id:" + manager_id + ",salary:" + salary + ",job_id:"
				+ job_id + ",first_name:" + first_name + ",phone_number:" + phone_number + ",email:" + email
				+ ",last_name:" + last_name + "}";
	}

}

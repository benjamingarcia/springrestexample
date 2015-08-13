package com.howtodoinjava.demo.controller;
 
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.howtodoinjava.demo.model.EmployeeListVO;
import com.howtodoinjava.demo.model.EmployeeVO;

import static org.springframework.http.HttpMethod.*;

@RestController
public class EmployeeRESTController 
{

    private EmployeeListVO employees = new EmployeeListVO();

    public EmployeeRESTController() {
        EmployeeVO empOne = new EmployeeVO(0,"Lokesh","Gupta","howtodoinjava@gmail.com");
        EmployeeVO empTwo = new EmployeeVO(1,"Amit","Singhal","asinghal@yahoo.com");
        EmployeeVO empThree = new EmployeeVO(2,"Kirti","Mishra","kmishra@gmail.com");

        employees.getEmployees().add(empOne);
        employees.getEmployees().add(empTwo);
        employees.getEmployees().add(empThree);
    }

    @RequestMapping(value = "/employees", produces = {"application/json", "application/xml"})
    public @ResponseBody EmployeeListVO getAllEmployees() 
    {
        return employees;
    }
     
    @RequestMapping(value = "/employees/{id}", produces = {"application/json", "application/xml"})
    @ResponseBody
    public ResponseEntity<EmployeeVO> getEmployeeById (@PathVariable("id") int id) 
    {
        if (id < employees.getEmployees().size()) {
            EmployeeVO employee = employees.getEmployees().get(id);
            return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/employee", produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    @ResponseBody
    public ResponseEntity<EmployeeVO> addEmployeeById (@RequestBody EmployeeVO employeeVO)
    {
        employeeVO.setId(employees.getEmployees().size());
        employees.getEmployees().add(employeeVO);
        return new ResponseEntity<EmployeeVO>(HttpStatus.OK);
    }
}
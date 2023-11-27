package com.example.employeems.controller;

import com.example.employeems.dto.EmployeeDTO;
import com.example.employeems.dto.ResponnseDTO;
import com.example.employeems.service.EmployeeService;
import com.example.employeems.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private ResponnseDTO responnseDTO;
  @Autowired
  private EmployeeService employeeService;
   @PostMapping(value = "/saveEmployee")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){
       try {
            String res=employeeService.saveEmployee(employeeDTO);
            if(res.equals(VarList.RSP_SUCCESS)){
                responnseDTO.setCode(VarList.RSP_SUCCESS);
                responnseDTO.setMessage("Successfully Registered!");
                responnseDTO.setContent(employeeDTO);
                return new ResponseEntity(responnseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals(VarList.RSP_DUPLICATE)){
                responnseDTO.setCode(VarList.RSP_DUPLICATE);
                responnseDTO.setMessage("This Employee Already Registered");
                responnseDTO.setContent(employeeDTO);
                return new ResponseEntity(responnseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responnseDTO.setCode(VarList.RSP_FAIL);
                responnseDTO.setMessage("Error");
                responnseDTO.setContent(null);
                return new ResponseEntity(responnseDTO, HttpStatus.BAD_REQUEST);
            }
       }catch (Exception e){
           responnseDTO.setCode(VarList.RSP_ERROR);
           responnseDTO.setMessage(e.getMessage());
           responnseDTO.setContent(null);
           return new ResponseEntity(responnseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping(value = "/updateEmployee")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try {
            String res=employeeService.updateEmployee(employeeDTO);
            if(res.equals(VarList.RSP_SUCCESS)){
                responnseDTO.setCode(VarList.RSP_SUCCESS);
                responnseDTO.setMessage("Successfully Updated!");
                responnseDTO.setContent(employeeDTO);
                return new ResponseEntity(responnseDTO, HttpStatus.ACCEPTED);
            }else if(res.equals(VarList.RSP_NO_DATA_FOUND)){
                responnseDTO.setCode(VarList.RSP_DUPLICATE);
                responnseDTO.setMessage("This Employee is not Registered");
                responnseDTO.setContent(employeeDTO);
                return new ResponseEntity(responnseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responnseDTO.setCode(VarList.RSP_FAIL);
                responnseDTO.setMessage("Error");
                responnseDTO.setContent(null);
                return new ResponseEntity(responnseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responnseDTO.setCode(VarList.RSP_ERROR);
            responnseDTO.setMessage(e.getMessage());
            responnseDTO.setContent(null);
            return new ResponseEntity(responnseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getALLEmployees")
    public ResponseEntity getALLEmployees(){
       try{
           List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployee();
           responnseDTO.setCode(VarList.RSP_SUCCESS);
           responnseDTO.setMessage("Success");
           responnseDTO.setContent(employeeDTOList);
           return new ResponseEntity(responnseDTO, HttpStatus.ACCEPTED);

       }catch (Exception e){
           responnseDTO.setCode(VarList.RSP_ERROR);
           responnseDTO.setMessage(e.getMessage());
           responnseDTO.setContent(null);
           return new ResponseEntity(responnseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

       }
    }

    @GetMapping("/searchEmployee/{empID}")
    public ResponseEntity  searchEmployee(@PathVariable int empID){
        try {
            EmployeeDTO employeeDTO=employeeService.searchEmployee(empID);
            if(employeeDTO!=null){
                responnseDTO.setCode(VarList.RSP_SUCCESS);
                responnseDTO.setMessage("Success");
                responnseDTO.setContent(employeeDTO);
                return new ResponseEntity(responnseDTO, HttpStatus.ACCEPTED);
            }else{
                responnseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responnseDTO.setMessage("No Employee found for this Employee ID");
                responnseDTO.setContent(null);
                return new ResponseEntity(responnseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responnseDTO.setCode(VarList.RSP_ERROR);
            responnseDTO.setMessage(e.getMessage());
            responnseDTO.setContent(null);
            return new ResponseEntity(responnseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEmployee/{empID}")
    public ResponseEntity  deleteEmployee(@PathVariable int empID){
        try {
            String res=employeeService.deleteEmployee(empID);
            if(res.equals(VarList.RSP_SUCCESS)){
                responnseDTO.setCode(VarList.RSP_SUCCESS);
                responnseDTO.setMessage("Success");
                responnseDTO.setContent(null);
                return new ResponseEntity(responnseDTO, HttpStatus.ACCEPTED);
            }else{
                responnseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responnseDTO.setMessage("No Employee found for this Employee ID");
                responnseDTO.setContent(null);
                return new ResponseEntity(responnseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            responnseDTO.setCode(VarList.RSP_ERROR);
            responnseDTO.setMessage(e.getMessage());
            responnseDTO.setContent(null);
            return new ResponseEntity(responnseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    }




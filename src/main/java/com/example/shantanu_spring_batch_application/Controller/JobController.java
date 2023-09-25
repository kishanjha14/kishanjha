package com.example.shantanu_spring_batch_application.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shantanu_spring_batch_application.Dto.CustomerDto;
import com.example.shantanu_spring_batch_application.Repository.CustomerRepository;
import com.example.shantanu_spring_batch_application.entity.Customer;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private Job job;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    
    @PostMapping("/importCustomers")
    public void importCsvToDBJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/get")
    public List<CustomerDto> get()
    {
    	List<Customer> list =  customerRepository.findAll();
    	return customertoDto((list));
    }
  
	public CustomerDto customertoDto(Customer customer)
    {
    	CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);
    	
    	return customerDto;
    }
	
	
	public  List<CustomerDto> customertoDto(List<Customer> customers)
	{
		return customers.stream().map(x->customertoDto(x)).collect(Collectors.toList());
		
	}
}


/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock;

import hu.gaborkolozsy.timeclock.model.Customer;
import hu.gaborkolozsy.timeclock.model.Customer.CustomerBuilder;
import hu.gaborkolozsy.timeclock.model.Developer;
import hu.gaborkolozsy.timeclock.model.Developer.DeveloperBuilder;
import hu.gaborkolozsy.timeclock.model.Job;
import hu.gaborkolozsy.timeclock.model.Job.JobBuilder;
import hu.gaborkolozsy.timeclock.model.Pay;
import hu.gaborkolozsy.timeclock.model.Pay.PayBuilder;
import hu.gaborkolozsy.timeclock.model.WorkingHours;
import hu.gaborkolozsy.timeclock.model.WorkingHours.WorkingHoursBuilder;
import hu.gaborkolozsy.timeclock.model.embedded.Address;
import hu.gaborkolozsy.timeclock.service.CustomerService;
import hu.gaborkolozsy.timeclock.service.DeveloperService;
import hu.gaborkolozsy.timeclock.service.JobService;
import hu.gaborkolozsy.timeclock.service.PayService;
import hu.gaborkolozsy.timeclock.service.WorkingHoursService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Common <i><u>@Development</u></i> annotated test class.<br>
 * Here can definition e.g. the {@link Before}, {@link After} etc. methods.
 * 
 * <p>The <i>{@code @Development}</i> annotation can use as a default test configuration 
 * or can override the 
 * <i>{@link org.springframework.test.context.ContextConfiguration}'s</i> application 
 * config classes and can override the 
 * <i>{@code ContextConfiguration}'s</i> application config loader class,
 * additionally can overrride the <i>{@code ContextConfiguration}'s</i> 
 * locations attribute for <i>.xml</i> configuration file(s).
 * 
 * <p>The {@code @Development} annotation can override the active profile 
 * or can adding beside to default.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
@Development
public class DevelopmentTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private DeveloperService developerService;
    
    @Autowired
    private WorkingHoursService workingHoursService;
    
    @Autowired 
    private JobService jobService;
    
    @Autowired
    private PayService payService;
    
    /**
     * Initialize embedded database before all test method.
     */
    @Before
    public void before() {
        for (int i = 100; i < 102; i++) {
            Customer customer = createCustomer(i);
            customerService.save(customer);
            
            Developer developer = createDeveloper(i);
            developerService.save(developer);
            
            for (int j = i; j < i+3; j++) {
                WorkingHours workingHours = new WorkingHoursBuilder(createWorkingHours())
                        .setDeveloper(developer)
                        .build();
                workingHoursService.save(workingHours);                
            }
            
            Job job = new JobBuilder(createJob(i))
                    .setCustomer(customer)
                    .build();
            jobService.save(job);
            
            Pay pay = new PayBuilder(createPay(i))
                    .setJob(job)
                    .build();
            payService.save(pay);
        }
    }
    
    /**
     * Remove all entity instance after all test method.
     */
    @After
    public void after() {        
        payService.removeAll();
        jobService.removeAll();
        workingHoursService.removeAll();
        developerService.removeAll();        
        customerService.removeAll();
    }
    
    /**
     * Logger test.
     */
    @Test
    public void loggerTest() {
        assertNotNull("Log is null!", logger);
    }
    
    /**
     * Application context test.
     */
    @Test
    public void applicationContextTest() {
        assertNotNull("ApplicationContext is null!", applicationContext);
    }
    
    /**
     * Create a {@code Customer} instance.
     * @return {@code Customer} 
     */
    private static Customer createCustomer(int someIndex) {
        return new CustomerBuilder()
                .setCustomerId((long)someIndex)
                .setName("Company"+someIndex)
                .setContact("Secretary")
                .setAddress(createAddressForCostumer(someIndex))
                .build();
    }
    
    /**
     * Create a {@code Developer} instance.
     * @return {@code Developer} 
     */
    private static Developer createDeveloper(int someIndex) {
        return new DeveloperBuilder()
                .setDeveloperId((long) someIndex)
                .setForename("Megan")
                .setLastName("Fox")
                .setAddress(createAddressForDeveloper(someIndex))
                .build();
    }
    
    /**
     * Create a {@code WorkingHours} instance.
     * @return {@code WorkingHours}
     */
    private static WorkingHours createWorkingHours() {
        return new WorkingHoursBuilder()
                .setDay(LocalDate.now())
                .setWorkStart(LocalDateTime.now())
                .build();               
    }
    
    /**
     * Create a {@code Job} instance.
     * @return {@code Job} 
     */
    private static Job createJob(int someIndex) {
        return new JobBuilder()
                .setOrderNumber(someIndex)
                .setProjectName("Project")
                .setStatus("WIP")
                .build();
    }
    
    /**
     * Create a {@code Pay} instance.
     * @return {@code Pay} 
     */
    private static Pay createPay(int someIndex) {
        String payId = "1600-0100-000" + someIndex;
        return new PayBuilder()
                .setPayId(payId)
                .setPayable(true)
                .setPaid(false)
                .build();
    }
    
    /**
     * Create a {@code Address} instance for {@code Costumer}.
     * @return {@code Address} 
     */
    private static Address createAddressForCostumer(int someIndex) {
        return new Address.AddressBuilder()
                .setCountry("Hungary")
                .setCity("Budapest")
                .setRegion("Pest megye")
                .setAddress("Blind Street 1.")
                .setZip(1492)
                .setPob(someIndex)
                .setPhoneNumber("7654321")
                .setEmailAddress("customer@testemail.com")
                .build();
    }
    
    /**
     * Create a {@code Address} instance for {@code Costumer}.
     * @return {@code Address} 
     */
    private static Address createAddressForDeveloper(int someIndex) {
        return new Address.AddressBuilder()
                .setCountry("USA")
                .setCity("LosAngeles")
                .setRegion("Down town")
                .setAddress("Developer Street 1.")
                .setZip(90001)
                .setPob(someIndex)
                .setPhoneNumber("1234567")
                .setEmailAddress("developer@testemail.com")
                .build();
    }
    
    /**
     * Exception verifier inner class.
     */
    public final class ExceptionVerifier {

        private final Supplier<Object> resultSupplier;

        public ExceptionVerifier(Supplier<Object> resultSupplier) {
            this.resultSupplier = resultSupplier;
        }

        public void isThrowing(Class<? extends Exception> exception) {
            try {
                fail("Expected suggestion to throw " + exception.getName() + 
                        ", but was " + resultSupplier.get());
            } catch (Exception ex) {
                String message = "Expected suggestion to throw " + 
                        exception.getSimpleName() + " instead of " + 
                        ex.getClass().getSimpleName();
                assertEquals(message, exception, ex.getClass());
            }
        }
    }
        
}

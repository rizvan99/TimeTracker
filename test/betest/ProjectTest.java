package betest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timetrackingexam.be.Project;

public class ProjectTest {
    
    public ProjectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test 
    public void testGetSetID(){
        Project p = new Project("testProject", 1, "No more testing", 150);
        p.setId(1);
        assertEquals(1, p.getId());
    }
    
    @Test 
    public void testGetSetName(){
        Project p = new Project("testProject", 1, "No more testing", 150);
        p.setName("AlternateTestName");
        assertEquals("AlternateTestName", p.getName());
    }
    @Test 
    public void testGetSetClientID(){
        Project p = new Project("testProject", 1, "No more testing", 150);
        p.setClientID(5);
        assertEquals(5, p.getClientID());
    }
    @Test 
    public void testGetSetRate(){
        Project p = new Project("testProject", 1, "No more testing", 150);
        
        p.setRate(250);
        assertEquals(250, p.getRate());
    }
    @Test 
    public void testGetSetDesc(){
        Project p = new Project("testProject", 1, "No more testing", 150);
        
    }
    @Test 
    public void testSetDesc(){
        Project p = new Project("testProject", 1, "No more testing", 150);
        p.setDescription("I need information on TDD");
        
        assertEquals("I need information on TDD", p.getDescription());
    }
}

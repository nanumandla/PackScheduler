package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests CourseNameValidator class
 * 
 * @author Ashwin Mahesh
 * @author Taylor Liu
 */
public class CourseNameValidatorTest {
	/** CourseNameValidator object */
	private CourseNameValidator validator;
    
	/**
     * Creates a new validator object before each test
     */
    @BeforeEach
    public void setUp() {
        validator = new CourseNameValidator();
    }
    
    /**
     * Tests valid course names
     */
    @Test
    public void testValidCourseNames() {
        assertAll(
            () -> assertTrue(validator.isValid("CSC116")),
            () -> assertTrue(validator.isValid("E115")),
            () -> assertTrue(validator.isValid("MA242")),
            () -> assertTrue(validator.isValid("PY123")),
            () -> assertTrue(validator.isValid("HESF101")),
            () -> assertTrue(validator.isValid("CSC216H"))
        );
    }
    
    /**
     * Tests invalid starting characters  for course names 
     */
    @Test
    public void testInvalidStartingCharacter() {
        assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("123ABC");
        });
    }
    
    /**
     * Tests invalid lengths for each course name 
     */
    @Test
    public void testInvalidLength() {
        assertDoesNotThrow(() -> {
            validator.isValid("C116"); // Not enough digits
        });
        assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("CS2345"); // Too many digits
        });
    }
    
    /**
     * Tests invalid characters for each course name
     */
    @Test
    public void testInvalidCharacters() {
        assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("CSC#216");
        });
    }
    
    /**
     * Tests course names with exceeded letter count
     */
    @Test
    public void testExceedLetterCount() {
        assertDoesNotThrow( () -> {
            validator.isValid("CSCA123");
        });
    }
    
    /**
     * Tests an invalid transition with invalid course name
     */
    @Test
    public void teststateD() {
        assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("CSCAAJKDGS123");
        });
    }
    
    /**
     * Tests an invalid transition with invalid course name 
     */
    @Test
    public void testStateD2() {
    	assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("CSC1a");
        });
    }
    
    /**
     * Tests an invalid transition with invalid course name 
     */
    @Test
    public void testStateD3() {
    	assertDoesNotThrow(() -> 
        validator.isValid("CSC123a")
    );
        
    }
    
    /**
     * Tests an invalid transition with invalid course name 
     */
    @Test
    public void testStateD4() {
        assertThrows(InvalidTransitionException.class, () -> {
            validator.isValid("CSC12311");
        });
    }
    
    /**
     * Tests valid and invalid suffixes
     */
    @Test
    public void testSuffix() {
        assertAll(
            () -> assertTrue(validator.isValid("CSC116A")),
            () -> assertThrows(InvalidTransitionException.class, () -> {
                validator.isValid("CSC116ABc");
            }),
            () -> assertThrows(InvalidTransitionException.class, () -> {
                validator.isValid("CSC216H1");
            })
        );
    }
   
}

package de.tum.in.www1.eist;

import static org.junit.Assert.*;

import org.junit.Test;

import de.tum.in.www1.eist.UniversityApp;

public class UniversityAppTest {

    @Test
    public void testButtonText() {
        UniversityApp app = new UniversityApp();
        String buttonText = app.getButtonText();
        assertEquals("getButtonText() should return EIST","EIST", buttonText);
    }
}

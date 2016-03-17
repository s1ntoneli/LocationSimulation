package com.kodelabs.boilerplate;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void regex_matched() throws Exception {
        String text = "liScottxin";
        String pat = "Scott";
        System.out.println(Pattern.compile(pat).matcher(text).matches());
    }
    @Test
    public void regex_matched2() throws Exception {
        String text = "liScottxin";
        String pat = ".*Scott.*";
        System.out.println(Pattern.compile(pat).matcher(text).matches());
    }
    @Test
    public void regex_matched3() throws Exception {
        String text = "Scottxin";
        String pat = ".+Scott.*";
        System.out.println(Pattern.compile(pat).matcher(text).matches());
    }
}
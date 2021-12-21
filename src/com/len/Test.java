package com.len;

import com.len.serializer.MyJSON;
import com.len.serializer.MyXML;

import java.util.List;

public class Test {
    @MyXML("string")
    @MyJSON("string")
    private String string;
    @MyXML("int")
    @MyJSON("int")
    private int anInt;
    @MyXML("floats")
    @MyJSON("floats")
    private float[] floats;
    @MyXML("tests")
    @MyJSON("tests")
    private List<Test> testList;
    @MyXML("ints")
    @MyJSON("ints")
    private List<Integer> integers;
    @MyXML("strings")
    @MyJSON("strings")
    private String[] strings;

    public Test(String string, int anInt, float[] floats, List<Test> testList, List<Integer> integers, String[] strings) {
        this.string = string;
        this.anInt = anInt;
        this.floats = floats;
        this.testList = testList;
        this.integers = integers;
        this.strings = strings;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public float[] getFloats() {
        return floats;
    }

    public void setFloats(float[] floats) {
        this.floats = floats;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(List<Integer> integers) {
        this.integers = integers;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }
}

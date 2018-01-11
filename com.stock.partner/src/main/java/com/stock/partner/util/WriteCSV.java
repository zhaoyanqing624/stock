package com.stock.partner.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import au.com.bytecode.opencsv.CSVWriter;


public class WriteCSV {
    public static void main(String[] args) throws Exception {  
        File file = new File("E:\\data\\nasdaq\\1.csv");  
        Writer writer = new FileWriter(file,true);  
        CSVWriter csvWriter = new CSVWriter(writer, ',');  
        String[] strs = {"abc" , "abc" , "abc"};  
        csvWriter.writeNext(strs);  
        csvWriter.close();  
    }
    
    public static void writeCSV_Nasdaq(String symbol,String[] strs) throws IOException {
        File file = new File("E:\\data\\nasdaq\\"+symbol+".csv");  
        Writer writer = new FileWriter(file,true);  
        CSVWriter csvWriter = new CSVWriter(writer, ',');  
        csvWriter.writeNext(strs);  
        csvWriter.close();
    }
}

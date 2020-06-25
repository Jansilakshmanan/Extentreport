package UtilityLayer;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Getexceldataintohashmaps {
    Properties properties;
    String testdatapath;
    public Getexceldataintohashmaps()
    {
        properties=new Properties();
        try {
            FileInputStream ip = new FileInputStream("C:\\Selenium_projects\\ExtentReport\\src\\main\\java\\UtilityLayer\\Config.properties");
            properties.load(ip);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public   Object[][] getdataintohashmaparray() throws IOException {
        testdatapath= System.getProperty("user.dir")+properties.getProperty("testdata");
        System.out.println( "Test data path="+testdatapath);

        Workbook book= new XSSFWorkbook(testdatapath);
        Sheet sheet=book.getSheetAt(0);
        int totalrow=sheet.getLastRowNum();
        int totalcol=sheet.getRow(0).getLastCellNum();
        Object[][] data=new Object[totalrow][1];
        for(int i=0;i<totalrow;i++)
        {
            HashMap<String,String> map=new HashMap<String,String>();
            for(int j=0;j<totalcol;j++)
            {
                map.put(sheet.getRow(0).getCell(j).toString(),sheet.getRow(i+1).getCell(j).toString());


            }
            data[i][0]=map;
        }

        return data;

    }

}

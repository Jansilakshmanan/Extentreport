package UtilityLayer;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

import java.lang.reflect.Method;
import java.util.Properties;

public class GetExceldataintoObjectarray {
    Properties properties;
    String testdatapath;
    public GetExceldataintoObjectarray()
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
    public  Object[][] readexceldata() throws IOException {
        testdatapath= System.getProperty("user.dir")+properties.getProperty("testdata");
        FileInputStream ip=new FileInputStream(testdatapath);
        Workbook book=new XSSFWorkbook(ip);
        Sheet sheet=book.getSheetAt(0);
        int rowno=sheet.getLastRowNum();
        int colno=sheet.getRow(0).getLastCellNum();
        Object[][] data=new Object[rowno][colno];
        for(int i=0;i<rowno;i++)
        {

            for(int j=0;j<colno;j++)
            { data[i][j]=sheet.getRow(i+1).getCell(j).toString();

            }

        }

        return data;
    }

    }

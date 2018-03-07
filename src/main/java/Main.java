import com.vsquare.Reader;
import com.vsquare.impl.ExcelReader;

public class Main {

    public static void main(String[] args) {

        Reader reader = new ExcelReader();

        reader.read("/Users/colus4/Documents/VSQUARE/NSU/다국어 지원/남서울번역 요청_영문완료/입학교육/공과대학/건축공학과 교수진.xlsx");
    }
}

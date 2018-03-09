import com.vsquare.formater.impl.HTMLFormatter;
import com.vsquare.reader.impl.ExcelReader;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        ExcelReader reader = new ExcelReader();

        List<Map<String, String>> lists = reader.read("/Users/colus4/Documents/VSQUARE/NSU/다국어 지원/남서울번역 요청_영문완료/입학교육/교양과정부 교수진.xlsx");

        HTMLFormatter formater = new HTMLFormatter();
        String prefix = "<style>\n" +
                "    .professor_img img {\n" +
                "        width: 100%;\n" +
                "    }\n" +
                "</style>\n" +
                "<div class=\"page_tit\">Professor\n" +
                "    <span></span>\n" +
                "</div>\n";

        String suffix = "<div class=\"pager\"></div>\n" +
                "<div class=\"college_number\">\n" +
                "    <div class=\"number\">본 이용에 대한 운영/정보 담당은 건축공학과 TEL. 041-580-2762 입니다.</div>\n" +
                "    <div class=\"print_btn\"></div>\n" +
                "</div>\n";

        String target =
                "<div class=\"professor_item\">\n" +
                "    <div class=\"professor_img\">\n" +
                "        <img src=\"/api/file/get?path=board//17831895-a61e-478c-8582-687c6c4c974f.png\" />\n" +
                "    </div>\n" +
                "    <div class=\"professor_info\">\n" +
                "        <p class=\"professor_name\">${1}\n" +
                "            <span>Professor</span>\n" +
                "        </p>\n" +
                "        <div>\n" +
                "            <div>\n" +
                "                <span class=\"factor\">+Phone</span>\n" +
                "                <div>${6}</div>\n" +
                "            </div>\n" +
                "            <div>\n" +
                "                <span class=\"factor\">+E-mail</span>\n" +
                "                <div>${7}</div>\n" +
                "            </div>\n" +
                "            <div>\n" +
                "                <span class=\"factor\">+Field of Study</span>\n" +
                "                <div>\n" +
                "                    ${3}\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"mobile_hide\">\n" +
                "            <div>\n" +
                "                <span class=\"factor\">+Academic Background</span>\n" +
                "                <div>\n" +
                "                    ${4}\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div>\n" +
                "                <span class=\"factor\">+Major Career</span>\n" +
                "                <div>\n" +
                "                    ${5}\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"button_group mobile_show center\">\n" +
                "            <div class=\"unique_btn1 small\">더보기\n" +
                "                <img src=\"/res/service/img/sub1/ico_shortcut.png\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>";

        System.out.println(formater.format(prefix,
                                           suffix,
                                           target,
                                           lists));
    }
}

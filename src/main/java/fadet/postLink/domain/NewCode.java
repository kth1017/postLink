package fadet.postLink.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class NewCode {

    private String allNewCode;


    /*
    init method
     */


    public void init(OldCode oldCode) {
        // 기능1
        String[] oldCodeArrByKeyword =  oldCode.getAllCode().split(oldCode.getTitleHtmlKeyword());
        String addTitleText = oldCodeArrByKeyword[0];

        int arrLen = oldCodeArrByKeyword.length;
        for (int i = 1; i < arrLen; i++) {
            addTitleText += "blockquote data-ke-size=\"size16\" data-ke-style=\"style1\" "+"id=\""+i+"th\">";
            addTitleText += oldCodeArrByKeyword[i];
        }

        // 기능2
        String[] resultArr = addTitleText.split("<blockquote data-ke-style=\"style2\">Index</blockquote>");
//        String resultText = resultArr[0]+"<blockquote data-ke-style=\"style2\">Index";
        String resultText = resultArr[0]+oldCode.getIndexHtmlKeyword();

        for (int i = 0; i < oldCode.getNewTitleList().size(); i++) {
            int j = i+1;
            resultText += "<br /><a href=\"#"+j+"th\">"+j+" "+oldCode.getNewTitleList().get(i)+"</a>";
        }
        resultText += "</blockquote>"+resultArr[1];

        this.setAllNewCode(resultText);

    }

}

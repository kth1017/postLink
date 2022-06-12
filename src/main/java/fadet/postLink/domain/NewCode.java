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
        String[] oldCodeArrByKeyword = oldCode.getAllCode().split(oldCode.getTitleHtmlKeyword());
        String addTitleText = oldCodeArrByKeyword[0];

        String[] modiTitleKeywordArr = oldCode.getTitleHtmlKeyword().split(" ");
        String modiTitleKeyword = "";

        int arrLen = oldCodeArrByKeyword.length;
        for (int i = 1; i < arrLen; i++) {
            addTitleText += modiTitleKeywordArr[0] + " id=\""+i+"\" "+modiTitleKeywordArr[1]+" "+modiTitleKeywordArr[2];
            addTitleText += oldCodeArrByKeyword[i];
        }

        // 기능2

//        String[] resultArr = addTitleText.split("<blockquote data-ke-style=\"style2\">Index</blockquote>");
        String[] resultArr = addTitleText.split(oldCode.getIndexHtmlKeyword());
//        String resultText = resultArr[0]+"<blockquote data-ke-style=\"style2\">Index";

        String[] modiIndexKeywordArr = oldCode.getIndexHtmlKeyword().split("<");

        String modiIndexKeyword = "<"+modiIndexKeywordArr[0]+modiIndexKeywordArr[1];

        String resultText = resultArr[0]+modiIndexKeyword;

        for (int i = 0; i < oldCode.getNewTitleList().size(); i++) {
            int j = i+1;
            resultText += "<br /><a href=\"#"+j+"\">"+j+" "+oldCode.getNewTitleList().get(i)+"</a>";
        }
        resultText += "<"+modiIndexKeywordArr[2]+resultArr[1];

        this.setAllNewCode(resultText);

    }

}

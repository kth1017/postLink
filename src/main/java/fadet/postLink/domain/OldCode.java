package fadet.postLink.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OldCode {

    private Long id;

    // 필수 입력
    private String allCode;
    private String titleHtmlKeyword;

    // 입력값을 분리하여 변수로 생성
    private Long titleCount;

    private List<String> oldTitleList;
    private List<String> newTitleList;

    public OldCode(String allCode, String titleHtmlKeyword) {
        this.allCode = allCode;
        this.titleHtmlKeyword = titleHtmlKeyword;
    }

    /*
        init method
         */
    public void init() {
        String[] startTextArrByBracket = this.allCode.split("<");
        int textLen = startTextArrByBracket.length;


        // titleCount, oldTitleList init

        Long count = 0L;
        List<String> oldList = new ArrayList<>();

        for (int i = 0; i < textLen; i++) {
            if (startTextArrByBracket[i].equals(this.titleHtmlKeyword)) {
                count++;
                // 타이틀명 스트림으로 저장
                oldList.add(startTextArrByBracket[i+1]);
            }
        }

        setTitleCount(count);
        setOldTitleList(oldList);

        // newTitleList init

        List<String> newList = new ArrayList<>();

        for (int i = 0; i < this.oldTitleList.size(); i++) {
            newList.add(this.oldTitleList.get(i).split(">")[1]);;
        }

        setNewTitleList(newList);
    }
}

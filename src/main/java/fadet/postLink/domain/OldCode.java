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
    private String indexHtmlKeyword;

    // 입력값을 분리하여 변수로 생성
    private Long titleCount;

    private List<String> oldTitleList;
    private List<String> newTitleList;
    private int indexOver;


    /*
        init method
         */
    public void init() {

        // 여기서 타이틀을 추출하기위해 <로 나눴기때문에 이후 <를 고려해서 작성해야함
        String[] startTextArrByBracket = this.allCode.split("<");
        int textLen = startTextArrByBracket.length;


        // titleCount, oldTitleList init

        Long count = 0L;
        List<String> oldList = new ArrayList<>();

        //여기서 위에서 <를 잘랐기에 <를 빼줌
        String addBracketKeyword = this.titleHtmlKeyword.substring(1);

        for (int i = 0; i < textLen; i++) {
            if (startTextArrByBracket[i].equals(addBracketKeyword)) {
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

        // indexOver init

        String[] resultArr = allCode.split(this.indexHtmlKeyword);

        setIndexOver(resultArr.length);

    }
}

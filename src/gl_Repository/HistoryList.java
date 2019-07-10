package gl_Repository;

import gl_Object.History;
import gl_Enum.BookSituation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HistoryList {
    private List<History> histories = new ArrayList<>();

    public List<History> getHistories() {

        return histories;
    }

    public void setHistories(List<History> histories) {

        this.histories = histories;
    }

    //************************** History List **************************//
    public void DataHistoryList (HistoryList histories){
        histories.getHistories().add(new History(UUID.randomUUID(),"Hidari","Umi","Captain America",
                "Comic", "A0002","Stan Lee",BookSituation.Borrow,
                LocalDate.now(),LocalDate.now().plusDays(7)));
        histories.getHistories().add(new History(UUID.randomUUID(),"Harin","Eli","Black Widow",
                "Novel", "B0002","Stan Lee",BookSituation.Borrow,
                LocalDate.now().minusDays(7),LocalDate.now()));
        histories.getHistories().add(new History(UUID.randomUUID(),"Miki","Maki","Thor",
                "Comic", "A0003","Stan Lee",BookSituation.Borrow,
                LocalDate.now().minusDays(2),LocalDate.now().plusDays(5)));
    }

}

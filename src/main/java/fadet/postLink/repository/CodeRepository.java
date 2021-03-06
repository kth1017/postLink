package fadet.postLink.repository;

import fadet.postLink.domain.NewCode;
import fadet.postLink.domain.OldCode;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CodeRepository {

    private static Map<Long, OldCode> store = new HashMap<>();
    private static long sequence = 0L;

    public int size() {
        return store.size();
    }

    public OldCode save(OldCode oldCode) {
        oldCode.setId(++sequence);
        store.put(oldCode.getId(), oldCode);
        return oldCode;
    }

    public OldCode change(Long id, OldCode oldCode) {
        oldCode.setId(id);
        store.remove(id);
        store.put(id, oldCode);
        return oldCode;
    }

    public OldCode findOne(Long id) {
        return store.get(id);
    }

    public NewCode result(Long id) {
        OldCode oldCode = store.get(id);
        NewCode newCode = new NewCode();
        newCode.init(oldCode);
        return newCode;

    }

}

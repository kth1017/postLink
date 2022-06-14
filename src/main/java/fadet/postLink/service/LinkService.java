package fadet.postLink.service;

import fadet.postLink.domain.NewCode;
import fadet.postLink.domain.OldCode;
import fadet.postLink.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final CodeRepository codeRepository;

    public void saveCode(OldCode oldCode) {
        oldCode.init();
        codeRepository.save(oldCode);
    }

    public OldCode findOne(Long id) {
        return codeRepository.findOne(id);
    }

    public void changeCode(OldCode oldCode) {
        int id = codeRepository.size();
        codeRepository.change((long)id, oldCode);

    }

    public NewCode newCode(Long id) {
        NewCode result = codeRepository.result(id);
        return result;
    }
}

package fadet.postLink.service;

import fadet.postLink.domain.NewCode;
import fadet.postLink.domain.OldCode;
import fadet.postLink.repository.OldCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final OldCodeRepository oldCodeRepository;

    public void saveCode(OldCode oldCode) {
        oldCode.init();
        oldCodeRepository.save(oldCode);
    }

    public NewCode newCode(Long id) {
        NewCode result = oldCodeRepository.result(id);
        return result;
    }
}
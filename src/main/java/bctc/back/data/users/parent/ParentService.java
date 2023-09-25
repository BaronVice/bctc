package bctc.back.data.users.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentService implements IParentService {
    private final ParentRepository parentRepository;

    @Override
    public List<Parent> findAll() {
        return parentRepository.findAll();
    }
}

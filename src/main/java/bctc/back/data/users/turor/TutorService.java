package bctc.back.data.users.turor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorService implements ITutorService {
    private final TutorRepository tutorRepository;

    @Override
    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }
}

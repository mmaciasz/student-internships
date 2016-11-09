package pl.com.pollub.firm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.pollub.db.entities.Firm;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by mmaciasz on 2016-11-09.
 */
@Service
@Transactional
public class FirmService {

    private final FirmRepository firmRepository;

    @Autowired
    public FirmService(FirmRepository firmRepository) {
        this.firmRepository = firmRepository;
    }

    public List<Firm> findAll() {
        return StreamSupport.stream(firmRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Firm findById(Integer id) {
        return firmRepository.findOne(id);
    }

    public void deleteFirm(Integer id) {
        firmRepository.delete(id);
    }

    public Firm saveOrUpdate(Firm firm) {
        return firmRepository.save(firm);
    }
}

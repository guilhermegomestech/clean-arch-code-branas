package br.com.branas.glstore.application.usecases;

import br.com.branas.glstore.domain.entities.ZipCode;
import br.com.branas.glstore.infrastructure.repositories.ZipCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZipCodeService {

    private ZipCodeRepository zipCodeRepository;

    public ZipCodeService(ZipCodeRepository zipCodeRepository) {
        this.zipCodeRepository = zipCodeRepository;
    }

    public ZipCode save(ZipCode zipCode){
        return this.zipCodeRepository.save(zipCode);
    }
    public List<ZipCode> save(List<ZipCode> zipCode){
        return this.zipCodeRepository.saveAll(zipCode);
    }

    public List<ZipCode> getZipCodeByCodeList(List<String> codeList){
        return this.zipCodeRepository.findAllById(codeList);
    }
}

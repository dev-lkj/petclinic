package dev.spring.petclinic.service;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService{

    private final OwnerRepository ownerRepository; // Entity Manger 역할로 보면됨



    @Override
    public Owner save(Owner owner) {
        // DB 등록 처리 로직 구현
       return ownerRepository.save(owner);
    }

    @Override
    public Owner findById(Long ownerId) {
        // JPA - manager.find(Owner.class, 1);
        Optional<Owner> optionalOwner = ownerRepository.findById(ownerId);

        if(optionalOwner.isPresent()){ // optional 내부의 값이 유효하면
            return optionalOwner.get(); // optional 내부에서 해당 객체를 꺼냄
        }else {
           return null;
        }
        // 한줄로 표현
        // ownerRepository.findById(id).orElse(null); // orElseThrow(람다 형태) 활용 권장
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public List<Owner> findByAllLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }


}

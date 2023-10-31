package dev.spring.petclinic.controller;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.model.Pet;
import dev.spring.petclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller// bean으로 등록시키기 위한 annotation 추가
@RequiredArgsConstructor
public class PetController {
    private static final String VIEWS_PET_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final OwnerService ownerService;

//    @GetMapping("/owners/{ownerId}/pets/new")
    public String creationPets(@PathVariable Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId); // owner를 찾아옵니다.
        Pet newPet = new Pet(); // 새로운 Pet 객체를 생성합니다.
        owner.getPets().add(newPet); // Owner 객체의 Pets 리스트에 새로운 Pet을 추가합니다.
        newPet.setOwner(owner); // Pet 객체의 Owner를 설정합니다.

        model.addAttribute("pet", owner.getPets());

        return VIEWS_PET_CREATE_OR_UPDATE_FORM;
    }

}

package dev.spring.petclinic.controller;

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;
import dev.spring.petclinic.service.OwnerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller// bean으로 등록시키기 위한 annotation 추가
@RequiredArgsConstructor
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;


//    public OwnerController(OwnerService ownerService) {
//        super();
//        this.ownerService = ownerService;
//    }

    @GetMapping // localhost:8080/owners로 동작, 별도의 URL 작성하지 않음
    public String processFindForm(Owner owner, BindingResult result, Model model){

//        System.out.println(lastName);
        /*
        * 요구사항 - 특정 키워드로 검색 시 해당 키워드가 포함된 모든 Owner가 검색 되서 목록에 출력되도록 구현
        * 필요한 API - Spring Data JPA에서 제공하는 Query Method
        * findAll() 대신 다른 메서드를 사용해야함
        *
        * */
        // 별도의 파라미터 값 없이 /owners로 요청할 경우 모든 owners 데이터 반환
        if(owner.getLastName() == null ) owner.setLastName("");


        // 조건문을 통해 구현
        List<Owner> results = ownerService.findByAllLastNameLike("%" + owner.getLastName() +"%");
        if(results.isEmpty()){
            result.rejectValue("lastName","notFound", "not Found");
            return "owners/findOwners";
        }else if(results.size() == 1){ // 일치하는 Owners 데이터가 1명일 경우
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else { // 2명 이상의 Owner가 조회될 경우
            // ownerService를 통해 전체 Owner 목록 조회 후 반환 데이터를 모델에 추가하여 view 페이지로 반환
            // ex. model.addAttribute("listOwners", 결과 객체);
            model.addAttribute("listOwners", results); // ownersList.html에 작성된 key값과 일치하도록(listOwners)
            // 전체 목록으로 반환할 페이지 - owners/ownersList.html
            return "owners/ownersList";
        }



    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");

        mav.addObject(ownerService.findById(ownerId));

        return mav;
    }

    // @RequestMapping(value = "/find", method = RequestMethod.GET)
    @GetMapping("/find") // 위 코드위 축약 표현
    public String findOwners(Model model) {
        // In servlet, request.setAttribute("key", value); 코드와 동일
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }


    // Owner 등록 화면으로 렌더링해주는 메서드
    @GetMapping("/new") // localhost:8081/owners/new 요청 시 렌더링할 페이지 지정
    public String initCreationForm(Model model) {

        model.addAttribute("owner", Owner.builder().build());
        
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM; // templates/owners에서 찾아보기
    }

    // Owner 등록 처리 메서드
    @PostMapping("/new")
    public String processCreationForm(@Validated Owner owner, BindingResult result){

        System.out.println(owner.toString());

        if(result.hasErrors()){

            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else {
            // OwnerService 등록 처리 로직 구현 후 작성

            Owner savedOwner = ownerService.save(owner);

            return "redirect:/owners/" + savedOwner.getId();
        }
    }



    // Owner 업데이트 페이지 이동
    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model){

        model.addAttribute("owner", ownerService.findById(ownerId));

        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    // Owner 업데이트 페이지 처리
    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Validated Owner owner, BindingResult result, @PathVariable Long ownerId){

        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);

            return "redirect:/owners/" + savedOwner.getId();
        }
    }



}

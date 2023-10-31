package dev.spring.petclinic.controller;

// owner에 대한 처리 컨트롤러이기 때문에 owners 경로의 하위 요청이 올 경우 모두 수행하도록

import dev.spring.petclinic.model.Owner;
import dev.spring.petclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/owners") // localhost:8081/owners로 오는 모든 요청을 받음
public class OwnerRestController {

    private final OwnerService ownerService;


    // GET: localhost:8080/owners - 전체 Owner 목록 조회 요청
    @GetMapping
    public List<Owner> listOwner() {
        // service.findAll() 호출 및 반환
        List<Owner> owners = ownerService.findAll();

        return owners;
    }
}

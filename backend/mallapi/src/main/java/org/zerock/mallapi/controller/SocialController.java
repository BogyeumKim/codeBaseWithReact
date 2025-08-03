package org.zerock.mallapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.mallapi.dto.MemberDTO;
import org.zerock.mallapi.service.MemberServiceImpl;

@RestController
@Log4j2
@RequiredArgsConstructor
public class SocialController {

    private final MemberServiceImpl memberService;

    @GetMapping("/api/member/kakao")
    public String[] getMemberFromKakao(String accessToken) {

        log.info("access Token ");
        log.info(accessToken);

        MemberDTO memberDTO = memberService.getKakaoMember(accessToken);


        return new String[]{"AAA","BBB","CCC"};
    }

}
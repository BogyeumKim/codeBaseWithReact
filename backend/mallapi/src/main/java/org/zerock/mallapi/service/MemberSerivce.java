package org.zerock.mallapi.service;

import org.zerock.mallapi.domain.Member;
import org.zerock.mallapi.dto.MemberDTO;

import java.util.stream.Collectors;


public interface MemberSerivce {

    MemberDTO getKakaoMember(String accessToken);

    default MemberDTO entityToDTO(Member member) {

        MemberDTO dto = new MemberDTO(
                member.getEmail(),
                member.getPw(),
                member.getNickname(),
                member.isSocial(),
                member.getMemberRoleList().stream()
                        .map(Enum::name).collect(Collectors.toList()));

        return dto;
    }
}

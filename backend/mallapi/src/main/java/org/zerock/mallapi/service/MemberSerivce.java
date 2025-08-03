package org.zerock.mallapi.service;

import org.zerock.mallapi.dto.MemberDTO;


public interface MemberSerivce {

    MemberDTO getKakaoMember(String accessToken);
}

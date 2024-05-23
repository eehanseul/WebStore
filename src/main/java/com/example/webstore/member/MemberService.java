package com.example.webstore.member;

import com.example.webstore.exception.DuplicateException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class MemberService {
    MemberRepository memberRepository;


    public String signUp(SignUpDTO signUpDto) {
        Member requestMember = signUpDto.convertToEntity();
        memberRepository.save(requestMember);
        return memberRepository.findByUserId(requestMember.getUserId()).getUserId();
    }

    public boolean login(String userId, String pw) {
        Member existMember = memberRepository.findByUserIdAndPw(userId,pw);
        if(existMember==null)
            throw new NoSuchElementException("login error");
        else
            return true;
    }

    @Transactional
    public void deleteMember(String userId) {
        Member existMember = memberRepository.findByUserId(userId);
        if (existMember==null)
            throw new NoSuchElementException("Member not found with userId: " + userId);
        else
            memberRepository.deleteByUserId(userId);
    }

    // userId 중복 체크
    public void checkDuplicateId(String userId) {
        Member existMember = memberRepository.findByUserId(userId);
        if(existMember!=null)
            throw new DuplicateException("아이디 중복");
    }
}

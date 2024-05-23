package com.example.webstore.member;

import com.example.webstore.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.webstore.utils.ApiUtils.error;
import static com.example.webstore.utils.ApiUtils.success;

@Slf4j
@AllArgsConstructor
@RestController
public class MemberController {
    MemberService memberService;

    // 회원가입
    @PostMapping("/signup") // after
    public ResponseEntity<ApiUtils.ApiResult> signUp(@Valid @RequestBody SignUpDTO signUpDto) {
        // ID 중복 체크
        isDuplicateId(signUpDto);
        String savedMember = memberService.signUp(signUpDto);
        return new ResponseEntity(success(savedMember), HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDto) {
        memberService.login(loginDto.getUserId(), loginDto.getPw());
        return new ResponseEntity(success("login success"), HttpStatus.CREATED);
    }

    // 회원 탈퇴
    @DeleteMapping("/withdrawal/{userId}")
    public ResponseEntity deleteMember(@PathVariable("userId") String userId) {
        //Validator.isString(id);
        memberService.deleteMember(userId);
        return new ResponseEntity<>(success("withdrawl success"),HttpStatus.OK);
    }

    private void isDuplicateId(SignUpDTO signUpDto) {
        memberService.checkDuplicateId(signUpDto.getUserId());
    }
}

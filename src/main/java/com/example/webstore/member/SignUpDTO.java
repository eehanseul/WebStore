package com.example.webstore.member;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SignUpDTO {
    private int id;

    @NotBlank(message = "id는 필수입니다")
    private String userId;

    @NotBlank(message = "비밀번호는 필수입니다")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,15}", message = "비밀번호는 영문과 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~15자의 비밀번호여야 합니다.")
    private String pw;

    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @NotBlank(message = "이메일은 필수입니다")
    @Email(message = "이메일 형식으로 입력해주세요") // @Pattern 사용해서 정규표현식으로도 할 수 있음
    private String email;

    @NotBlank(message = "연락처는 필수입니다")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "'-'포함한 전화번호를 입력해주세요")
    private String contact;

    public Member convertToEntity(){
        return new Member(userId,pw,name,email,contact);
    }
}

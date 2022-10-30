package com.neo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 관리자 userdetails
 *
 * @author leekw
 */
@SuppressWarnings("serial")
public class CustomAdminDetails implements UserDetails {

    private String member_code;            // 회원코드
    private String member_id;            // 회원아이디
    private String member_name;            // 회원이름
    private String platform_gbn;        // 플랫폼구분
    private String platform_auth;        // 플랫폼권한


    public CustomAdminDetails(
            String member_code
            , String member_id
            , String member_name
            , String platform_gbn
            , String platform_auth
    ) {
        this.member_code = member_code;
        this.member_id = member_id;
        this.member_name = member_name;
        this.platform_gbn = platform_gbn;
        this.platform_auth = platform_auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername() {
        return this.member_id;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    public String getMember_code() {
        return member_code;
    }

    public void setMember_code(String member_code) {
        this.member_code = member_code;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getPlatform_gbn() {
        return platform_gbn;
    }

    public void setPlatform_gbn(String platform_gbn) {
        this.platform_gbn = platform_gbn;
    }

    public String getPlatform_auth() {
        return platform_auth;
    }

    public void setPlatform_auth(String platform_auth) {
        this.platform_auth = platform_auth;
    }

}

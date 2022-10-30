package com.neo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 상담팀 userdetails
 *
 * @author leekw
 */
@SuppressWarnings("serial")
public class CustomConsultDetails implements UserDetails {

    private String member_code;         // 회원코드
    private String member_id;           // 회원아이디
    private String member_name;         // 회원이름
    private String platform_gbn;        // 플랫폼구분
    private String platform_auth;       // 플랫폼권한
    private String branch_code;			// 지점코드
    private String branch_name;			// 지점명
    private String member_authority;    // 지점, 심사팀, 심사팀 관리자, 시스템 관리자 로 메뉴 권한 설정 위해 추가)
    private String member_authority_name;    // 지점, 심사팀, 심사팀 관리자, 시스템 관리자 로 메뉴 권한 설정 위해 추가)
    private String member_otp;          // otp key

    public CustomConsultDetails(
            String member_code
            , String member_id
            , String member_name
            , String platform_gbn
            , String platform_auth
            , String branch_code
            , String branch_name
            , String member_authority
            , String member_authority_name
            , String member_otp
    ) {
        this.member_code = member_code;
        this.member_id = member_id;
        this.member_name = member_name;
        this.platform_gbn = platform_gbn;
        this.platform_auth = platform_auth;
        this.branch_code = branch_code;
        this.branch_name = branch_name;
        this.member_authority = member_authority;
        this.member_authority_name = member_authority_name;
        this.member_otp = member_otp;
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

    public String getMember_authority() {
        return member_authority;
    }

    public void setMember_authority(String member_authority) {
        this.member_authority = member_authority;
    }

	/**
	 * @return the member_otp
	 */
	public String getMember_otp() {
		return member_otp;
	}

	/**
	 * @param member_otp the member_otp to set
	 */
	public void setMember_otp(String member_otp) {
		this.member_otp = member_otp;
	}

	/**
	 * @return the branch_code
	 */
	public String getBranch_code() {
		return branch_code;
	}

	/**
	 * @param branch_code the branch_code to set
	 */
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	/**
	 * @return the branch_name
	 */
	public String getBranch_name() {
		return branch_name;
	}

	/**
	 * @param branch_name the branch_name to set
	 */
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	/**
	 * @return the member_authority_name
	 */
	public String getMember_authority_name() {
		return member_authority_name;
	}

	/**
	 * @param member_authority_name the member_authority_name to set
	 */
	public void setMember_authority_name(String member_authority_name) {
		this.member_authority_name = member_authority_name;
	}
    
}

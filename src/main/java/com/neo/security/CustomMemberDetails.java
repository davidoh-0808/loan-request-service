package com.neo.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 회원 userdetails
 * @author leekw
 *
 */
@SuppressWarnings("serial")
public class CustomMemberDetails implements UserDetails {

	private String member_code;			// 회원코드
	private String member_id;			// 회원아이디
	private String member_name;			// 회원이름
	private String member_gubun;		// 멤버구분

	public CustomMemberDetails(
			String member_code
			, String member_id
			, String member_name
			, String member_gubun
		) {
		this.member_code = member_code;
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_gubun = member_gubun;
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

	public String getMember_gubun() {
		return member_gubun;
	}

	public void setMember_gubun(String member_gubun) {
		this.member_gubun = member_gubun;
	}

}

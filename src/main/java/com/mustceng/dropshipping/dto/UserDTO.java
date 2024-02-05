package com.mustceng.dropshipping.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;
	private Boolean active;
	private String username;
	private String password;
	private String email;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean forcePasswordChange;
	private Date passwordChangeDate;
	private Date lastPasswordResetDate;
	private boolean admin;

}

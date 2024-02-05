package com.mustceng.dropshipping.entity.user;

import com.mustceng.dropshipping.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

	@Column(name = "username", nullable = false)
	private String username;

	//encrypted password
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "account_non_expired")
	private boolean accountNonExpired = true;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked = true;

	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired = true;

	@Column(name = "force_password_change")
	private boolean forcePasswordChange = false;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "password_change_date")
	private Date passwordChangeDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_password_reset_date")
	private Date lastPasswordResetDate;

	@Column(name = "admin")
	private boolean admin = false;


}

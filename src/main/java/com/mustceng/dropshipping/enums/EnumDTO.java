package com.mustceng.dropshipping.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnumDTO {

	private String value;
	private String name;

	public EnumDTO(String name, String value) {
		this.name = name;
		this.value = value;
	}
}

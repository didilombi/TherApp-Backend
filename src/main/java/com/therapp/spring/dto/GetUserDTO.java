package com.therapp.spring.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GetUserDTO {

	private Long id;
	private String username;
	private String fotoPerfil;
	private Set<String> rol;
}

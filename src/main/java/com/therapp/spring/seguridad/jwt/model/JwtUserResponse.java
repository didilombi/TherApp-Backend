package com.therapp.spring.seguridad.jwt.model;

import java.util.Set;
import com.therapp.spring.dto.GetUserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUserDTO{

	private String token;
	
	@Builder(builderMethodName="jwtUserResponseBuilder")
	public JwtUserResponse(Long id, String username, String fotoPerfil, Set<String> rol, String token) {
		super(id, username, fotoPerfil, rol);
		this.token = token;
	}
}

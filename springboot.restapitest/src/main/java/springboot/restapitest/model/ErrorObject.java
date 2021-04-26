package springboot.restapitest.model;


import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorObject {
	
	@Schema(
			description = "HTTP status error code",
			example="204"
	)
	private String errorCode;
	
	@Schema(
			description = "Description and detail",
			example="Registro no existe en base de datos"
	)
	private String errorDescription;
}